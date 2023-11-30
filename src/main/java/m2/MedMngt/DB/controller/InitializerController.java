package m2.MedMngt.DB.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import m2.MedMngt.DB.models.*;
import m2.MedMngt.DB.service.InitializerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/")
public class InitializerController {

   private InitializerService initializerService;

   public InitializerController(InitializerService initializerService){
       this.initializerService = initializerService;
   };

    Farmacia farmacia1 = new Farmacia(90561736000121L, "DevMed Ltda", "Farmácia DevMed", "devmed@farmacia.com", "(44)4444-4444", "(44)94444-4444", new Endereco(88888999L, "Rua Porto Real", 67, "Westeros", "Berlim", "SC", " ", 15.23456, 2.8678687));
    Farmacia farmacia2 = new Farmacia(43178995000198L, "MedHouse Ltda", "Farmácia MedHouse", "medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555", new Endereco(8877799L, "Rua Madrid", 76, "Winterfell", "Estocolmo", "SC", " ", 19.254356, 3.8987687));
    Medicamento medicamento1 = new Medicamento(1010, "Programapan", "Matrix", "2x ao dia", "Lorem impsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend.", 111.00f, TipoMedicamento.COMUM);
    Medicamento medicamento2 = new Medicamento(7473, "Cafex", "Colombia Farm", "4x ao dia", "Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh.", 51.50f, TipoMedicamento.COMUM);
    Medicamento medicamento3 = new Medicamento(2233, "Estomazol", "Acme", "1x ao dia", "Sed risus mauris, consectetur eget egestas vitae.", 22.50f, TipoMedicamento.COMUM);
    Medicamento medicamento4 = new Medicamento(8880, "Gelox", "Ice", "2x ao dia", "Quisque quam orci, vulputate sit amet.", 11.50f, TipoMedicamento.COMUM);
    Medicamento medicamento5 = new Medicamento(5656, "Aspirazol", "Acme", "3x ao dia", "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend.", 10.50f, TipoMedicamento.CONTROLADO);
    Medicamento medicamento6 = new Medicamento(4040, "Propolizol", "Bee", "5x ao dia", "Nunc euismod ipsum purus, sit amet finibus libero ultricies.", 10.50f, TipoMedicamento.CONTROLADO);
    Estoque estoque1 = new Estoque(90561736000121L, 1010, 12, LocalDateTime.now());
    Estoque estoque2 = new Estoque(90561736000121L, 7473, 10, LocalDateTime.now());
    Estoque estoque3 = new Estoque(43178995000198L, 7473, 2, LocalDateTime.now());
    Estoque estoque4 = new Estoque(43178995000198L, 2233, 15, LocalDateTime.now());
    Estoque estoque5 = new Estoque(43178995000198L, 8880, 16, LocalDateTime.now());
    Estoque estoque6 = new Estoque(43178995000198L, 4040, 22, LocalDateTime.now());

    @PostMapping("/inicializacao")
    public void inicializar() {
        initializerService.salvarFarmacia(farmacia1);
        initializerService.salvarFarmacia(farmacia2);
        initializerService.salvarMedicamento(medicamento1);
        initializerService.salvarMedicamento(medicamento2);
        initializerService.salvarMedicamento(medicamento3);
        initializerService.salvarMedicamento(medicamento4);
        initializerService.salvarMedicamento(medicamento5);
        initializerService.salvarMedicamento(medicamento6);
        initializerService.salvarEstoque(estoque1);
        initializerService.salvarEstoque(estoque2);
        initializerService.salvarEstoque(estoque3);
        initializerService.salvarEstoque(estoque4);
        initializerService.salvarEstoque(estoque5);
        initializerService.salvarEstoque(estoque6);
    }
}
