package co.com.smartjob.api.mapper;

import co.com.smartjob.api.dto.user.request.PhoneRequest;
import co.com.smartjob.api.dto.user.request.UserRequest;
import co.com.smartjob.model.phone.Phone;
import co.com.smartjob.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper(){
        super();
    };
    public static User userFromRequest(UserRequest request){
       return User.builder()
               .email(request.getEmail())
               .name(request.getName())
               .password(request.getPassword())
               .phones(phonesFromRequest(request.getPhones()))
               .build();
    }
    public static Phone phoneFromRequest(PhoneRequest request){
        return Phone.builder()
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .number(request.getNumber())
                .build();
    }
    public static List<Phone> phonesFromRequest(List<PhoneRequest> phonesRequest){
        return phonesRequest.stream().map(UserMapper::phoneFromRequest).collect(Collectors.toList());
    }

}
