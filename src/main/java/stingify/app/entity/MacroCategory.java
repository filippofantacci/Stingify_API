package stingify.app.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="macro_categories")
public class MacroCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer macroCategoryId;
					
	private Integer creatorUserId;
	private String description;
	
	@OneToMany(mappedBy = "macroCategory")
	@JsonIgnoreProperties("macroCategory")
	private List<Category> categories;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("MacroCategory [macroCategoryId=" + macroCategoryId
				+ ", creatorUserId=" + creatorUserId + ", description=" + description + ", categories= [");
		for (Category category : categories) {
			str.append(category.toStringIgnoreMacroCategory());
		}
		str.append(" ] ");
		str.append(", insertionTimestamp=" + insertionTimestamp + ", changeTimestamp=" + changeTimestamp
				+ ", cancellationTimestamp=" + cancellationTimestamp + "]");
		return str.toString();
	}

	public String toStringIgnoreCategories() {
		return "MacroCategory [macroCategoryId=" + macroCategoryId + ", creatorUserId=" + creatorUserId
				+ ", description=" + description + ", insertionTimestamp="
				+ insertionTimestamp + ", changeTimestamp=" + changeTimestamp + ", cancellationTimestamp="
				+ cancellationTimestamp + "]";
	}
}
