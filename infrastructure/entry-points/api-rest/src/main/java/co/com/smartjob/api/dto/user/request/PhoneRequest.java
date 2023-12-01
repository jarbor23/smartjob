package co.com.smartjob.api.dto.user.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonDeserialize(as = PhoneRequest.class)
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {
    @NotNull( message = "The number is required.")
    private String number;
    @NotNull( message = "The city_code is required.")
    private String cityCode;
    @NotNull( message = "The country_code is required.")
    private String countryCode;

}
