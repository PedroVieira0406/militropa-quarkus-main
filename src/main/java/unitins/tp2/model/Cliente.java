package unitins.tp2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente extends DefaultEntity {

    @Column(length = 60)
    private String nome;

    @Column(length = 20)
    private String cpf;

    @Column(length = 100)
    private String email;

    @Column(length = 10)
    private String numeroRegistroPossePorte;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @JoinTable(name = "cliente_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private Usuario usuario;

}
