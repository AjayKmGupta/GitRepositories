package ai.sahaj.snakeladder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ai.sahaj.snakeladder.constants.RollType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Roll {

	@Id
	private String id;
	private RollType rollType;
	private int noOfRolls;
	private int faceValue;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;

	public Roll(String id) {
		this.id = id;
	}

}
