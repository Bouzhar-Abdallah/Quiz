package bouzhar.quiz.demo.level;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LevelConfig {
    @Bean
    CommandLineRunner levelCommandLineRunner(LevelRepository levelRepository){
        return args -> {
            Level level1 = new Level(
                    "niveau 1",
                    4F,
                    2F
            );
            Level level2 = new Level(
                    "niveau 2",
                    8F,
                    4F
            );
            levelRepository.saveAll(List.of(level1,level2));

        };
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
