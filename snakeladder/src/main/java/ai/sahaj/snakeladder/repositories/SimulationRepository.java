package ai.sahaj.snakeladder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.sahaj.snakeladder.entity.Simulation;

public interface SimulationRepository
		extends
			JpaRepository<Simulation, String> {

}
