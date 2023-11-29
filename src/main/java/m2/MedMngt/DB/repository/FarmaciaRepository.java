package m2.MedMngt.DB.repository;

import m2.MedMngt.DB.models.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmaciaRepository extends JpaRepository<Farmacia, Long> {
}
