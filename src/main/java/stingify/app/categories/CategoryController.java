package stingify.app.categories;

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
import stingify.app.dto.CategoryDTO;

@OpenAPIDefinition(servers = {
		@Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		log.info("REST request to /categories: getAllCategory");
		try {
			List<CategoryDTO> categories = categoryService.getAllCategorys();
			log.info("Categorys retrieved: {}", categories.size());
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) {
		log.info("REST request to /categories/{id}: getCategory with id {}", id);
		try {
			CategoryDTO category = categoryService.getCategory(id);
			if (category != null) {
				log.info("Retrieved category: {}", category);
				return new ResponseEntity<>(category, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve category with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/categories/user/{userId}")
	public ResponseEntity<List<CategoryDTO>> getCategoriesByUserId(@PathVariable Integer userId, boolean unused) {
		log.info("REST request to /categories/user/{userId}: getCategoryByUserId with id {}", userId);
		try {
			List<CategoryDTO> categories = categoryService.getCategoriesByUserId(userId, unused);
			log.info("Categorys retrieved: {}", categories.size());
			return new ResponseEntity<>(categories, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@PostMapping("/categories")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO category) {
		log.info("REST request to /categories: addCategory with input {}", category);
		try {
			CategoryDTO addedCategory = categoryService.addCategory(category);
			log.info("Category inserted : {}", addedCategory);
			return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/categories")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO category) {
		log.info("REST request to /categories: updateCategory with input {}", category);
		try {
			CategoryDTO updatedCategory = categoryService.updateCategory(category);
			log.info("Category updated : {}", updatedCategory);
			return new ResponseEntity<>(updatedCategory, HttpStatus.OK);		
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categories")
	public ResponseEntity<String> deleteCategory(@RequestBody CategoryDTO category) {
		log.info("REST request to /categories: deleteCategory with input {}", category);
		try {
			categoryService.deleteCategory(category);
			log.info("Deleted category with CategoryId: {}", category.getCategoryId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
