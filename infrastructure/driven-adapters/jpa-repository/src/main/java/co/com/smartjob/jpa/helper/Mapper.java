package co.com.smartjob.jpa.helper;

import co.com.smartjob.jpa.phone.EntityPhone;
import co.com.smartjob.jpa.user.EntityUser;
import co.com.smartjob.model.phone.Phone;
import co.com.smartjob.model.user.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    private Mapper(){
        super();
    };

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static EntityUser userToEntityUser(User user){
        return EntityUser.builder()
                .token(user.getToken())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(phonesToEntityPhones(user.getPhones()))
                .isActive(user.getIsActive())
                .build();
    }
    public static EntityPhone phoneToEntityPhone(Phone phone){
        return EntityPhone.builder()
                .countryCode(phone.getCityCode())
                .number(phone.getNumber())
                .cityCode(phone.getCityCode())
                .build();
    }

    public static List<EntityPhone> phonesToEntityPhones(List<Phone> phones){
        return phones.stream().map(Mapper::phoneToEntityPhone).collect(Collectors.toList());
    }

    public static User EntityUserToUser(EntityUser entityUser){
        return User.builder()
                .token(entityUser.getToken())
                .name(entityUser.getName())
                .email(entityUser.getEmail())
                .password(entityUser.getPassword())
                .phones(entityPhonesToPhones(entityUser.getPhones()))
                .id(entityUser.getUuid())
                .created(entityUser.getCreated().format(formatter))
                .isActive(entityUser.getIsActive())
                .lastLogin(entityUser.getLastLogin().format(formatter))
                .modified(entityUser.getModified().format(formatter))
                .build();
    }
    public static Phone entityPhoneToPhone(EntityPhone entityPhone){
        return Phone.builder()
                .countryCode(entityPhone.getCityCode())
                .number(entityPhone.getNumber())
                .cityCode(entityPhone.getCityCode())
                .build();
    }
    public static List<Phone> entityPhonesToPhones(List<EntityPhone> entityPhones){
        return entityPhones.stream().map(Mapper::entityPhoneToPhone).collect(Collectors.toList());
    }
}
