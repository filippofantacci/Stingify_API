package stingify.app.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import stingify.app.entity.BudgetBook;
import stingify.app.entity.UserBudgetBooks;

public class UserBudgetBooksCustomRepositoryImpl implements UserBudgetBooksCustomRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<BudgetBook> findByUserId(Integer userId) {
		String sql = "SELECT B "
				+ " FROM BudgetBook B "
				+ " JOIN UserBudgetBooks UB "
				+ " ON B.budgetBookId = UB.budgetBookId "
				+ " AND UB.userId = :userId "
				+ " WHERE UB.userId = :userId "
				+ " ORDER BY "
				+ " CASE WHEN B.changeTimestamp IS NULL "
				+ " THEN B.insertionTimestamp ELSE B.changeTimestamp END DESC ";

		final TypedQuery<BudgetBook> query = entityManager.createQuery(sql, BudgetBook.class);

		query.setParameter("userId", userId);

		return query.getResultList();
	}

	@Override
	public UserBudgetBooks findByUserIdAndBudgetBookId(Integer userId, Integer budgetBookId) {
		String sql = "SELECT UB "
				+ " FROM UserBudgetBooks UB "
				+ " WHERE UB.userId = :userId "
				+ " AND UB.budgetBookId = :budgetBookId";

		final TypedQuery<UserBudgetBooks> query = entityManager.createQuery(sql, UserBudgetBooks.class);

		query.setParameter("userId", userId);
		query.setParameter("budgetBookId", budgetBookId);

		Optional<UserBudgetBooks> optionalUserBudgetBooks = query.getResultList().stream().findFirst();
		
		if (optionalUserBudgetBooks.isPresent()) {
			return optionalUserBudgetBooks.get();
		} else {
			return null;
		}
	}
}
