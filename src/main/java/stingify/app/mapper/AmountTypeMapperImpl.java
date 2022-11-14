package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.AmountTypeDTO;
import stingify.app.entity.AmountType;

@Component
public class AmountTypeMapperImpl implements AmountTypeMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AmountTypeDTO toDto(AmountType amountType) {

		if (amountType == null) {
			return null;
		}

		return modelMapper.map(amountType, AmountTypeDTO.class);
	}

	@Override
	public List<AmountTypeDTO> toDto(List<AmountType> amountTypeList) {
		if (amountTypeList == null) {
			return new ArrayList<>();
		}

		List<AmountTypeDTO> amountTypeDTOList = new ArrayList<>(amountTypeList.size());

		for (AmountType amountType : amountTypeList) {
			amountTypeDTOList.add(toDto(amountType));
		}

		return amountTypeDTOList;
	}

	@Override
	public AmountType toEntity(AmountTypeDTO amountTypeDTO) {
		
		if (amountTypeDTO == null) {
			return null;
		}
		
		return modelMapper.map(amountTypeDTO, AmountType.class);
	}

}
