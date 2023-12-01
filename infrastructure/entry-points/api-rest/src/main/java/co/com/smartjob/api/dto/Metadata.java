package co.com.smartjob.api.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Metadata {
    private String message;
    private String errors;
}
