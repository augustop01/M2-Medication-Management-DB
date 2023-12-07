package m2.MedMngt.DB.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class IdEstoque implements Serializable {
    private Long cnpj;
    private Integer nroRegistro;

//    public boolean seIguala(Object obj){
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        IdEstoque idEstoque = (IdEstoque) obj;
//        return Objects.equals(cnpj, idEstoque.cnpj) && Objects.equals(nroRegistro, idEstoque.nroRegistro);
//    }
//
//    @Override
//    public int hashCode(){
//        return Objects.hash(cnpj, nroRegistro);
//    }
}
