package stingify.app.entity;

import java.sql.Timestamp;
import java.util.Date;

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
@Table(name="amounts")
public class Amount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer amountId;
	
	private Integer budgetBookId;

	private Integer creatorUserId;

	@ManyToOne(optional = true)
	@JoinColumn(name = "category_id")
	private Category category;

	private Date date;
	private String description;
	private Float planned;
	private Float actual;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "amount_type_id")
	private AmountType amountType;
	
	private Timestamp insertionTimestamp;
	private Timestamp changeTimestamp;
	private Timestamp cancellationTimestamp;
	
}
