package ai.sahaj.snakeladder.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
public class Game {

	@Id
	private String id;
	@ToString.Exclude
	@JoinColumn(name = "simulation_id", nullable = false)
	@ManyToOne
	private Simulation simulation;
	@ToString.Exclude
	@JoinColumn(name = "player_id", nullable = false)
	@ManyToOne
	private Player winner;
	@OneToMany(mappedBy = "game")
	@ToString.Exclude
	private List<Roll> rolls;
	@ToString.Exclude
	@OneToMany(mappedBy = "game")
	private List<GameAccOrDeacclerator> gameAccOrDeacc;
}
