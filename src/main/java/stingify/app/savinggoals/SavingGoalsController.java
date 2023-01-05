package stingify.app.savinggoals;

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
import stingify.app.dto.SavingGoalDTO;

@OpenAPIDefinition(servers = { @Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class SavingGoalsController {

	@Autowired
	private SavingGoalsService savingGoalsService;

	private static final Logger log = LoggerFactory.getLogger(SavingGoalsController.class);

	@GetMapping("/saving-goals")
	public ResponseEntity<List<SavingGoalDTO>> getAllSavingGoal() {
		log.info("REST request to /saving-goals: getAllSavingGoal");
		try {
			List<SavingGoalDTO> savingGoals = savingGoalsService.getAllSavingGoals();
			log.info("SavingGoals retrieved: {}", savingGoals.size());
			return new ResponseEntity<>(savingGoals, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/saving-goals/{id}")
	public ResponseEntity<SavingGoalDTO> getSavingGoal(@PathVariable Integer id) {
		log.info("REST request to /saving-goals/{id}: getSavingGoal with id {}", id);
		try {
			SavingGoalDTO savingGoal = savingGoalsService.getSavingGoal(id);
			if (savingGoal != null) {
				log.info("Retrieved savingGoal: {}", savingGoal);
				return new ResponseEntity<>(savingGoal, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve savingGoal with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/saving-goals/user/{userId}")
	public ResponseEntity<List<SavingGoalDTO>> getSavingGoalsByUserId(@PathVariable Integer userId) {
		log.info("REST request to /saving-goals/user/: getSavingGoalsByUserId with userId {}",
				userId);
		try {
			List<SavingGoalDTO> savingGoals = savingGoalsService.getSavingGoalsByUserId(userId);
			log.info("SavingGoals retrieved: {}", savingGoals.size());
			return new ResponseEntity<>(savingGoals, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/saving-goals")
	public ResponseEntity<SavingGoalDTO> addSavingGoal(@RequestBody SavingGoalDTO savingGoal) {
		log.info("REST request to /saving-goals: addSavingGoal with input {}", savingGoal);
		try {
			SavingGoalDTO addedSavingGoal = savingGoalsService.addSavingGoal(savingGoal);
			log.info("SavingGoal inserted : {}", addedSavingGoal);
			return new ResponseEntity<>(addedSavingGoal, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/saving-goals")
	public ResponseEntity<SavingGoalDTO> updateSavingGoal(@RequestBody SavingGoalDTO savingGoal) {
		log.info("REST request to /saving-goals: updateSavingGoal with input {}", savingGoal);
		try {
			SavingGoalDTO updatedSavingGoal = savingGoalsService.updateSavingGoal(savingGoal);
			log.info("SavingGoal updated : {}", updatedSavingGoal);
			return new ResponseEntity<>(updatedSavingGoal, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/saving-goals")
	public ResponseEntity<String> deleteSavingGoal(@RequestBody SavingGoalDTO savingGoal) {
		log.info("REST request to /saving-goals: deleteSavingGoal with input {}", savingGoal);
		try {
			savingGoalsService.deleteSavingGoal(savingGoal);
			log.info("Deleted savingGoal with SavingGoalId: {}", savingGoal.getSavingGoalId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
