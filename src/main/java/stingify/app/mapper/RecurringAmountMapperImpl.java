package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.RecurringAmountDTO;
import stingify.app.entity.RecurringAmount;

@Component
public class RecurringAmountMapperImpl implements RecurringAmountMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RecurringAmountDTO toDto(RecurringAmount recurringAmount) {

		if (recurringAmount == null) {
			return null;
		}
		
		if (recurringAmount.getCategory() != null && recurringAmount.getCategory().getMacroCategory() != null) {			
			recurringAmount.getCategory().getMacroCategory().setCategories(new ArrayList<>(0));
		}

		RecurringAmountDTO recurringAmountDTO = modelMapper.map(recurringAmount, RecurringAmountDTO.class);
		
		recurringAmountDTO.setInsertionTimestamp(recurringAmount.getInsertionTimestamp());
		recurringAmountDTO.setChangeTimestamp(recurringAmount.getChangeTimestamp());
		recurringAmountDTO.setCancellationTimestamp(recurringAmount.getCancellationTimestamp());
		
		return recurringAmountDTO;
	}

	@Override
	public List<RecurringAmountDTO> toDto(List<RecurringAmount> recurringAmountList) {
		if (recurringAmountList == null) {
			return new ArrayList<>();
		}

		List<RecurringAmountDTO> recurringAmountDTOList = new ArrayList<>(recurringAmountList.size());

		for (RecurringAmount recurringAmount : recurringAmountList) {
			recurringAmountDTOList.add(toDto(recurringAmount));
		}

		return recurringAmountDTOList;
	}

	@Override
	public RecurringAmount toEntity(RecurringAmountDTO recurringAmountDTO) {
		
		if (recurringAmountDTO == null) {
			return null;
		}
		
		RecurringAmount recurringAmount = modelMapper.map(recurringAmountDTO, RecurringAmount.class);
				
		recurringAmount.setInsertionTimestamp(recurringAmountDTO.getInsertionTimestamp());
		recurringAmount.setChangeTimestamp(recurringAmountDTO.getChangeTimestamp());
		recurringAmount.setCancellationTimestamp(recurringAmountDTO.getCancellationTimestamp());
		
		return recurringAmount;
	}

}
