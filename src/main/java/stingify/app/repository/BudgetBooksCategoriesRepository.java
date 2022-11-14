package stingify.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.BudgetBooksCategories;

@Repository
public interface BudgetBooksCategoriesRepository
		extends CrudRepository<BudgetBooksCategories, Integer>, BudgetBooksCategoriesCustomRepository {


}
