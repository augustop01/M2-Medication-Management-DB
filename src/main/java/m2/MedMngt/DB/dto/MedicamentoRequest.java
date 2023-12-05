package m2.MedMngt.DB.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import m2.MedMngt.DB.models.TipoMedicamento;

@Data
public class MedicamentoRequest {
    @NotNull(message = "O número de registro deve ser fornecido.")
    private Integer nroRegistro;
    @NotBlank(message = "O nome do medicamento não pode estar em branco.")
    private String nome;
    @NotBlank(message = "O laboratório não pode estar em branco.")
    private String laboratorio;
    @NotBlank(message = "A dosagem do medicamento não foi informada.")
    private String dosagem;
    private String descricao;
    @NotNull(message = "O preço do medicamento não foi informado.")
    private Float preco;
    @NotBlank(message = "O tipo do medicamento ('COMUM' ou 'CONTROLADO') não foi informado.")
    private TipoMedicamento tipo;
}