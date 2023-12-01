package co.com.smartjob.api;
import co.com.smartjob.api.dto.user.request.UserRequest;
import co.com.smartjob.api.mapper.UserMapper;

import co.com.smartjob.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final UserUseCase userUseCase;


    @GetMapping(path = "/path")
    public String commandName() {
        return "Hello World";
    }

    @PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object createUser(@Valid @RequestBody UserRequest request){
        return userUseCase.createUser(UserMapper.userFromRequest(request));
    }
}
