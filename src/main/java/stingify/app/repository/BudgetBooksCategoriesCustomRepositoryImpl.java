package stingify.app.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import stingify.app.entity.BudgetBooksCategories;
import stingify.app.entity.Category;

public class BudgetBooksCategoriesCustomRepositoryImpl implements BudgetBooksCategoriesCustomRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Category> findCategoriesByBudgetBookId(Integer budgetBookId) {
		String sql = "SELECT C "
				+ " FROM Category C "
				+ " JOIN BudgetBooksCategories BC "
				+ " ON C.categoryId = BC.categoryId "
				+ " AND BC.budgetBookId = :budgetBookId "
				+ " WHERE BC.budgetBookId = :budgetBookId "
				+ " AND BC.cancellationTimestamp IS NULL";

		final TypedQuery<Category> query = entityManager.createQuery(sql, Category.class);

		query.setParameter("budgetBookId", budgetBookId);

		return query.getResultList();	
		
	}

	@Override
	public BudgetBooksCategories findByBudgetBookIdAndCategoryId(Integer budgetBookId, Integer categoryId) {
		String sql = "SELECT BC "
				+ " FROM BudgetBooksCategories BC "
				+ " WHERE BC.budgetBookId = :budgetBookId "
				+ " AND BC.cancellationTimestamp IS NULL "
				+ " AND BC.categoryId = :categoryId";

		final TypedQuery<BudgetBooksCategories> query = entityManager.createQuery(sql, BudgetBooksCategories.class);

		query.setParameter("budgetBookId", budgetBookId);
		query.setParameter("categoryId", categoryId);

		Optional<BudgetBooksCategories> optionalBudgetBooksCategories = query.getResultList().stream().findFirst();

		if (optionalBudgetBooksCategories.isPresent()) {
			return optionalBudgetBooksCategories.get();
		} else {
			return null;
		}
	}
}
