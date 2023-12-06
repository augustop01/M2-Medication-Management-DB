package m2.MedMngt.DB.service;

import ch.qos.logback.core.net.SocketConnector;
import m2.MedMngt.DB.models.Estoque;
import m2.MedMngt.DB.models.IdEstoque;
import m2.MedMngt.DB.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}}
