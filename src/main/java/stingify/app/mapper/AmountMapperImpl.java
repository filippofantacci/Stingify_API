package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.AmountDTO;
import stingify.app.entity.Amount;

@Component
public class AmountMapperImpl implements AmountMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AmountDTO toDto(Amount amount) {

		if (amount == null) {
			return null;
		}
		
		if (amount.getCategory() != null && amount.getCategory().getMacroCategory() != null) {			
			amount.getCategory().getMacroCategory().setCategories(new ArrayList<>(0));
		}

		AmountDTO amountDTO = modelMapper.map(amount, AmountDTO.class);
		
		amountDTO.setDate(amount.getDate());
		
		amountDTO.setInsertionTimestamp(amount.getInsertionTimestamp());
		amountDTO.setChangeTimestamp(amount.getChangeTimestamp());
		amountDTO.setCancellationTimestamp(amount.getCancellationTimestamp());
		
		return amountDTO;
	}

	@Override
	public List<AmountDTO> toDto(List<Amount> amountList) {
		if (amountList == null) {
			return new ArrayList<>();
		}

		List<AmountDTO> amountDTOList = new ArrayList<>(amountList.size());

		for (Amount amount : amountList) {
			amountDTOList.add(toDto(amount));
		}

		return amountDTOList;
	}

	@Override
	public Amount toEntity(AmountDTO amountDTO) {
		
		if (amountDTO == null) {
			return null;
		}

		
		Amount amount = modelMapper.map(amountDTO, Amount.class);
		
		amount.setDate(amountDTO.getDate());
		
		amount.setInsertionTimestamp(amountDTO.getInsertionTimestamp());
		amount.setChangeTimestamp(amountDTO.getChangeTimestamp());
		amount.setCancellationTimestamp(amountDTO.getCancellationTimestamp());
		
		return amount;
	}

}
