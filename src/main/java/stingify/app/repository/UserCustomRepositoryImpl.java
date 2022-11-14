package stingify.app.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import stingify.app.entity.User;

public class UserCustomRepositoryImpl implements UserCustomRepository {

	@Autowired
	private EntityManager entityManager;

	
	@Override
	public User findUserByAuthId(String authId) {
		String sql = "SELECT U FROM User U WHERE U.authId = :authId";

		final TypedQuery<User> query = entityManager.createQuery(sql, User.class);

		query.setParameter("authId", authId);

		return query.getResultList().stream().findFirst().orElse(null);
	}

}
