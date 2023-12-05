package m2.MedMngt.DB.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco{
    @NotNull(message = "O campo 'CEP' é obrigatório e deve ser preenchido corretamente.")
    private Long cep;
    @NotBlank(message = "O campo 'logradouro' deve ser preenchido.")
    private String logradouro;
    @NotNull(message = "O campo 'número' é obrigatório e deve ser preenchido corretamente.")
    private Integer numero;
    @NotBlank(message = "O campo 'bairro' deve ser preenchido.")
    private String bairro;
    @NotBlank(message = "O campo 'cidade' deve ser preenchido.")
    private String cidade;
    @NotBlank(message = "O campo 'estado' deve ser preenchido.")
    private String estado;
    private String complemento;
    @NotNull(message = "O campo 'latitude' é obrigatório e deve ser preenchido corretamente.")
    private Double latitude;
    @NotNull(message = "O campo 'longitude' é obrigatório e deve ser preenchido corretamente.")
    private Double longitude;
}
