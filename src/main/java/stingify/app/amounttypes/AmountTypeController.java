package stingify.app.amounttypes;

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
import stingify.app.dto.AmountTypeDTO;

@OpenAPIDefinition(servers = { @Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class AmountTypeController {

	@Autowired
	private AmountTypeService amountTypeService;

	private static final Logger log = LoggerFactory.getLogger(AmountTypeController.class);

	@GetMapping("/amount-types")
	public ResponseEntity<List<AmountTypeDTO>> getAllAmountType() {
		log.info("REST request to /amount-types: getAllAmountType");
		try {
			List<AmountTypeDTO> amountTypes = amountTypeService.getAllAmountTypes();
			log.info("AmountTypes retrieved: {}", amountTypes.size());
			return new ResponseEntity<>(amountTypes, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/amount-types/{id}")
	public ResponseEntity<AmountTypeDTO> getAmountType(@PathVariable Integer id) {
		log.info("REST request to /amount-types/{id}: getAmountType with id {}", id);
		try {
			AmountTypeDTO amountType = amountTypeService.getAmountType(id);
			if (amountType != null) {
				log.info("Retrieved amountType: {}", amountType);
				return new ResponseEntity<>(amountType, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve amountType with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/amount-types")
	public ResponseEntity<AmountTypeDTO> addAmountType(@RequestBody AmountTypeDTO amountType) {
		log.info("REST request to /amount-types: addAmountType with input {}", amountType);
		try {
			AmountTypeDTO addedAmountType = amountTypeService.addAmountType(amountType);
			log.info("AmountType inserted : {}", addedAmountType);
			return new ResponseEntity<>(addedAmountType, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/amount-types")
	public ResponseEntity<AmountTypeDTO> updateAmountType(@RequestBody AmountTypeDTO amountType) {
		log.info("REST request to /amount-types: updateAmountType with input {}", amountType);
		try {
			AmountTypeDTO updatedAmountType = amountTypeService.updateAmountType(amountType);
			log.info("AmountType updated : {}", updatedAmountType);
			return new ResponseEntity<>(updatedAmountType, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/amount-types")
	public ResponseEntity<String> deleteAmountType(@RequestBody AmountTypeDTO amountType) {
		log.info("REST request to /amount-types: deleteAmountType with input {}", amountType);
		try {
			amountTypeService.deleteAmountType(amountType);
			log.info("Deleted amountType with AmountTypeId: {}", amountType.getAmountTypeId());
			return new ResponseEntity<>("AmountType deleted", HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>("Error in deleteAmountType", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
