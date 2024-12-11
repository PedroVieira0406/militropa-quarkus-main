package unitins.tp2.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "arma")
public class Arma extends Produto{


    @Column
    private TipoArma tipoArma;
    @Column(length = 60)
    private String marca;
    @Column
    private String calibre;
    @Column(length = 60)
    private String comprimentoDoCano;
    @Column
    private int capacidadeDeTiro;
    @Column(length = 60)
    private String numeroSigma;
    @Column
    private String numeroDaArma;
    @Column(length = 60)
    private String modelo;
    @Column(name="registro_nacional_armas")
    private String rna;
    @Column
    private String nomeImagem;
    @ManyToMany
    @JoinTable (name="arma_acabamento",
    joinColumns= @JoinColumn(name="id_arma"),
    inverseJoinColumns = @JoinColumn(name="id_acabamento") )
    private List<Acabamento> listaAcabamento;


}
