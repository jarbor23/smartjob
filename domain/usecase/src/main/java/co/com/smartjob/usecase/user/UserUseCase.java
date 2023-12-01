package co.com.smartjob.usecase.user;

import co.com.smartjob.model.exception.BusinessException;
import co.com.smartjob.model.user.gateways.TokenAccess;
import co.com.smartjob.model.user.User;
import co.com.smartjob.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    private final TokenAccess tokenAccess;
    public List<User> getUser(){
        return userRepository.getUser();
    }
    public User createUser(User user){
        Optional.ofNullable(userRepository.getUserByEmail(user.getEmail()))
                .ifPresent(existingUser -> {
                    throw new BusinessException("The email is already registered");
                });
        String token = tokenAccess.getToken(user.getEmail());
        user.setToken(token);
        user.setIsActive(true);
        return userRepository.createUser(user);
    }

}
