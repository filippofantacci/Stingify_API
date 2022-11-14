package stingify.app.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class AmountDTO {

	private Integer amountId;

	private Integer budgetBookId;

	private Integer creatorUserId;

	private CategoryDTO category;

	private Date date;
	private String description;
	private Float planned;
	private Float actual;

	private AmountTypeDTO amountType;

	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;

}
