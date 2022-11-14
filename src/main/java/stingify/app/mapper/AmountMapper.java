package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.AmountDTO;
import stingify.app.entity.Amount;

public interface AmountMapper {

	public AmountDTO toDto(Amount amount);
	
	public List<AmountDTO> toDto(List<Amount> amountList);
	
	public Amount toEntity(AmountDTO amountDTO);
}
