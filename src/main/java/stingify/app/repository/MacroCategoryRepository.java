package stingify.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.MacroCategory;

@Repository
public interface MacroCategoryRepository  extends CrudRepository<MacroCategory, Integer>  {

	@Query(value= " SELECT DISTINCT MC "
			+ " FROM MacroCategory MC "
			+ " LEFT JOIN Category C "
			+ "  ON C.macroCategory.macroCategoryId = MC.macroCategoryId "
			+ " WHERE MC.creatorUserId = :userId "
			+ " ORDER BY "
			+ " CASE WHEN MC.changeTimestamp IS NULL "
			+ " THEN MC.insertionTimestamp ELSE MC.changeTimestamp END DESC ")
	List<MacroCategory> getCategoriesByUserId(Integer userId);
	
	@Query(
	   value= " SELECT DISTINCT MC "
			+ " FROM MacroCategory MC "
			+ " JOIN Category C "
			+ "     ON C.macroCategory.macroCategoryId = MC.macroCategoryId "
			+ " JOIN BudgetBooksCategories BBC "
			+ "     ON BBC.categoryId = C.categoryId "
			+ " WHERE "
			+ " BBC.budgetBookId = :budgetBookId ")
	List<MacroCategory> getMacroCategoriesByBudgetBookId(Integer budgetBookId);
}
