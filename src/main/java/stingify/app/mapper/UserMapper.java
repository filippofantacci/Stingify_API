package stingify.app.mapper;

import java.util.List;

import stingify.app.dto.UserDTO;
import stingify.app.entity.User;

public interface UserMapper {

	public UserDTO toDto(User user);
	
	public List<UserDTO> toDto(List<User> userList);
	
	public User toEntity(UserDTO userDTO);
}
