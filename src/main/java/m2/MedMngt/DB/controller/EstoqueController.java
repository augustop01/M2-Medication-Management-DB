package m2.MedMngt.DB.controller;

import jakarta.validation.Valid;
import m2.MedMngt.DB.dto.EstoqueRequest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/adicionar")
    public ResponseEntity<EstoqueResponse> adicionarMedicamento(@RequestBody EstoqueRequest estoqueRequest){
        var estoque = mapper.map(estoqueRequest, Estoque.class);
        if (estoque.getCnpj() == null || estoque.getNroRegistro() == null || estoque.getQuantidade() == null){
            return new ResponseEntity("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.", HttpStatus.BAD_REQUEST);
        }
        if (estoqueRepository.findAllByCnpj(estoque.getCnpj()).isEmpty()){
            return new ResponseEntity("ERRO DE OPERAÇÃO: o CNPJ informado no campo 'cnpj' não consta no banco de dados.", HttpStatus.BAD_REQUEST);
        }
        if (estoqueRepository.findAllByNroRegistro(estoque.getNroRegistro()).isEmpty()){
            return new ResponseEntity("ERRO DE OPERAÇÃO: o Número de Registro informado no campo 'nroRegistro' não consta no banco de dados.", HttpStatus.BAD_REQUEST);
        }
        if (estoque.getQuantidade() <= 0){
            return new ResponseEntity("ERRO DE OPERAÇÃO: A quantidade informada no campo 'quantidade' deve ser maior que 0 (zero).", HttpStatus.BAD_REQUEST);
        }
        Estoque getEstoque = estoqueRepository.findByCnpjAndNroRegistro(estoque.getCnpj(), estoque.getNroRegistro());
        if (getEstoque == null){
            estoque.setDataAtualizacao(LocalDateTime.now());
            estoqueService.salvar(estoque);
            getEstoque = estoqueRepository.findByCnpjAndNroRegistro(estoque.getCnpj(), estoque.getNroRegistro());
            var result = mapper.map(getEstoque, EstoqueResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        getEstoque.setQuantidade(getEstoque.getQuantidade() + estoque.getQuantidade());
        getEstoque.setDataAtualizacao(LocalDateTime.now());
        estoqueService.salvar(getEstoque);
        var result = mapper.map(getEstoque, EstoqueResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
