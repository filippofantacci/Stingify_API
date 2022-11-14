package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.BudgetBookDTO;
import stingify.app.entity.BudgetBook;

@Component
public class BudgetBookMapperImpl implements BudgetBookMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BudgetBookDTO toDto(BudgetBook budgetBook) {

		if (budgetBook == null) {
			return null;
		}
		
		BudgetBookDTO budgetBookDTO = modelMapper.map(budgetBook, BudgetBookDTO.class);
		budgetBookDTO.setInsertionTimestamp(budgetBook.getInsertionTimestamp());
		budgetBookDTO.setChangeTimestamp(budgetBook.getChangeTimestamp());
		budgetBookDTO.setCancellationTimestamp(budgetBook.getCancellationTimestamp());
		
		return budgetBookDTO;
	}

	@Override
	public List<BudgetBookDTO> toDto(List<BudgetBook> budgetBookList) {
		if (budgetBookList == null) {
			return new ArrayList<>();
		}

		List<BudgetBookDTO> budgetBookDTOList = new ArrayList<>(budgetBookList.size());

		for (BudgetBook budgetBook : budgetBookList) {
			budgetBookDTOList.add(toDto(budgetBook));
		}

		return budgetBookDTOList;
	}

	@Override
	public BudgetBook toEntity(BudgetBookDTO budgetBookDTO) {
		
		if (budgetBookDTO == null) {
			return null;
		}
		
		BudgetBook budgetBook = modelMapper.map(budgetBookDTO, BudgetBook.class);
		budgetBook.setInsertionTimestamp(budgetBookDTO.getInsertionTimestamp());
		budgetBook.setChangeTimestamp(budgetBookDTO.getChangeTimestamp());
		budgetBook.setCancellationTimestamp(budgetBookDTO.getCancellationTimestamp());
		
		return budgetBook;
	}

}
