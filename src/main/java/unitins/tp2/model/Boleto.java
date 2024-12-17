package unitins.tp2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@Entity
public class Boleto extends FormaPagamento {

    private String nome;

    private String cpf;

    @Column(nullable = false)
    private LocalDate dataGeracaoBoleto;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    public Boleto (Double valor, String nome, String cpf) {

        super(valor);

        this.nome = nome;
        this.cpf = cpf;
        this.dataGeracaoBoleto = LocalDate.now();
        this.dataVencimento = LocalDate.now().plusDays(7);
    }

    public Boleto() {
        
    }
}