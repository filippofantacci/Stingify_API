package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserBudgetBooksDTO {
	
	private Integer usersBudgetBookId;
	private Integer userId;
	private Integer budgetBookId;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
}
