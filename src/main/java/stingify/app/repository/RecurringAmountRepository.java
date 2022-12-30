package stingify.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.RecurringAmount;

@Repository
public interface RecurringAmountRepository  extends CrudRepository<RecurringAmount, Integer>  {

	@Query(value = "SELECT RA FROM RecurringAmount RA " 
			+ " WHERE RA.creatorUserId = :userId "
			+ " AND RA.cancellationTimestamp = null"
			+ " ORDER BY RA.description ASC")
	Page<RecurringAmount> findRecurringAmountsByUserId(Integer userId, Pageable pageable);
}
