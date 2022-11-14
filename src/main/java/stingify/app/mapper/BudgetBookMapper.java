package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.BudgetBookDTO;
import stingify.app.entity.BudgetBook;

public interface BudgetBookMapper {

	public BudgetBookDTO toDto(BudgetBook budgetBook);
	
	public List<BudgetBookDTO> toDto(List<BudgetBook> budgetBook);
	
	public BudgetBook toEntity(BudgetBookDTO budgetBookDTO);
}
