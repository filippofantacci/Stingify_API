package stingify.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.SavingGoal;

@Repository
public interface SavingGoalRepository extends CrudRepository<SavingGoal, Integer> {

	@Query(value ="SELECT new SavingGoal (SG "
			+ " , SUM(COALESCE(A.actual, 0)) AS actual "
			+ " ) "
			+ "FROM SavingGoal SG "
			+ " LEFT JOIN Amount A "
			+ " ON A.category.categoryId = SG.category.categoryId "
			+ " AND A.creatorUserId = :userId "
			+ " AND A.cancellationTimestamp = null "
			+ " WHERE SG.creatorUserId = :userId "
			+ " GROUP BY SG.savingGoalId "
			+ " ORDER BY "
			+ " CASE WHEN SG.changeTimestamp IS NULL THEN SG.insertionTimestamp ELSE SG.changeTimestamp END ASC " )
	List<SavingGoal> findSavingGoalByUserId(Integer userId);

}
