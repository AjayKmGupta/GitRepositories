package ai.sahaj.snakeladder.dto.backend;

import lombok.Data;

@Data
public class Dice {

	public static final int MIN_VAL = 1;
	public static final int MAX_VAL = 6;
	private int faceValue;

	public Dice(int faceValue) {
		if (faceValue >= MIN_VAL && faceValue <= MAX_VAL)
			this.faceValue = faceValue;
	}

}
