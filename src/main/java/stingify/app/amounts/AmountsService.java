package stingify.app.amounts;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stingify.app.dto.AmountDTO;
import stingify.app.entity.Amount;
import stingify.app.mapper.AmountMapper;
import stingify.app.repository.AmountRepository;
import stingify.app.repository.BudgetBooksRepository;

@Service
@Transactional
public class AmountsService {

	@Autowired
	private AmountRepository amountRepository;

	@Autowired
	private BudgetBooksRepository budgetBooksRepository;

	@Autowired
	private AmountMapper amountMapper;

	public List<AmountDTO> getAllAmounts() {
		List<Amount> amounts = new ArrayList<>();
		amountRepository.findAll().forEach(amounts::add);
		return amountMapper.toDto(amounts);
	}

	public AmountDTO getAmount(Integer id) {
		Optional<Amount> amountOptional = amountRepository.findById(id);
		if (amountOptional.isPresent()) {
			return amountMapper.toDto(amountOptional.get());
		} else {
			return null;
		}
	}

	public Page<AmountDTO> getAmountsByBudgetBookId(Integer budgetBookId, Pageable pageable) {
		return amountRepository.findAmountsByBudgetBookId(budgetBookId, pageable).map(amountMapper::toDto);
	}

	public AmountDTO addAmount(AmountDTO amount) {
		amount.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		AmountDTO amountDTO = amountMapper.toDto(amountRepository.save(amountMapper.toEntity(amount)));
		budgetBooksRepository.updateChangeTimestamp(amountDTO.getBudgetBookId(), amountDTO.getInsertionTimestamp());
		return amountDTO;

	}

	public List<AmountDTO> addRecurringAmounts(List<AmountDTO> recurringAmounts) {
		Timestamp currentTimestamp = new Timestamp(new Date().getTime());

		List<AmountDTO> addedAmounts = new ArrayList<>();
		for (AmountDTO amountDTO : recurringAmounts) {
			amountDTO.setInsertionTimestamp(currentTimestamp);
			addedAmounts.add(amountMapper.toDto(amountRepository.save(amountMapper.toEntity(amountDTO))));
		}
		if (!addedAmounts.isEmpty()) {
			budgetBooksRepository.updateChangeTimestamp(addedAmounts.get(0).getBudgetBookId(), currentTimestamp);
		}
		return addedAmounts;
	}

	public AmountDTO updateAmount(AmountDTO amount) {
		amount.setChangeTimestamp(new Timestamp(new Date().getTime()));
		AmountDTO amountDTO = amountMapper.toDto(amountRepository.save(amountMapper.toEntity(amount)));
		budgetBooksRepository.updateChangeTimestamp(amountDTO.getBudgetBookId(), amountDTO.getChangeTimestamp());
		return amountDTO;
	}

	public void deleteAmount(AmountDTO amount) {
		amountRepository.delete(amountMapper.toEntity(amount));
		budgetBooksRepository.updateChangeTimestamp(amount.getBudgetBookId(), new Timestamp(new Date().getTime()));
	}

}
