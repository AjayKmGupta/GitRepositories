package ai.sahaj.snakeladder.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ai.sahaj.snakeladder.constants.SimulationMode;
import lombok.Data;

@Entity
@Data
public class Simulation {

	@Id
	private String id;
	private String name;
	private SimulationMode simulationMode;
	private int simulationCount;
	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	private Board board;
	@OneToMany(mappedBy = "simulation")
	private List<Roll> rolls;
	@JoinTable(name = "simulation_player", joinColumns = @JoinColumn(name = "simulation_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
	@ManyToMany
	private List<Player> players;
}
