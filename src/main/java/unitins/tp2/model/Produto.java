package unitins.tp2.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Produto extends DefaultEntity{
    
    @Column(length = 60)
    private String nome;

    @Column(length = 6)
    private int qtdNoEstoque;

    @Column(length = 15)
    private Double preco;

    @Column(length = 60)
    private String descricao;

}