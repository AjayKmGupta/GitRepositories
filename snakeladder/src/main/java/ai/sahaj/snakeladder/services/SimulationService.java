package ai.sahaj.snakeladder.services;

import java.util.Optional;
import java.util.Set;

import ai.sahaj.snakeladder.dto.frontend.AddSimulationDto;
import ai.sahaj.snakeladder.dto.frontend.SimulationDto;
import ai.sahaj.snakeladder.entity.Simulation;

public interface SimulationService {

	void addSimulation(AddSimulationDto simulation);

	void updateSimulation(String simulationId, AddSimulationDto simulation);

	SimulationDto getSimulation(String simulationId);
	
	Optional<Simulation> getSimulationEntity(String simulationId);

	String startAutomatedSimulation(String simulationId);

	Set<SimulationDto> getSimulation();
}
