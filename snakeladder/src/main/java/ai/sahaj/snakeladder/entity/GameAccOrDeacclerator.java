package ai.sahaj.snakeladder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class GameAccOrDeacclerator {

	@Id
	private String id;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private Game game;
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "acc_or_deacc_id", nullable = false)
	private AccOrDeaccelerator accOrDeacc;

}
