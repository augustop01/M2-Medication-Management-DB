package m2.MedMngt.DB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import m2.MedMngt.DB.models.TipoMedicamento;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoResponse {
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    private TipoMedicamento tipo;

    public MedicamentoResponse(String nome){
        this.nome = nome;
    }
}