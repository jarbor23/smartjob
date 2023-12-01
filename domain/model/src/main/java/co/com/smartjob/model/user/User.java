package co.com.smartjob.model.user;
import co.com.smartjob.model.phone.Phone;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    private String created;
    private Boolean isActive;
    private String modified;
    private String lastLogin;
    private List<Phone> phones;
}

