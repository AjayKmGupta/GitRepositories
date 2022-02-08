package ai.sahaj.snakeladder.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToOne
	private Position startPos;
	@OneToOne
	private Position finalPos;
	@JoinTable(name = "board_accOrDeacc", joinColumns = @JoinColumn(name = "board_id"), inverseJoinColumns = @JoinColumn(name = "accOrDeacc_id"))
	@ManyToMany
	private List<AccOrDeaccelerator> accOrDeAccelerators;
	@OneToMany(mappedBy = "board")
	private List<Simulation> simulation;
}
