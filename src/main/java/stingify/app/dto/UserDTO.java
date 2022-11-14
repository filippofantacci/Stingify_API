package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data

public class UserDTO {

	private Integer userId;
	private String authId;
	private String firstName;
	private String lastName;
	private String email;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;

}
