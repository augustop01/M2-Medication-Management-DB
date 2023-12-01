package m2.MedMngt.DB.controller;

import jakarta.validation.Valid;
import m2.MedMngt.DB.dto.FarmaciaRequest;
import m2.MedMngt.DB.dto.FarmaciaResponse;
import m2.MedMngt.DB.models.Farmacia;
import m2.MedMngt.DB.repository.FarmaciaRepository;
import m2.MedMngt.DB.service.FarmaciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/farmacias")
public class FarmaciaController {

    @Autowired
    FarmaciaRepository farmaciaRepository;

    @Autowired
    private FarmaciaService farmaciaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<FarmaciaResponse>> consultar(){
        var farmacias = farmaciaService.consultar();
        var result = new ArrayList<FarmaciaResponse>();
        for (Farmacia farmacia : farmacias){
            var farmaciaDTO = mapper.map(farmacia, FarmaciaResponse.class);
            result.add(farmaciaDTO);

        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<FarmaciaResponse> consultar(@PathVariable("cnpj") Long cnpj){
        var farmacia = farmaciaService.consultar(cnpj);
        var result = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<FarmaciaResponse> cadastrar(@RequestBody @Valid FarmaciaRequest farmaciaRequest){
        var farmacia = mapper.map(farmaciaRequest, Farmacia.class);
        farmacia = farmaciaService.cadastrar(farmacia);
        var result = mapper.map(farmacia, FarmaciaResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
