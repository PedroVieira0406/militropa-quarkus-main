package unitins.tp2.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido extends DefaultEntity {

    private LocalDateTime dataPedido;

    private Double valorTotal = 0.0;

    private Boolean ifPedidoFeito = false;


    @OneToOne
    @JoinColumn(name = "id_formaPagamento", unique = true)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itens;

}
