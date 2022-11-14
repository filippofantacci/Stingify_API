package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.AmountTypeDTO;
import stingify.app.entity.AmountType;

public interface AmountTypeMapper {

	public AmountTypeDTO toDto(AmountType amountType);
	
	public List<AmountTypeDTO> toDto(List<AmountType> amountTypeList);
	
	public AmountType toEntity(AmountTypeDTO amountTypeDTO);
}
