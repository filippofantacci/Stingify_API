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
@Table(name = "users_budget_books")
public class UserBudgetBooks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usersBudgetBookId;
	private Integer userId;
	private Integer budgetBookId;
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
}
