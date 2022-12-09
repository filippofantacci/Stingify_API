package stingify.app.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class BudgetBookDTO {

	private Integer budgetBookId;
	private Integer creatorUserId;
	private String description;
	private List<CategoryDTO> categories;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	private Float expenses;
	private Float incomings;
	private Float savings;
}
