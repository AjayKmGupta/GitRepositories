package ai.sahaj.snakeladder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import ai.sahaj.snakeladder.services.GameService;
import ai.sahaj.snakeladder.services.impl.AutoModeGameServiceImpl;
import ai.sahaj.snakeladder.services.impl.ManualModeGameServiceImpl;

@Configuration
public class BeanConfig {

	@Bean("autoModeGameService")
	@Primary
	public GameService autoGameService() {
		return new AutoModeGameServiceImpl();
	}

	@Bean("manualModeGameService")
	public GameService manualGameService() {
		return new ManualModeGameServiceImpl();
	}
}
