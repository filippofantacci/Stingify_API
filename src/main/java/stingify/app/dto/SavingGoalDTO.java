package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SavingGoalDTO {

	private Integer savingGoalId;

	private Integer creatorUserId;

	private CategoryDTO category;

	private String description;

	private Float goal;

	private Float actual;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;

}
