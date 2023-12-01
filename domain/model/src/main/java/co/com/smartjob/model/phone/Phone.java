package co.com.smartjob.model.phone;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
