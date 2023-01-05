package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.SavingGoalDTO;
import stingify.app.entity.SavingGoal;

public interface SavingGoalMapper {

	public SavingGoalDTO toDto(SavingGoal savingGoal);
	
	public List<SavingGoalDTO> toDto(List<SavingGoal> savingGoalList);
	
	public SavingGoal toEntity(SavingGoalDTO savingGoalDTO);
}
