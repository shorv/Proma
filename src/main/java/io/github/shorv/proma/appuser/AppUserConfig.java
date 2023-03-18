package io.github.shorv.proma.appuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppUserConfig {

    @Bean
    public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
        return args -> {
            List<AppUser> users = List.of(
                    new AppUser("Krzysio123", "krzyss", "Krzysztof", "Nowak", "krzysio.nowak@gmail.com"),
                    new AppUser("Stasio123", "sass", "Stanisław", "Kowal", "stanislaw.kowalll123@gmail.com"));
            appUserRepository.saveAll(users);
        };
    }
}
