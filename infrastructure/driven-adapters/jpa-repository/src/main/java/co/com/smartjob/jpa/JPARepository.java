package co.com.smartjob.jpa;

import co.com.smartjob.jpa.user.EntityUser;
import co.com.smartjob.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.UUID;

public interface JPARepository extends CrudRepository<EntityUser, UUID>, QueryByExampleExecutor<EntityUser> {
    public EntityUser findByEmail(String email);
}
