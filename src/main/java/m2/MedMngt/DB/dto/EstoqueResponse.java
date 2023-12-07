package m2.MedMngt.DB.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2.MedMngt.DB.models.Medicamento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueResponse {
    private Long cnpj;
    private String nomeMedicamento;
    private Integer nroRegistro;
    private Integer quantidade;
    @JsonFormat(pattern = "dd-MM-yyyy, HH'h'mm'm'ss's'")
    private LocalDateTime dataAtualizacao;

}
