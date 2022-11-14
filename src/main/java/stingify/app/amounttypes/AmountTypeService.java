package stingify.app.amounttypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stingify.app.dto.AmountTypeDTO;
import stingify.app.entity.AmountType;
import stingify.app.mapper.AmountTypeMapper;
import stingify.app.repository.AmountTypeRepository;

@Service
public class AmountTypeService {

	@Autowired
	private AmountTypeRepository amountTypeRepository;

	@Autowired
	private AmountTypeMapper amountTypeMapper;

	public List<AmountTypeDTO> getAllAmountTypes() {
		List<AmountType> utenti = new ArrayList<>();
		amountTypeRepository.findAll().forEach(utenti::add);
		return amountTypeMapper.toDto(utenti);
	}

	public AmountTypeDTO getAmountType(Integer id) {
		Optional<AmountType> amountTypeOptional = amountTypeRepository.findById(id);
		if (amountTypeOptional.isPresent()) {
			return amountTypeMapper.toDto(amountTypeOptional.get());
		} else {
			return null;
		}
	}
	
	public AmountTypeDTO addAmountType(AmountTypeDTO amountType) {
		return amountTypeMapper.toDto(amountTypeRepository.save(amountTypeMapper.toEntity(amountType)));
	}

	public AmountTypeDTO updateAmountType(AmountTypeDTO amountType) {
		return amountTypeMapper.toDto(amountTypeRepository.save(amountTypeMapper.toEntity(amountType)));
	}

	public void deleteAmountType(AmountTypeDTO amountType) {
		amountTypeRepository.delete(amountTypeMapper.toEntity(amountType));
	}
}
