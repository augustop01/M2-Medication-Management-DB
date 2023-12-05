package m2.MedMngt.DB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "MEDICAMENTOS")
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @Column(nullable = false)
    private Integer nroRegistro;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String laboratorio;
    @Column(nullable = false)
    private String dosagem;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Float preco;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMedicamento tipo;
}

