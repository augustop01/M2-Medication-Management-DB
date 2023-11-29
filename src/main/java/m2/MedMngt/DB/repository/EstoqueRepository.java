package m2.MedMngt.DB.repository;

import m2.MedMngt.DB.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}

