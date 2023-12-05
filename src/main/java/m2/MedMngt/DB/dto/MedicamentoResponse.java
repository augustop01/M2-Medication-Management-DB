package m2.MedMngt.DB.dto;

import lombok.Data;
import m2.MedMngt.DB.models.TipoMedicamento;

@Data
public class MedicamentoResponse {
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    private TipoMedicamento tipo;
}
