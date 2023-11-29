package m2.MedMngt.DB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUES")
@IdClass(IdEstoque.class)
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    @Column(nullable = false)
    private Long cnpj;
    @Id
    @Column(nullable = false)
    private Integer nroRegistro;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;
}