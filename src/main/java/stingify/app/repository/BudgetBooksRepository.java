package stingify.app.repository;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.BudgetBook;

@Repository
public interface BudgetBooksRepository  extends CrudRepository<BudgetBook, Integer>, UserBudgetBooksCustomRepository, BudgetBooksCategoriesCustomRepository  {

	@Query(value ="SELECT new BudgetBook (B "
			+ " , SUM(CASE WHEN A.amountType.amountTypeId = 1 THEN A.actual else 0 END) AS expenses "
			+ " , SUM(CASE WHEN A.amountType.amountTypeId = 2 THEN A.actual else 0 END) AS incomings "
			+ " , SUM(CASE WHEN A.amountType.amountTypeId = 3 THEN A.actual else 0 END) AS savings "
			+ " ) "
			+ "FROM BudgetBook B "
			+ " JOIN UserBudgetBooks UB "
			+ " ON B.budgetBookId = UB.budgetBookId "
			+ " AND UB.userId = :userId "
			+ " LEFT JOIN Amount A ON A.budgetBookId = B.budgetBookId "
			+ " WHERE UB.userId = :userId "
			+ " AND B.cancellationTimestamp = null "
			+ " GROUP BY B.budgetBookId "
			+ " ORDER BY "
			+ " CASE WHEN B.changeTimestamp IS NULL THEN B.insertionTimestamp ELSE B.changeTimestamp END DESC " )
	Page<BudgetBook> findByUserId(Integer userId, Pageable pageable);

	@Modifying
	@Query(value = "UPDATE BudgetBook B "
			+ " SET B.changeTimestamp = :changeTimestamp "
			+ " WHERE B.budgetBookId = :budgetBookId")
	void updateChangeTimestamp(Integer budgetBookId, Timestamp changeTimestamp);
}
