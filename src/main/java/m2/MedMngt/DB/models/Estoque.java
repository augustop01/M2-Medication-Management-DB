package m2.MedMngt.DB.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ESTOQUES")
@IdClass(IdEstoque.class)
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    private Long cnpj;
    @Id
    private Integer nroRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nroRegistro", insertable = false, updatable = false)
    private Medicamento medicamento;

    public Estoque(Long cnpj, Integer nroRegistro, Integer quantidade, LocalDateTime dataAtualizacao) {
        this.cnpj = cnpj;
        this.nroRegistro = nroRegistro;
        this.quantidade = quantidade;
        this.dataAtualizacao = dataAtualizacao;
    }
}