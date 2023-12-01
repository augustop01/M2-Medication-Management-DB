package m2.MedMngt.DB.dto;

import lombok.Data;
import m2.MedMngt.DB.models.Endereco;

@Data
public class FarmaciaResponse {
    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    private Endereco endereco;
}
