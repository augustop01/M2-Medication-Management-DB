package m2.MedMngt.DB.controller;

import m2.MedMngt.DB.dto.EstoqueResponse;
import m2.MedMngt.DB.dto.FarmaciaResponse;
import m2.MedMngt.DB.models.Estoque;
import m2.MedMngt.DB.models.Farmacia;
import m2.MedMngt.DB.models.Medicamento;
import m2.MedMngt.DB.repository.EstoqueRepository;
import m2.MedMngt.DB.repository.MedicamentoRepository;
import m2.MedMngt.DB.service.EstoqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    EstoqueService estoqueService;

    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstoqueResponse>> consultar(){
        var estoques = estoqueService.consultar();
        var result = new ArrayList<EstoqueResponse>();
        for (Estoque estoque : estoques){
            var estoqueDTO = mapper.map(estoque, EstoqueResponse.class);
            result.add(estoqueDTO);

        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<List<EstoqueResponse>> consultar(@PathVariable("cnpj") Long cnpj){
        List<Estoque> estoques = estoqueRepository.findAllByCnpj(cnpj);

        if (!estoques.isEmpty()){
            var result = new ArrayList<EstoqueResponse>();
            for (Estoque estoque : estoques){
                var estoqueDTO = mapper.map(estoque, EstoqueResponse.class);
                result.add(estoqueDTO);
            }
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
