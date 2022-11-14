package stingify.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import stingify.app.entity.BudgetBooksCategories;
import stingify.app.entity.Category;

@Repository
public interface BudgetBooksCategoriesCustomRepository {

	List<Category> findCategoriesByBudgetBookId(Integer budgetBookId);
	
	BudgetBooksCategories findByBudgetBookIdAndCategoryId(Integer budgetBookId, Integer categoryId);

}
