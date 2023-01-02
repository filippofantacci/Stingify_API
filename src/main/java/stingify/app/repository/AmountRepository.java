package stingify.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.Amount;

@Repository
public interface AmountRepository  extends CrudRepository<Amount, Integer>  {

	@Query(value = "SELECT A FROM Amount A " 
			+ " WHERE A.budgetBookId = :budgetBookId "
			+ " AND A.cancellationTimestamp = null "
			+ " ORDER BY A.date ASC")
	Page<Amount> findAmountsByBudgetBookId(Integer budgetBookId, Pageable pageable);
}
