package ru.adept.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.adept.entity.Preserve;

public interface PreserveRepository extends JpaRepository<Preserve, Long> {
}
