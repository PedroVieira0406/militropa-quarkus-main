package unitins.tp2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemPedido extends DefaultEntity {

    private Double desconto;
    
    @Column(nullable = false)
    private Integer quantidade;

    private Double subTotal;
    
    @ManyToOne
    @JoinColumn(name = "id_arma")
    private Arma arma;

}