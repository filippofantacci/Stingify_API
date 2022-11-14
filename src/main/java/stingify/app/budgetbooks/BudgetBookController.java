package stingify.app.budgetbooks;

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
import stingify.app.dto.BudgetBookDTO;
import stingify.app.dto.BudgetBooksCategoriesDTO;
import stingify.app.dto.CategoryDTO;
import stingify.app.dto.UserBudgetBooksDTO;

@OpenAPIDefinition(servers = { @Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class BudgetBookController {

	@Autowired
	private BudgetBooksService budgetBooksService;

	private static final Logger log = LoggerFactory.getLogger(BudgetBookController.class);

	@GetMapping("/budget-books")
	public ResponseEntity<List<BudgetBookDTO>> getAllBudgetBook() {
		log.info("REST request to /budget-books/: getAllBudgetBook");
		try {
			List<BudgetBookDTO> budgetBooks = budgetBooksService.getAllBudgetBooks();
			log.info("BudgetBooks retrieved: {}", budgetBooks.size());
			return new ResponseEntity<>(budgetBooks, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/budget-books/{budgetBookId}")
	public ResponseEntity<BudgetBookDTO> getBudgetBookById(@PathVariable Integer budgetBookId) {
		log.info("REST request to /budget-books/{budgetBookId}: getBudgetBookById with budgetBookId {}", budgetBookId);
		try {
			BudgetBookDTO budgetBook = budgetBooksService.getBudgetBook(budgetBookId);
			log.info("BudgetBook with id {} retrieved: {}", budgetBookId, budgetBook);
			return new ResponseEntity<>(budgetBook, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/budget-books/user")
	public ResponseEntity<Page<BudgetBookDTO>> getAllUserBudgetBooksByUserId(@ParameterObject Pageable pageable,
			@ParameterObject Integer userId) {
		log.info(
				"REST request to /budget-books/user : getAllUserBudgetBooksByUserId with userId {}, pageable {}",
				userId, pageable);
		try {
			Page<BudgetBookDTO> budgetBooksPaged = budgetBooksService.getAllUserBudgetBooksByUserId(userId, pageable);
			log.info("BudgetBooks for the user {} retrieved: {}", userId, budgetBooksPaged.getSize());
			return new ResponseEntity<>(budgetBooksPaged, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/budget-books/{budgetBookId}/categories")
	public ResponseEntity<List<CategoryDTO>> getAllBudgetBooksCategories(
			@PathVariable(required = true) Integer budgetBookId) {
		log.info(
				"REST request to /budget-books/{budgetBookId}/categories : getAllBudgetBooksCategories with budgetBookId {}",
				budgetBookId);
		try {
			List<CategoryDTO> categories = budgetBooksService.getAllBudgetBooksCategories(budgetBookId);
			log.info("Categories for the budgetBook {} retrieved: {}", budgetBookId, categories.size());
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/budget-books")
	public ResponseEntity<BudgetBookDTO> addBudgetBook(@RequestBody BudgetBookDTO budgetBookDTO) {
		log.info("REST request to /budget-books: addBudgetBook with input {}", budgetBookDTO);
		try {
			BudgetBookDTO addedBudgetBook = budgetBooksService.addBudgetBook(budgetBookDTO);
			log.info("BudgetBooks insered : {}", addedBudgetBook);
			return new ResponseEntity<>(addedBudgetBook, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/budget-books/user")
	public ResponseEntity<BudgetBookDTO> addUserToBudgetBook(@RequestBody UserBudgetBooksDTO userBudgetBookDTO) {
		log.info("REST request to /budget-books/user/: addUserToBudgetBook with budgetBookId: {} and userId: {}",
				userBudgetBookDTO.getBudgetBookId(), userBudgetBookDTO.getUserId());
		try {
			BudgetBookDTO addedBudgetBook = budgetBooksService.addUserBudgetBooks(userBudgetBookDTO);
			log.info("User {} added to budgetBook : {}", userBudgetBookDTO.getUserId(),
					userBudgetBookDTO.getBudgetBookId());
			return new ResponseEntity<>(addedBudgetBook, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/budget-books/category")
	public ResponseEntity<List<CategoryDTO>> addCategoryToBudgetBook(
			@RequestBody BudgetBooksCategoriesDTO budgetBooksCategoriesDTO) {
		log.info(
				"REST request to /budget-books/category/: addCategoryToBudgetBook with budgetBookId: {} and categoryId: {}",
				budgetBooksCategoriesDTO.getBudgetBookId(), budgetBooksCategoriesDTO.getCategoryId());
		try {
			List<CategoryDTO> addedCategories = budgetBooksService.addBudgetBooksCategory(budgetBooksCategoriesDTO);
			log.info("Category {} added to budgetBook : {}", budgetBooksCategoriesDTO.getCategoryId(),
					budgetBooksCategoriesDTO.getBudgetBookId());
			return new ResponseEntity<>(addedCategories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/budget-books")
	public ResponseEntity<BudgetBookDTO> updateBudgetBook(@RequestBody BudgetBookDTO budgetBookDTO) {
		log.info("REST request to /budget-books: updateBudgetBook with input {}", budgetBookDTO);
		try {
			BudgetBookDTO updatedBudgetBook = budgetBooksService.updateBudgetBook(budgetBookDTO);
			log.info("BudgetBooks updated : {}", updatedBudgetBook);
			return new ResponseEntity<>(updatedBudgetBook, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/budget-books")
	public ResponseEntity<String> deleteBudgetBook(@RequestBody BudgetBookDTO budgetBookDTO) {
		log.info("REST request to /budget-books: deleteBudgetBook with input {}", budgetBookDTO);
		try {
			budgetBooksService.deleteBudgetBook(budgetBookDTO);
			log.info("Deleted BudgetBooks with id : {}", budgetBookDTO.getBudgetBookId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/budget-books/user")
	public ResponseEntity<String> deleteUserToBudgetBook(@RequestBody UserBudgetBooksDTO userBudgetBookDTO) {
		log.info("REST request to /budget-books/user/: deleteUserToBudgetBook with budgetBookId: {} and userId: {}",
				userBudgetBookDTO.getBudgetBookId(), userBudgetBookDTO.getUserId());
		try {
			budgetBooksService.deleteUserBudgetBooks(userBudgetBookDTO);
			log.info("Deleted UserBudgetBooks : {}", userBudgetBookDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/budget-books/category")
	public ResponseEntity<String> deleteCategoryToBudgetBook(
			@RequestBody BudgetBooksCategoriesDTO budgetBooksCategoriesDTO) {
		log.info(
				"REST request to /budget-books/category/: deleteCategoryToBudgetBook with budgetBookId: {} and categoryId: {}",
				budgetBooksCategoriesDTO.getBudgetBookId(), budgetBooksCategoriesDTO.getCategoryId());
		try {
			budgetBooksService.deleteBudgetBooksCategory(budgetBooksCategoriesDTO);
			log.info("Deleted BudgetBooksCategories : {}", budgetBooksCategoriesDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
