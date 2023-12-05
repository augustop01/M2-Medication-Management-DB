package m2.MedMngt.DB.service;

import m2.MedMngt.DB.models.Medicamento;
import m2.MedMngt.DB.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    public List<Medicamento> consultar() {return medicamentoRepository.findAll();}
}