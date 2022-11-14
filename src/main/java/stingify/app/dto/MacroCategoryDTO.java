package stingify.app.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data

public class MacroCategoryDTO {

	private Integer macroCategoryId;

	private Integer creatorUserId;
	private String description;

	private List<CategoryDTO> categories;

	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("MacroCategoryDTO [macroCategoryId=" + macroCategoryId
				+ ", creatorUserId=" + creatorUserId + ", description=" + description + ", categories= [");
		for (CategoryDTO categoryDTO : categories) {
			str.append(categoryDTO.toStringIgnoreMacroCategory());
		}
		str.append(" ] ");
		str.append(", insertionTimestamp=" + insertionTimestamp + ", changeTimestamp=" + changeTimestamp
				+ ", cancellationTimestamp=" + cancellationTimestamp + "]");
		return str.toString();
	}

	public String toStringIgnoreCategories() {
		return "MacroCategoryDTO [macroCategoryId=" + macroCategoryId + ", creatorUserId=" + creatorUserId
				+ ", description=" + description + ", insertionTimestamp="
				+ insertionTimestamp + ", changeTimestamp=" + changeTimestamp + ", cancellationTimestamp="
				+ cancellationTimestamp + "]";
	}

}
