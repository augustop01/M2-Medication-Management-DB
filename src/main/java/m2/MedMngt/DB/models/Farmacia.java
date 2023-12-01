package m2.MedMngt.DB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "FARMACIAS")
@NoArgsConstructor
@AllArgsConstructor
public class Farmacia{
    @Id
    @Column(nullable = false, unique = true)
    private Long cnpj;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false)
    private String nomeFantasia;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    private String celular;
    @Embedded
    private Endereco endereco;
}
