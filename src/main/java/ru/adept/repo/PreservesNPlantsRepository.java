package ru.adept.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.adept.entity.PreservesAndPlant;

public interface PreservesNPlantsRepository extends JpaRepository<PreservesAndPlant, Long> {
}
