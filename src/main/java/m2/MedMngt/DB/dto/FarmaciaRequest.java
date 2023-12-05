package m2.MedMngt.DB.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import m2.MedMngt.DB.models.Endereco;

@Data
public class FarmaciaRequest {
    @NotNull(message = "O campo 'CNPJ' não pode estar em branco.")
    private Long cnpj;
    @NotBlank(message = "O campo 'razão social' não pode estar em branco.")
    private String razaoSocial;
    @NotBlank(message = "O campo 'nome fantasia' não pode estar em branco.")
    private String nomeFantasia;
    @NotBlank(message = "O campo 'e-mail' não pode estar em branco.")
    private String email;
    private String telefone;
    @NotBlank(message = "O campo 'celular' não pode estar em branco.")
    private String celular;
    @Valid
    private Endereco endereco;
}

