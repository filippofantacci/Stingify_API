package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BudgetBooksCategoriesDTO {

	private Integer budgetBooksCategoriesId;
	private Integer budgetBookId;
	private Integer categoryId;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
}
