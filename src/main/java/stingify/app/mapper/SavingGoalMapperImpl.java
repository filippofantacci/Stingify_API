package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.SavingGoalDTO;
import stingify.app.entity.SavingGoal;

@Component
public class SavingGoalMapperImpl implements SavingGoalMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SavingGoalDTO toDto(SavingGoal savingGoal) {

		if (savingGoal == null) {
			return null;
		}

		if (savingGoal.getCategory() != null && savingGoal.getCategory().getMacroCategory() != null) {
			savingGoal.getCategory().getMacroCategory().setCategories(new ArrayList<>(0));
		}

		SavingGoalDTO savingGoalDTO = modelMapper.map(savingGoal, SavingGoalDTO.class);

		savingGoalDTO.setInsertionTimestamp(savingGoal.getInsertionTimestamp());
		savingGoalDTO.setChangeTimestamp(savingGoal.getChangeTimestamp());
		savingGoalDTO.setCancellationTimestamp(savingGoal.getCancellationTimestamp());

		return savingGoalDTO;
	}

	@Override
	public List<SavingGoalDTO> toDto(List<SavingGoal> savingGoalList) {
		if (savingGoalList == null) {
			return new ArrayList<>();
		}

		List<SavingGoalDTO> savingGoalDTOList = new ArrayList<>(savingGoalList.size());

		for (SavingGoal savingGoal : savingGoalList) {
			savingGoalDTOList.add(toDto(savingGoal));
		}

		return savingGoalDTOList;
	}

	@Override
	public SavingGoal toEntity(SavingGoalDTO savingGoalDTO) {

		if (savingGoalDTO == null) {
			return null;
		}

		SavingGoal savingGoal = modelMapper.map(savingGoalDTO, SavingGoal.class);

		savingGoal.setInsertionTimestamp(savingGoalDTO.getInsertionTimestamp());
		savingGoal.setChangeTimestamp(savingGoalDTO.getChangeTimestamp());
		savingGoal.setCancellationTimestamp(savingGoalDTO.getCancellationTimestamp());

		return savingGoal;
	}

}
