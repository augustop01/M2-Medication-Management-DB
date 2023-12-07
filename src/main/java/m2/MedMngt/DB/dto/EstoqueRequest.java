package m2.MedMngt.DB.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueRequest {
    private Long cnpj;
    private Long cnpjOrigem;
    private Long cnpjDestino;
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;}
