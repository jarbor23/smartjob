package co.com.smartjob.jpa;

import co.com.smartjob.jpa.helper.AdapterOperations;
import co.com.smartjob.jpa.helper.Mapper;
import co.com.smartjob.jpa.user.EntityUser;
import co.com.smartjob.model.user.User;
import co.com.smartjob.model.user.gateways.UserRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JPARepositoryAdapter extends AdapterOperations<User, EntityUser, UUID, JPARepository>
 implements UserRepository
{

    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public List<User> getUser() {
        return null;
    }

    @Override
    public User createUser(User user) {
        repository.save(Mapper.userToEntityUser(user));
        return this.getUserByEmail(user.getEmail());

    }

    @Override
    public User getUserByEmail(String email) {
        return Optional.ofNullable(repository.findByEmail(email)).map(Mapper::EntityUserToUser).orElse(null);
    }
}
