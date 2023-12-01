package co.com.smartjob.model.user.gateways;

import co.com.smartjob.model.user.User;

import java.util.List;

public interface UserRepository {
    public List<User> getUser();
    public User createUser(User user);
    public User getUserByEmail(String email);
}
