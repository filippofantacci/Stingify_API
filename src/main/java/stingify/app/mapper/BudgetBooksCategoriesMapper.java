package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.BudgetBooksCategoriesDTO;
import stingify.app.entity.BudgetBooksCategories;

public interface BudgetBooksCategoriesMapper {

	public BudgetBooksCategoriesDTO toDto(BudgetBooksCategories budgetBooksCategories);
	
	public List<BudgetBooksCategoriesDTO> toDto(List<BudgetBooksCategories> budgetBooksCategories);
	
	public BudgetBooksCategories toEntity(BudgetBooksCategoriesDTO budgetBooksCategoriesDTO);
}
