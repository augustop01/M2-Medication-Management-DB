package m2.MedMngt.DB.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EstoqueRequest {
    @NotNull(message = "Um CNPJ válido deve ser fornecido.")
    private Long cnpj;
    @NotNull(message = "Um nº de registro válido deve ser fornecido.")
    private Integer nroRegistro;
    @NotNull(message = "Uma quantidade válida deve ser fornecida.")
    private Integer quantidade;
    @NotNull(message = "Uma data válida deve ser fornecida.")
    private LocalDateTime dataAtualizacao;}
