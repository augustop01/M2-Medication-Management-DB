package m2.MedMngt.DB.service;

import m2.MedMngt.DB.models.*;
import m2.MedMngt.DB.repository.EstoqueRepository;
import m2.MedMngt.DB.repository.FarmaciaRepository;
import m2.MedMngt.DB.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InitializerService {
    private final FarmaciaRepository farmaciaRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final EstoqueRepository estoqueRepository;

    public InitializerService(FarmaciaRepository farmaciaRepository, MedicamentoRepository medicamentoRepository, EstoqueRepository estoqueRepository){
        this.farmaciaRepository = farmaciaRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public void salvarFarmacia(Farmacia farmacia){
        farmaciaRepository.save(farmacia);
    }

    public void salvarMedicamento(Medicamento medicamento) {
        medicamentoRepository.save(medicamento);
    }

    public void salvarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }
}
