package stingify.app.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private Integer creatorUserId;
	private String description;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "macro_category_id")
	@JsonIgnoreProperties(value={"categories"}, allowSetters = true, allowGetters = true)
	private MacroCategory macroCategory;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "amount_type_id")
	private AmountType amountType;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", creatorUserId=" + creatorUserId + ", description="
				+ description + ", macroCategory=" + macroCategory.toStringIgnoreCategories() + ", insertionTimestamp=" + insertionTimestamp
				+ ", changeTimestamp=" + changeTimestamp + ", cancellationTimestamp=" + cancellationTimestamp + "]";
	}

	public String toStringIgnoreMacroCategory() {
		return "Category [categoryId=" + categoryId + ", creatorUserId=" + creatorUserId + ", description="
				+ description + ", insertionTimestamp=" + insertionTimestamp
				+ ", changeTimestamp=" + changeTimestamp + ", cancellationTimestamp=" + cancellationTimestamp + "]";
	}
}
