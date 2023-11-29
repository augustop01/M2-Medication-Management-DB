package m2.MedMngt.DB.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class IdEstoque implements Serializable {
    private Long cnpj;
    private Integer nroRegistro;
}
