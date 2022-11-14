package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.BudgetBooksCategoriesDTO;
import stingify.app.entity.BudgetBooksCategories;

@Component
public class BudgetBooksCategoriesMapperImpl implements BudgetBooksCategoriesMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public BudgetBooksCategoriesDTO toDto(BudgetBooksCategories budgetBooksCategories) {

		if (budgetBooksCategories == null) {
			return null;
		}
		
		BudgetBooksCategoriesDTO budgetBooksCategoriesDTO = modelMapper.map(budgetBooksCategories, BudgetBooksCategoriesDTO.class);
		budgetBooksCategoriesDTO.setInsertionTimestamp(budgetBooksCategories.getInsertionTimestamp());
		budgetBooksCategoriesDTO.setChangeTimestamp(budgetBooksCategories.getChangeTimestamp());
		budgetBooksCategoriesDTO.setCancellationTimestamp(budgetBooksCategories.getCancellationTimestamp());
		
		return budgetBooksCategoriesDTO;
	}

	@Override
	public List<BudgetBooksCategoriesDTO> toDto(List<BudgetBooksCategories> budgetBooksCategoriesList) {
		if (budgetBooksCategoriesList == null) {
			return new ArrayList<>();
		}

		List<BudgetBooksCategoriesDTO> budgetBooksCategoriesDTOList = new ArrayList<>(budgetBooksCategoriesList.size());

		for (BudgetBooksCategories budgetBooksCategories : budgetBooksCategoriesList) {
			budgetBooksCategoriesDTOList.add(toDto(budgetBooksCategories));
		}

		return budgetBooksCategoriesDTOList;
	}

	@Override
	public BudgetBooksCategories toEntity(BudgetBooksCategoriesDTO budgetBooksCategoriesDTO) {
		
		if (budgetBooksCategoriesDTO == null) {
			return null;
		}
		
		BudgetBooksCategories budgetBooksCategories = modelMapper.map(budgetBooksCategoriesDTO, BudgetBooksCategories.class);
		budgetBooksCategories.setInsertionTimestamp(budgetBooksCategoriesDTO.getInsertionTimestamp());
		budgetBooksCategories.setChangeTimestamp(budgetBooksCategoriesDTO.getChangeTimestamp());
		budgetBooksCategories.setCancellationTimestamp(budgetBooksCategoriesDTO.getCancellationTimestamp());
		
		return budgetBooksCategories;
	}

}
