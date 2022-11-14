package stingify.app.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "budget_books_categories")
public class BudgetBooksCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer budgetBooksCategoriesId;
	private Integer budgetBookId;
	private Integer categoryId;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
}
