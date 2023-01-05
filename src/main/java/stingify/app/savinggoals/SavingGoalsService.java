package stingify.app.savinggoals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stingify.app.dto.SavingGoalDTO;
import stingify.app.entity.SavingGoal;
import stingify.app.mapper.SavingGoalMapper;
import stingify.app.repository.SavingGoalRepository;

@Service
@Transactional
public class SavingGoalsService {

	@Autowired
	private SavingGoalRepository savingGoalRepository;

	@Autowired
	private SavingGoalMapper savingGoalMapper;

	public List<SavingGoalDTO> getAllSavingGoals() {
		List<SavingGoal> savingGoals = new ArrayList<>();
		savingGoalRepository.findAll().forEach(savingGoals::add);
		return savingGoalMapper.toDto(savingGoals);
	}

	public SavingGoalDTO getSavingGoal(Integer id) {
		Optional<SavingGoal> savingGoalOptional = savingGoalRepository.findById(id);
		if (savingGoalOptional.isPresent()) {
			return savingGoalMapper.toDto(savingGoalOptional.get());
		} else {
			return null;
		}
	}

	public List<SavingGoalDTO> getSavingGoalsByUserId(Integer userId) {
		return savingGoalMapper.toDto(savingGoalRepository.findSavingGoalByUserId(userId));

	}
	
	public SavingGoalDTO addSavingGoal(SavingGoalDTO savingGoal) {
		savingGoal.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		return savingGoalMapper.toDto(savingGoalRepository.save(savingGoalMapper.toEntity(savingGoal)));

	}

	public SavingGoalDTO updateSavingGoal(SavingGoalDTO savingGoal) {
		savingGoal.setChangeTimestamp(new Timestamp(new Date().getTime()));
		return savingGoalMapper.toDto(savingGoalRepository.save(savingGoalMapper.toEntity(savingGoal)));
	}

	public void deleteSavingGoal(SavingGoalDTO savingGoal) {
		savingGoalRepository.delete(savingGoalMapper.toEntity(savingGoal));
	}

}
