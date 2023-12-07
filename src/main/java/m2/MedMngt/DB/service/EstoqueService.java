package m2.MedMngt.DB.service;

import ch.qos.logback.core.net.SocketConnector;
import jakarta.validation.Valid;
import m2.MedMngt.DB.dto.EstoqueRequest;
import m2.MedMngt.DB.models.Estoque;
import m2.MedMngt.DB.models.IdEstoque;
import m2.MedMngt.DB.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstoqueService {
    @Autowired
    EstoqueRepository estoqueRepository;

    public List<Estoque> consultar(){
        List<Estoque> estoques = estoqueRepository.findAll();
        if (estoques.isEmpty()){
            throw new RuntimeException("Nenhum estoque foi encontrado.");
        }
        return estoques;
    }
    public List<Estoque> consultar(Long cnpj) {
        List<Estoque> estoques = estoqueRepository.findAllByCnpj(cnpj);
        if (estoques.isEmpty()){
            throw new RuntimeException("Nenhum estoque foi encontrado com esses parâmetros.");
        }
    return estoques;
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
    public Estoque salvar(Estoque estoque) {
        if (estoque.getCnpj() == null || estoque.getNroRegistro() == null || estoque.getQuantidade() == null){
           throw  new RuntimeException("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoqueRepository.findAllByCnpj(estoque.getCnpj()).isEmpty()){
            throw  new RuntimeException("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoqueRepository.findAllByNroRegistro(estoque.getNroRegistro()).isEmpty()){
            throw  new RuntimeException("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        if (estoque.getQuantidade() <= 0){
            throw  new RuntimeException("CAMPO OBRIGATÓRIO: Todos os campos ('cnpj', 'nroRegistro' e 'quantidade') são obrigatórios e devem ser informados.");
        }
        estoque = estoqueRepository.save(estoque);
        return estoque;
    }
}
