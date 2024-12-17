package unitins.tp2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Entity
public abstract class FormaPagamento extends DefaultEntity{

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Boolean confirmacaoPagamento;

    private LocalDate dataPagamento;

    public FormaPagamento (Double valor) {
        this.valor = valor;
        this.confirmacaoPagamento = true;
        this.dataPagamento = LocalDate.now();
    }

    public FormaPagamento () {
        this.confirmacaoPagamento = false;
    }
}