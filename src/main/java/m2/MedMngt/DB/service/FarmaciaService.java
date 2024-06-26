package m2.MedMngt.DB.service;

import m2.MedMngt.DB.models.Farmacia;
import m2.MedMngt.DB.repository.FarmaciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class FarmaciaService {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public List<Farmacia> consultar(){
        return farmaciaRepository.findAll();
    }

    public Farmacia consultar(Long cnpj) throws ResponseStatusException {
            return this.farmaciaRepository.findById(cnpj)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrada nenhuma farmácia com o CNPJ " + cnpj + "."));
    }

    public Farmacia cadastrar(Farmacia farmacia){
        boolean jaCadastrada = farmaciaRepository.existsById(farmacia.getCnpj());
        if (jaCadastrada){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO DE OPERAÇÃO: Já existe uma farmácia com esse CNPJ.");
        }
        farmacia = farmaciaRepository.save(farmacia);
        return farmacia;
    }

}
