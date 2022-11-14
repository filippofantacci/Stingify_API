package stingify.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.UserBudgetBooks;

@Repository
public interface UserBudgetBooksRepository  extends CrudRepository<UserBudgetBooks, Integer>, UserBudgetBooksCustomRepository  {


}
