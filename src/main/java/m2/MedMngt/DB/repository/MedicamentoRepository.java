package m2.MedMngt.DB.repository;

import m2.MedMngt.DB.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

}

