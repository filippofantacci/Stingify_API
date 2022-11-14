package stingify.app.amounts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import stingify.app.dto.AmountDTO;

@OpenAPIDefinition(servers = { @Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class AmountsController {

	@Autowired
	private AmountsService amountsService;

	private static final Logger log = LoggerFactory.getLogger(AmountsController.class);

	@GetMapping("/amounts")
	public ResponseEntity<List<AmountDTO>> getAllAmount() {
		log.info("REST request to /amounts: getAllAmount");
		try {
			List<AmountDTO> amounts = amountsService.getAllAmounts();
			log.info("Amounts retrieved: {}", amounts.size());
			return new ResponseEntity<>(amounts, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/amounts/{id}")
	public ResponseEntity<AmountDTO> getAmount(@PathVariable Integer id) {
		log.info("REST request to /amounts/{id}: getAmount with id {}", id);
		try {
			AmountDTO amount = amountsService.getAmount(id);
			if (amount != null) {
				log.info("Retrieved amount: {}", amount);
				return new ResponseEntity<>(amount, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve amount with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/amounts/budget-book/")
	public ResponseEntity<Page<AmountDTO>> getAmountsByBudgetBookId(@ParameterObject Pageable pageable,
			@ParameterObject Integer budgetBookId) {
		log.info("REST request to /amounts/budget-book/: getAmountsByBudgetBookId with budgetBookId {} , pageable {}",
				budgetBookId, pageable);
		try {
			Page<AmountDTO> amountsPaged = amountsService.getAmountsByBudgetBookId(budgetBookId, pageable);
			log.info("Amounts retrieved: {}", amountsPaged.getSize());
			return new ResponseEntity<>(amountsPaged, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/amounts")
	public ResponseEntity<AmountDTO> addAmount(@RequestBody AmountDTO amount) {
		log.info("REST request to /amounts: addAmount with input {}", amount);
		try {
			AmountDTO addedAmount = amountsService.addAmount(amount);
			log.info("Amount inserted : {}", addedAmount);
			return new ResponseEntity<>(addedAmount, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/amounts")
	public ResponseEntity<AmountDTO> updateAmount(@RequestBody AmountDTO amount) {
		log.info("REST request to /amounts: updateAmount with input {}", amount);
		try {
			AmountDTO updatedAmount = amountsService.updateAmount(amount);
			log.info("Amount updated : {}", updatedAmount);
			return new ResponseEntity<>(updatedAmount, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/amounts")
	public ResponseEntity<String> deleteAmount(@RequestBody AmountDTO amount) {
		log.info("REST request to /amounts: deleteAmount with input {}", amount);
		try {
			amountsService.deleteAmount(amount);
			log.info("Deleted amount with AmountId: {}", amount.getAmountId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
