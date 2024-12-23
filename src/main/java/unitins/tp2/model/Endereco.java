package unitins.tp2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco extends DefaultEntity {
    
    @Column(length = 60)
    private String nome;
    @Column(length = 60)
    private String logradouro;
    @Column(length = 10)
    private String numero;
    @Column(length = 50)
    private String complemento;
    @Column(length = 120)
    private String bairro;
    @Column(length = 9)
    private String cep;
    @Column(length = 60)
    private String cidade;
    @Column(length = 60)
    private String estado;

}
