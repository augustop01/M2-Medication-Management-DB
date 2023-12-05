package m2.MedMngt.DB.controller;

import m2.MedMngt.DB.dto.MedicamentoResponse;
import m2.MedMngt.DB.models.Medicamento;
import m2.MedMngt.DB.repository.MedicamentoRepository;
import m2.MedMngt.DB.service.MedicamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/medicamentos")
public class MedicamentoController {
    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    MedicamentoService medicamentoService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MedicamentoResponse>> consultar() {
        var medicamentos = medicamentoService.consultar();
        var result = new ArrayList<MedicamentoResponse>();
        for (Medicamento medicamento : medicamentos){
            var medicamentoDTO = mapper.map(medicamento, MedicamentoResponse.class);
            result.add(medicamentoDTO);
        }
        return ResponseEntity.ok(result);
    }
}