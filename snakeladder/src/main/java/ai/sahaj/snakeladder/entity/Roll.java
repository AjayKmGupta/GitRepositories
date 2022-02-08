package ai.sahaj.snakeladder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Roll {

	@Id
	private String id;
	private int noOfRolls;
	private int faceValue;
	@ManyToOne
	@JoinColumn(name = "simulation_id", nullable = false)
	private Simulation simulation;

	public Roll(String id) {
		this.id = id;
	}

}
