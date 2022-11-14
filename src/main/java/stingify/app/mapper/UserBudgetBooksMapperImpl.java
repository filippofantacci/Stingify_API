package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.UserBudgetBooksDTO;
import stingify.app.entity.UserBudgetBooks;

@Component
public class UserBudgetBooksMapperImpl implements UserBudgetBooksMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserBudgetBooksDTO toDto(UserBudgetBooks userBudgetBooks) {

		if (userBudgetBooks == null) {
			return null;
		}
		
		UserBudgetBooksDTO userBudgetBooksDTO = modelMapper.map(userBudgetBooks, UserBudgetBooksDTO.class);
		userBudgetBooksDTO.setInsertionTimestamp(userBudgetBooks.getInsertionTimestamp());
		userBudgetBooksDTO.setChangeTimestamp(userBudgetBooks.getChangeTimestamp());
		userBudgetBooksDTO.setCancellationTimestamp(userBudgetBooks.getCancellationTimestamp());
		
		return userBudgetBooksDTO;
	}

	@Override
	public List<UserBudgetBooksDTO> toDto(List<UserBudgetBooks> userBudgetBooksList) {
		if (userBudgetBooksList == null) {
			return new ArrayList<>();
		}

		List<UserBudgetBooksDTO> userBudgetBooksDTOList = new ArrayList<>(userBudgetBooksList.size());

		for (UserBudgetBooks userBudgetBooks : userBudgetBooksList) {
			userBudgetBooksDTOList.add(toDto(userBudgetBooks));
		}

		return userBudgetBooksDTOList;
	}

	@Override
	public UserBudgetBooks toEntity(UserBudgetBooksDTO userBudgetBooksDTO) {
		
		if (userBudgetBooksDTO == null) {
			return null;
		}
		
		UserBudgetBooks userBudgetBooks = modelMapper.map(userBudgetBooksDTO, UserBudgetBooks.class);
		userBudgetBooks.setInsertionTimestamp(userBudgetBooksDTO.getInsertionTimestamp());
		userBudgetBooks.setChangeTimestamp(userBudgetBooksDTO.getChangeTimestamp());
		userBudgetBooks.setCancellationTimestamp(userBudgetBooksDTO.getCancellationTimestamp());
		
		return userBudgetBooks;
	}

}
