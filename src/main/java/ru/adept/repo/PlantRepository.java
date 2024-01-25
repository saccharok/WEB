package ru.adept.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.adept.entity.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    Plant findByName(String name);
}
