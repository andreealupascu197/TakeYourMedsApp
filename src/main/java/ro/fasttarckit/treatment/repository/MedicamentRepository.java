package ro.fasttarckit.treatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttarckit.treatment.domain.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
}
