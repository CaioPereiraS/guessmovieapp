package io.guessmovie.guessmovieapp.configs;

import org.springframework.boot.CommandLineRunner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class SeedData implements CommandLineRunner {
    
    private final JdbcTemplate jdbcTemplate;
    private boolean hasSeeded = false;

    public SeedData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    private void seedDatabase() {
        if (!hasSeeded) {
            return;
        }
        try {
            ClassPathResource resource = new ClassPathResource("seed.sql");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            if (bytes.length == 0) {
                System.out.println("Seed data file is empty.");
                return;
            }
            String sql = new String(bytes, StandardCharsets.UTF_8);
            jdbcTemplate.execute(sql);
            System.out.println("Seed data successfully inserted into the database.");
        } catch (IOException e) {
            System.err.println("Failed to read seed.sql file: " + e.getMessage());
        }
    }
}