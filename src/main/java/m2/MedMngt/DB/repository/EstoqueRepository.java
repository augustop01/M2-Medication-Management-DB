package m2.MedMngt.DB.repository;

import m2.MedMngt.DB.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findAllByCnpj(Long cnpj);
    Estoque findByCnpj(Long cnpj);
    Estoque findByNroRegistro(Integer nroRegistro);
    Estoque findByCnpjAndNroRegistro(Long cnpj, Integer nroRegistro);
}

