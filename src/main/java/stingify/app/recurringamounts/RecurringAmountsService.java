package stingify.app.recurringamounts;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stingify.app.dto.RecurringAmountDTO;
import stingify.app.entity.RecurringAmount;
import stingify.app.mapper.RecurringAmountMapper;
import stingify.app.repository.RecurringAmountRepository;

@Service
@Transactional
public class RecurringAmountsService {

	@Autowired
	private RecurringAmountRepository recurringAmountRepository;

	@Autowired
	private RecurringAmountMapper recurringAmountMapper;

	public List<RecurringAmountDTO> getAllRecurringAmounts() {
		List<RecurringAmount> recurringAmounts = new ArrayList<>();
		recurringAmountRepository.findAll().forEach(recurringAmounts::add);
		return recurringAmountMapper.toDto(recurringAmounts);
	}

	public RecurringAmountDTO getRecurringAmount(Integer id) {
		Optional<RecurringAmount> recurringAmountOptional = recurringAmountRepository.findById(id);
		if (recurringAmountOptional.isPresent()) {
			return recurringAmountMapper.toDto(recurringAmountOptional.get());
		} else {
			return null;
		}
	}

	public List<RecurringAmountDTO> getRecurringAmountsByUserId(Integer userId) {
		return recurringAmountRepository.findRecurringAmountsByUserId(userId, Pageable.unpaged())
				.map(recurringAmountMapper::toDto).toList();
	}

	public RecurringAmountDTO addRecurringAmount(RecurringAmountDTO recurringAmount) {
		recurringAmount.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		return recurringAmountMapper
				.toDto(recurringAmountRepository.save(recurringAmountMapper.toEntity(recurringAmount)));

	}

	public RecurringAmountDTO updateRecurringAmount(RecurringAmountDTO recurringAmount) {
		recurringAmount.setChangeTimestamp(new Timestamp(new Date().getTime()));
		return recurringAmountMapper
				.toDto(recurringAmountRepository.save(recurringAmountMapper.toEntity(recurringAmount)));
	}

	public void deleteRecurringAmount(RecurringAmountDTO recurringAmount) {
		recurringAmountRepository.delete(recurringAmountMapper.toEntity(recurringAmount));
	}

}
