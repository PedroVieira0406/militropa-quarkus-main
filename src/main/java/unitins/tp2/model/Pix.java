package unitins.tp2.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
@Entity
public class Pix extends FormaPagamento {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    private LocalDate dataExpiracaoTokenPix;
    
    public Pix (Double valor, String nome, String cpf) {
        super(valor);
        this.nome = nome;
        this.cpf = cpf;
        this.dataExpiracaoTokenPix = LocalDate.now().plusDays(1);
    }
    
    public Pix () {
        
    }
        
    public LocalDate getDataExpiracaoTokenPix() {
        return dataExpiracaoTokenPix;
    }

    public void setDataExpiracaoTokenPix(LocalDate dataExpiracaoTokenPix) {
        this.dataExpiracaoTokenPix = dataExpiracaoTokenPix;
    }

}