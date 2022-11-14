package stingify.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stingify.app.dto.UserDTO;
import stingify.app.entity.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO toDto(User user) {

		if (user == null) {
			return null;
		}

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		userDTO.setInsertionTimestamp(user.getInsertionTimestamp());
		userDTO.setChangeTimestamp(user.getChangeTimestamp());
		userDTO.setCancellationTimestamp(user.getCancellationTimestamp());
		
		return userDTO;
	}

	@Override
	public List<UserDTO> toDto(List<User> userList) {
		if (userList == null) {
			return new ArrayList<>();
		}

		List<UserDTO> userDTOList = new ArrayList<>(userList.size());

		for (User user : userList) {
			userDTOList.add(toDto(user));
		}

		return userDTOList;
	}

	@Override
	public User toEntity(UserDTO userDTO) {
		
		if (userDTO == null) {
			return null;
		}

		User user = modelMapper.map(userDTO, User.class);
		user.setInsertionTimestamp(userDTO.getInsertionTimestamp());
		user.setChangeTimestamp(userDTO.getChangeTimestamp());
		user.setCancellationTimestamp(userDTO.getCancellationTimestamp());
		
		return user;
	}

}
