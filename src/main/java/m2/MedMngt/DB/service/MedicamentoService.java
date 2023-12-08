package m2.MedMngt.DB.service;

import m2.MedMngt.DB.dto.MedicamentoRequest;
import m2.MedMngt.DB.dto.MedicamentoResponse;
import m2.MedMngt.DB.models.Medicamento;
import m2.MedMngt.DB.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    MedicamentoRepository medicamentoRepository;

    public List<Medicamento> consultar() {return medicamentoRepository.findAll();}

    public Medicamento incluir(Medicamento medicamento, @RequestBody MedicamentoRequest request){
        boolean jaIncluso = medicamentoRepository.existsById(medicamento.getNroRegistro());
        if (jaIncluso){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já consta no banco de dados um medicamento com o nº. de registro " + request.getNroRegistro() + '.');
        }
        medicamento = medicamentoRepository.save(medicamento);
        return medicamento;
    }
}