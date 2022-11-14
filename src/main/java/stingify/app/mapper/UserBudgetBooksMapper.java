package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.UserBudgetBooksDTO;
import stingify.app.entity.UserBudgetBooks;

public interface UserBudgetBooksMapper {

	public UserBudgetBooksDTO toDto(UserBudgetBooks userBudgetBooks);
	
	public List<UserBudgetBooksDTO> toDto(List<UserBudgetBooks> userBudgetBooks);
	
	public UserBudgetBooks toEntity(UserBudgetBooksDTO userBudgetBooksDTO);
}
