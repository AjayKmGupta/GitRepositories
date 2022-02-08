package ai.sahaj.snakeladder.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import ai.sahaj.snakeladder.constants.AccOrDeacceleratorType;
import lombok.Data;

@Data
@Entity
public class AccOrDeaccelerator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private AccOrDeacceleratorType accOrDeAccType;
	@OneToOne
	private Position startPos;
	@OneToOne
	private Position finalPosition;
	@ManyToMany(mappedBy = "accOrDeAccelerators")
	private List<Board> board;
}
