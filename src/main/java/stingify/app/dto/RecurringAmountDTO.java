package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RecurringAmountDTO {
	
	private Integer recurringAmountId;
	
	private Integer creatorUserId;

	private CategoryDTO category;

	private String description;
	private Float planned;
	private Float actual;
	
	private AmountTypeDTO amountType;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
}
