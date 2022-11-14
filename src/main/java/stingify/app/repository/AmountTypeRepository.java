package stingify.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stingify.app.entity.AmountType;

@Repository
public interface AmountTypeRepository  extends CrudRepository<AmountType, Integer>  {

}
