package stingify.app.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stingify.app.dto.UserDTO;
import stingify.app.entity.User;
import stingify.app.mapper.UserMapper;
import stingify.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public List<UserDTO> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return userMapper.toDto(users);
	}

	public UserDTO getUser(Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userMapper.toDto(userOptional.get());
		} else {
			return null;
		}
	}
	
	public UserDTO getUser(String authId) {
		return userMapper.toDto(userRepository.findUserByAuthId(authId));
	}

	public UserDTO addUser(UserDTO user) {
		user.setInsertionTimestamp(new Timestamp(new Date().getTime()));
		return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
	}

	public UserDTO updateUser(UserDTO user) {
		user.setChangeTimestamp(new Timestamp(new Date().getTime()));
		return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
	}

	public void deleteUser(UserDTO user) {
		userRepository.delete(userMapper.toEntity(user));
	}
}
