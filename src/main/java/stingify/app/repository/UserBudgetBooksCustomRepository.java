package stingify.app.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import stingify.app.entity.BudgetBook;
import stingify.app.entity.UserBudgetBooks;

@Repository
public interface UserBudgetBooksCustomRepository {

	List<BudgetBook> findByUserId(Integer userId);
	
	UserBudgetBooks findByUserIdAndBudgetBookId(Integer userId, Integer budgetBookId);

}
