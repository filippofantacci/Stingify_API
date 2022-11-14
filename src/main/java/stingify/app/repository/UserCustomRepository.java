package stingify.app.repository;

import org.springframework.stereotype.Repository;

import stingify.app.entity.User;

@Repository
public interface UserCustomRepository {

	 User findUserByAuthId(String authId);
	 
}
