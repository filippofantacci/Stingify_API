package stingify.app.user;

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
import stingify.app.dto.UserDTO;

@OpenAPIDefinition(servers = {
		@Server(url = "/app/stingify/api/v1", description = "API of Stingify microservice") })
@RequestMapping(produces = "application/json")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		log.info("REST request to /users: getAllUser");
		try {
			List<UserDTO> users = userService.getAllUsers();
			log.info("Users retrieved: {}", users.size());
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
		log.info("REST request to /users/{id}: getUser with id {}", id);
		try {
			UserDTO user = userService.getUser(id);
			if (user != null) {
				log.info("Retrieved user: {}", user);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve user with id : {}", id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/auth/{authId}")
	public ResponseEntity<UserDTO> getUserByAuthId(@PathVariable String authId) {
		log.info("REST request to /users/{authId}: getUser with authId {}", authId);
		try {
			UserDTO user = userService.getUser(authId);
			if (user != null) {
				log.info("Retrieved user: {}", user);
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				log.info("Can Not retrieve user with authId : {}", authId);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
		log.info("REST request to /users: addUser with input {}", user);
		try {
			UserDTO addedUser = userService.addUser(user);
			log.info("User inserted : {}", addedUser);
			return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
		log.info("REST request to /users: updateUser with input {}", user);
		try {
			UserDTO updatedUser = userService.updateUser(user);
			log.info("User updated : {}", updatedUser);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);		
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users")
	public ResponseEntity<String> deleteUser(@RequestBody UserDTO user) {
		log.info("REST request to /users: deleteUser with input {}", user);
		try {
			userService.deleteUser(user);
			log.info("Deleted user with UserId: {}", user.getUserId());
			return new ResponseEntity<>("User deleted", HttpStatus.OK);
		} catch (Exception e) {
			log.error("error message {}", e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>("Error in deleteUser",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
