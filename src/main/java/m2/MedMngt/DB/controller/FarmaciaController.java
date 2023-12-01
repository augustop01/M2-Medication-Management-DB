package m2.MedMngt.DB.controller;

import m2.MedMngt.DB.dto.FarmaciaResponse;
import m2.MedMngt.DB.models.Farmacia;
import m2.MedMngt.DB.repository.FarmaciaRepository;
import m2.MedMngt.DB.service.FarmaciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

}
