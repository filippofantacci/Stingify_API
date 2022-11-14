package stingify.app.macrocategories;

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
import stingify.app.dto.MacroCategoryDTO;

@OpenAPIDefinition(servers = {
		@Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class MacroCategoryController {
	
	@Autowired
	private MacroCategoryService macroCategoryService;

	private static final Logger log = LoggerFactory.getLogger(MacroCategoryController.class);

	@GetMapping("/macro-categories")
	public ResponseEntity<List<MacroCategoryDTO>> getAllMacroCategory() {
		log.info("REST request to /macro-categories: getAllMacroCategory");
		try {
			List<MacroCategoryDTO> macroCategories = macroCategoryService.getAllMacroCategorys();
			log.info("MacroCategorys retrieved: {}", macroCategories.size());
			return new ResponseEntity<>(macroCategories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/macro-categories/{id}")
	public ResponseEntity<MacroCategoryDTO> getMacroCategory(@PathVariable Integer id) {
		log.info("REST request to /macro-categories/{id}: getMacroCategory with id {}", id);
		try {
			MacroCategoryDTO macroCategory = macroCategoryService.getMacroCategory(id);
			if (macroCategory != null) {
				log.info("Retrieved macroCategory: {}", macroCategory);
				return new ResponseEntity<>(macroCategory, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve macroCategory with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/macro-categories/user/{userId}")
	public ResponseEntity<List<MacroCategoryDTO>> getMacroCategoriesByUserId(@PathVariable Integer userId) {
		log.info("REST request to /macro-categories/user/{userId}: getMacroCategoriesByUserId with id {}", userId);
		try {
			List<MacroCategoryDTO> macroCategories = macroCategoryService.getMacroCategoriesByUserId(userId);
			log.info("MacroCategorys retrieved: {}", macroCategories.size());
			return new ResponseEntity<>(macroCategories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/macro-categories/budget-book/{budgetBookId}")
	public ResponseEntity<List<MacroCategoryDTO>> getMacroCategoriesByBudgetBookId(@PathVariable Integer budgetBookId) {
		log.info("REST request to /macro-categories/budget-book/{budgetBookId}: getMacroCategoriesByBudgetBookId with id {}", budgetBookId);
		try {
			List<MacroCategoryDTO> macroCategories = macroCategoryService.getMacroCategoriesByBudgetBookId(budgetBookId);
			log.info("MacroCategorys retrieved: {}", macroCategories.size());
			return new ResponseEntity<>(macroCategories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/macro-categories")
	public ResponseEntity<MacroCategoryDTO> addMacroCategory(@RequestBody MacroCategoryDTO macroCategory) {
		log.info("REST request to /macro-categories: addMacroCategory with input {}", macroCategory);
		try {
			MacroCategoryDTO addedMacroCategory = macroCategoryService.addMacroCategory(macroCategory);
			log.info("MacroCategory inserted : {}", addedMacroCategory);
			return new ResponseEntity<>(addedMacroCategory, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/macro-categories")
	public ResponseEntity<MacroCategoryDTO> updateMacroCategory(@RequestBody MacroCategoryDTO macroCategory) {
		log.info("REST request to /macro-categories: updateMacroCategory with input {}", macroCategory);
		try {
			MacroCategoryDTO updatedMacroCategory = macroCategoryService.updateMacroCategory(macroCategory);
			log.info("MacroCategory updated : {}", updatedMacroCategory);
			return new ResponseEntity<>(updatedMacroCategory, HttpStatus.OK);		
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/macro-categories")
	public ResponseEntity<String> deleteMacroCategory(@RequestBody MacroCategoryDTO macroCategory) {
		log.info("REST request to /macro-categories: deleteMacroCategory with input {}", macroCategory);
		try {
			macroCategoryService.deleteMacroCategory(macroCategory);
			log.info("Deleted macroCategory with MacroCategoryId: {}", macroCategory.getMacroCategoryId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
