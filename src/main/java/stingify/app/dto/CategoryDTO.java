package stingify.app.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CategoryDTO {

	private Integer categoryId;
	private Integer creatorUserId;
	private String description;

	private MacroCategoryDTO macroCategory;

	private AmountTypeDTO amountType;

	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;

	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", creatorUserId=" + creatorUserId + ", description="
				+ description + ", macroCategory="
				+ (macroCategory != null ? macroCategory.toStringIgnoreCategories() : "null") + ", amountType="
				+ amountType + ", insertionTimestamp=" + insertionTimestamp + ", changeTimestamp=" + changeTimestamp
				+ ", cancellationTimestamp=" + cancellationTimestamp + "]";
	}

	public String toStringIgnoreMacroCategory() {
		return "CategoryDTO [categoryId=" + categoryId + ", creatorUserId=" + creatorUserId + ", description="
				+ description + ", amountType=" + amountType + ", insertionTimestamp=" + insertionTimestamp
				+ ", changeTimestamp=" + changeTimestamp + ", cancellationTimestamp=" + cancellationTimestamp + "]";
	}
}
