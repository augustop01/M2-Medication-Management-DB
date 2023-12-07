package m2.MedMngt.DB.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long cnpj;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long cnpjOrigem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long cnpjDestino;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nomeMedicamento;
    private Integer nroRegistro;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantidade;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantidadeOrigem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantidadeDestino;
    @JsonFormat(pattern = "dd-MM-yyyy, HH'h'mm'm'ss's'")
    private LocalDateTime dataAtualizacao;

}
