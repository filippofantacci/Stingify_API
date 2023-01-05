package stingify.app.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "saving_goals")
public class SavingGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer savingGoalId;

	private Integer creatorUserId;

	@ManyToOne(optional = true)
	@JoinColumn(name = "category_id")
	private Category category;

	private String description;
	
	private Float goal;

	private Float actual;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
	/**
	 * @param savingGoalId
	 * @param creatorUserId
	 * @param category
	 * @param description
	 * @param goal
	 * @param insertionTimestamp
	 * @param changeTimestamp
	 * @param cancellationTimestamp
	 */
	public SavingGoal(Integer savingGoalId, Integer creatorUserId, Category category, String description, Float goal, Float actual,
			Timestamp insertionTimestamp, Timestamp changeTimestamp, Timestamp cancellationTimestamp) {
		super();
		this.savingGoalId = savingGoalId;
		this.creatorUserId = creatorUserId;
		this.category = category;
		this.description = description;
		this.goal = goal;
		this.actual = actual;
		this.insertionTimestamp = insertionTimestamp;
		this.changeTimestamp = changeTimestamp;
		this.cancellationTimestamp = cancellationTimestamp;
	}
	
	public SavingGoal(SavingGoal savingGoal, double actual) {
		super();
		this.savingGoalId = savingGoal.savingGoalId;
		this.creatorUserId = savingGoal.creatorUserId;
		this.category = savingGoal.category;
		this.description = savingGoal.description;
		this.goal = savingGoal.goal;
		this.actual = new Float(actual);
		this.insertionTimestamp = savingGoal.insertionTimestamp;
		this.changeTimestamp = savingGoal.changeTimestamp;
		this.cancellationTimestamp = savingGoal.cancellationTimestamp;
	}
	
	public SavingGoal() {
		
	}

}
