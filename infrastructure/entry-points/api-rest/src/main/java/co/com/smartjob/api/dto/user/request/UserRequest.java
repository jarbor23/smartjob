package co.com.smartjob.api.dto.user.request;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@JsonDeserialize(as = UserRequest.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "The name is required.")
    private String name;
    @NotNull(message = "The email is required.")
    @Email
    private String email;
    @NotNull(message = "The password is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@.#])(?=.*\\d)[a-zA-Z@.#\\d]{8,12}$",
            message = "The password must meet the specified criteria."
    )
    private String password;
    @Valid
    private List<@Valid @NotNull(message = "Phone information is required.") PhoneRequest> phones;

}
