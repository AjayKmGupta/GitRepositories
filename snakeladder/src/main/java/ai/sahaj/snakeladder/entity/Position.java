package ai.sahaj.snakeladder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int number;

	public Position(int number) {
		this.number = number;
	}

}
