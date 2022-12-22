package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.RecurringAmountDTO;
import stingify.app.entity.RecurringAmount;

public interface RecurringAmountMapper {

	public RecurringAmountDTO toDto(RecurringAmount recurringAmount);
	
	public List<RecurringAmountDTO> toDto(List<RecurringAmount> recurringAmountList);
	
	public RecurringAmount toEntity(RecurringAmountDTO recurringAmountDTO);
}
