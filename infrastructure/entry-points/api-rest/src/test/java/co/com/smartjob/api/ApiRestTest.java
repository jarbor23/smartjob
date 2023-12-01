package co.com.smartjob.api;

import co.com.smartjob.usecase.user.UserUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    @Mock
    UserUseCase userUseCase;
    ApiRest apiRest = new ApiRest(userUseCase);

    @Test
    void apiRestTest() {
        var response = apiRest.commandName();
        assertEquals("Hello World", response);
    }
}
