package stingify.app.recurringamounts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import stingify.app.dto.RecurringAmountDTO;

@OpenAPIDefinition(servers = { @Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class RecurringAmountsController {

	@Autowired
	private RecurringAmountsService recurringAmountsService;

	private static final Logger log = LoggerFactory.getLogger(RecurringAmountsController.class);

	@GetMapping("/recurring-amounts")
	public ResponseEntity<List<RecurringAmountDTO>> getAllRecurringAmount() {
		log.info("REST request to /recurring-amounts: getAllRecurringAmount");
		try {
			List<RecurringAmountDTO> amounts = recurringAmountsService.getAllRecurringAmounts();
			log.info("RecurringAmounts retrieved: {}", amounts.size());
			return new ResponseEntity<>(amounts, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/recurring-amounts/{id}")
	public ResponseEntity<RecurringAmountDTO> getRecurringAmount(@PathVariable Integer id) {
		log.info("REST request to /recurring-amounts/{id}: getRecurringAmount with id {}", id);
		try {
			RecurringAmountDTO amount = recurringAmountsService.getRecurringAmount(id);
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

	@GetMapping("/recurring-amounts/user/{userId}")
	public ResponseEntity<List<RecurringAmountDTO>> getRecurringAmountByUserId(@PathVariable Integer userId) {
		log.info("REST request to /recurring-amounts/user/{userId}: getRecurringAmountByUserId with userId {}", userId);
		try {
			List<RecurringAmountDTO> recurringAmounts = recurringAmountsService.getRecurringAmountsByUserId(userId);
			log.info("RecurringAmounts retrieved: {}", recurringAmounts.size());
			return new ResponseEntity<>(recurringAmounts, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/recurring-amounts")
	public ResponseEntity<RecurringAmountDTO> addRecurringAmount(@RequestBody RecurringAmountDTO recurringAmount) {
		log.info("REST request to /recurring-amounts: addRecurringAmount with input {}", recurringAmount);
		try {
			RecurringAmountDTO addedRecurringAmount = recurringAmountsService.addRecurringAmount(recurringAmount);
			log.info("RecurringAmount inserted : {}", addedRecurringAmount);
			return new ResponseEntity<>(addedRecurringAmount, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/recurring-amounts")
	public ResponseEntity<RecurringAmountDTO> updateRecurringAmount(@RequestBody RecurringAmountDTO recurringAmount) {
		log.info("REST request to /recurring-amounts: updateRecurringAmount with input {}", recurringAmount);
		try {
			RecurringAmountDTO updatedRecurringAmount = recurringAmountsService.updateRecurringAmount(recurringAmount);
			log.info("RecurringAmount updated : {}", updatedRecurringAmount);
			return new ResponseEntity<>(updatedRecurringAmount, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/recurring-amounts")
	public ResponseEntity<String> deleteRecurringAmount(@RequestBody RecurringAmountDTO recurringAmount) {
		log.info("REST request to /recurring-amounts: deleteRecurringAmount with input {}", recurringAmount);
		try {
			recurringAmountsService.deleteRecurringAmount(recurringAmount);
			log.info("Deleted amount with RecurringAmountId: {}", recurringAmount.getRecurringAmountId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
