package stingify.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Integer>, UserCustomRepository  {

}
