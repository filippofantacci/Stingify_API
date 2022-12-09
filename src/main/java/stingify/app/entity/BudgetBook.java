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
@Table(name="budget_books")
public class BudgetBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer budgetBookId;
	private Integer creatorUserId;
	private String description;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
	private Float expenses;
	private Float incomings;
	private Float savings;
	
	
	/**
	 * @param budgetBookId
	 * @param creatorUserId
	 * @param description
	 * @param insertionTimestamp
	 * @param changeTimestamp
	 * @param cancellationTimestamp
	 * @param expenses
	 * @param incomings
	 * @param savings
	 */
	public BudgetBook(Integer budgetBookId, Integer creatorUserId, String description, Timestamp insertionTimestamp,
			Timestamp changeTimestamp, Timestamp cancellationTimestamp, Float expenses, Float incomings,
			Float savings) {
		super();
		this.budgetBookId = budgetBookId;
		this.creatorUserId = creatorUserId;
		this.description = description;
		this.insertionTimestamp = insertionTimestamp;
		this.changeTimestamp = changeTimestamp;
		this.cancellationTimestamp = cancellationTimestamp;
		this.expenses = expenses;
		this.incomings = incomings;
		this.savings = savings;
	}

	public BudgetBook(BudgetBook budgetBook, double expenses, double incomings,
			double savings) {
		super();
		this.budgetBookId = budgetBook.budgetBookId;
		this.creatorUserId = budgetBook.creatorUserId;
		this.description = budgetBook.description;
		this.insertionTimestamp = budgetBook.insertionTimestamp;
		this.changeTimestamp = budgetBook.changeTimestamp;
		this.cancellationTimestamp = budgetBook.cancellationTimestamp;
		this.expenses = new Float(expenses);
		this.incomings = new Float(incomings);
		this.savings = new Float(savings);
	}
	
	/**
	 * 
	 */
	public BudgetBook() {
		super();
	}
	

	
	
}
