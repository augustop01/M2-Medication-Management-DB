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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

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
       var estoques = estoqueService.consultar(cnpj);
            var result = new ArrayList<EstoqueResponse>();
            for (Estoque estoque : estoques){
                var estoqueDTO = mapper.map(estoque, EstoqueResponse.class);
                result.add(estoqueDTO);
            }
            return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<EstoqueResponse> adicionarMedicamento(@RequestBody EstoqueRequest estoqueRequest){
        var estoque = mapper.map(estoqueRequest, Estoque.class);
        if (estoque.getCnpj() == null || estoque.getNroRegistro() == null || estoque.getQuantidade() == null){
            throwBadRequest("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoqueRepository.findAllByCnpj(estoque.getCnpj()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o CNPJ informado no campo 'cnpj' não consta no banco de dados.");
        }
        if (estoqueRepository.findAllByNroRegistro(estoque.getNroRegistro()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o Número de Registro informado no campo 'nroRegistro' não consta no banco de dados.");
        }
        if (estoque.getQuantidade() <= 0){
            throwBadRequest("ERRO DE OPERAÇÃO: A quantidade informada no campo 'quantidade' deve ser maior que 0 (zero).");
        }
        Estoque getEstoque = estoqueRepository.findByCnpjAndNroRegistro(estoque.getCnpj(), estoque.getNroRegistro());
        if (getEstoque == null){
            estoque.setDataAtualizacao(LocalDateTime.now());
            estoque = estoqueService.salvar(estoque);
            var result = mapper.map(estoque, EstoqueResponse.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        getEstoque.setQuantidade(getEstoque.getQuantidade() + estoque.getQuantidade());
        getEstoque.setDataAtualizacao(LocalDateTime.now());
        getEstoque = estoqueService.salvar(getEstoque);
        var result = mapper.map(getEstoque, EstoqueResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping ()
    public ResponseEntity<EstoqueResponse> venderMedicamento(@RequestBody EstoqueRequest estoqueRequest){
        var requestEstoque = mapper.map(estoqueRequest, Estoque.class);
        if (requestEstoque.getCnpj() == null || requestEstoque.getNroRegistro() == null || requestEstoque.getQuantidade() == null){
            throwBadRequest("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoqueRepository.findAllByCnpj(requestEstoque.getCnpj()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o CNPJ informado no campo 'cnpj' não consta no banco de dados.");
        }
        if (estoqueRepository.findAllByNroRegistro(requestEstoque.getNroRegistro()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o Número de Registro informado no campo 'nroRegistro' não consta no banco de dados.");
        }
        if (requestEstoque.getQuantidade() <= 0){
            throwBadRequest("ERRO DE OPERAÇÃO: A quantidade informada no campo 'quantidade' deve ser maior que 0 (zero).");
        }
        Estoque estoqueAtual = estoqueRepository.findByCnpjAndNroRegistro(requestEstoque.getCnpj(), requestEstoque.getNroRegistro());
        if (estoqueAtual == null){
            throwBadRequest("ERRO DE OPERAÇÃO: Não consta o medicamento com número de registro " + requestEstoque.getNroRegistro() + " no estoque da farmácia com CNPJ " + requestEstoque.getCnpj() + ".");
        }
        if (estoqueAtual.getQuantidade() > requestEstoque.getQuantidade()){
            estoqueAtual.setQuantidade(estoqueAtual.getQuantidade() - requestEstoque.getQuantidade());
            estoqueAtual.setDataAtualizacao(LocalDateTime.now());
            estoqueAtual = estoqueService.salvar(estoqueAtual);
            var result = mapper.map(estoqueAtual, EstoqueResponse.class);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        estoqueAtual.setQuantidade(estoqueAtual.getQuantidade() - requestEstoque.getQuantidade());
        estoqueAtual.setDataAtualizacao(LocalDateTime.now());
        estoqueAtual = estoqueService.zerar(estoqueAtual);
        var result = mapper.map(estoqueAtual, EstoqueResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping ()
    public ResponseEntity<EstoqueResponse> transferirMedicamento(@RequestBody EstoqueRequest estoqueRequest){
        Estoque estoqueDestino = estoqueRepository.findByCnpjAndNroRegistro(estoqueRequest.getCnpjDestino(), estoqueRequest.getNroRegistro());
        Estoque estoqueOrigem = estoqueRepository.findByCnpjAndNroRegistro(estoqueRequest.getCnpjOrigem(), estoqueRequest.getNroRegistro());
        if (estoqueRequest.getCnpjOrigem() == null || estoqueRequest.getCnpjDestino() == null || estoqueRequest.getNroRegistro() == null || estoqueRequest.getQuantidade() == null){
            throwBadRequest("CAMPO OBRIGATÓRIO: Todos os campos ('cnpjOrigem', 'cnpjDestino, 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoqueRepository.findAllByCnpj(estoqueRequest.getCnpjOrigem()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o CNPJ informado no campo 'cnpjOrigem' não consta no banco de dados.");
        }
        if (estoqueRepository.findAllByCnpj(estoqueRequest.getCnpjDestino()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o CNPJ informado no campo 'cnpjDestino' não consta no banco de dados.");
        }
        if (estoqueRepository.findAllByNroRegistro(estoqueRequest.getNroRegistro()).isEmpty()){
            throwBadRequest("ERRO DE OPERAÇÃO: o Número de Registro informado no campo 'nroRegistro' não consta no banco de dados.");
        }
        if (estoqueRequest.getQuantidade() <= 0){
            throwBadRequest("ERRO DE OPERAÇÃO: A quantidade informada no campo 'quantidade' deve ser maior que 0 (zero).");
        }
        if (estoqueOrigem == null){
            throwBadRequest("ERRO DE OPERAÇÃO: Não consta o medicamento com número de registro " + estoqueRequest.getNroRegistro() + " no estoque da farmácia com CNPJ " + estoqueRequest.getCnpjOrigem() + ".");
        }
        if (estoqueDestino == null){
            estoqueDestino = new Estoque();
            estoqueDestino.setQuantidade(0);
            estoqueDestino.setCnpj(estoqueRequest.getCnpjDestino());
            estoqueDestino.setNroRegistro(estoqueRequest.getNroRegistro());
            estoqueDestino.setDataAtualizacao(LocalDateTime.now());
        }
        if (estoqueOrigem.getQuantidade() < estoqueRequest.getQuantidade()){
            throwBadRequest("ERRO DE OPERAÇÃO: A quantidade solicitada do medicamento de número de registro '" + estoqueRequest.getNroRegistro() + "' é maior que a quantidade disponível no estoque de origem.");
        }

        estoqueOrigem.setQuantidade(estoqueOrigem.getQuantidade() - estoqueRequest.getQuantidade());
        estoqueOrigem.setDataAtualizacao(LocalDateTime.now());
        estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + estoqueRequest.getQuantidade());
        estoqueDestino.setDataAtualizacao(LocalDateTime.now());
        estoqueService.salvar(estoqueDestino);
        estoqueService.salvar(estoqueOrigem);

        if (estoqueOrigem.getQuantidade() == 0){
            estoqueService.zerar(estoqueOrigem);
        }

        EstoqueResponse estoqueResponse = new EstoqueResponse();
        estoqueResponse.setNroRegistro(estoqueOrigem.getNroRegistro());
        estoqueResponse.setNomeMedicamento(estoqueOrigem.getMedicamento().getNome());
        estoqueResponse.setCnpjOrigem(estoqueOrigem.getCnpj());
        estoqueResponse.setQuantidadeOrigem(estoqueOrigem.getQuantidade());
        estoqueResponse.setCnpjDestino(estoqueDestino.getCnpj());
        estoqueResponse.setQuantidadeDestino(estoqueDestino.getQuantidade());
        estoqueResponse.setDataAtualizacao(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(estoqueResponse);
    }

    private void throwBadRequest(String msg){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
    }
}
