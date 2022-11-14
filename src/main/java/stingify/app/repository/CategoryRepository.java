package stingify.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.Category;

@Repository
public interface CategoryRepository  extends CrudRepository<Category, Integer>  {

	@Query(value= " SELECT C "
			+ " FROM Category C "
			+ " WHERE C.creatorUserId = :userId "
			+ " ORDER BY "
			+ " CASE WHEN C.changeTimestamp IS NULL THEN C.insertionTimestamp ELSE C.changeTimestamp END DESC ")
	List<Category> getCategoriesByUserId(Integer userId);

	@Query(value= " SELECT C "
			+ " FROM Category C "
			+ " WHERE C.creatorUserId = :userId "
			+ " AND C.macroCategory IS NULL "
			+ " ORDER BY "
			+ " CASE WHEN C.changeTimestamp IS NULL THEN C.insertionTimestamp ELSE C.changeTimestamp END DESC ")
	List<Category> getUnusedCategoriesByUserId(Integer userId);

}
