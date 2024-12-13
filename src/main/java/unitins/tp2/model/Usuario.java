package unitins.tp2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity {
    @Column(length = 60)
    private String login;
    private String senha;

    //@Enumerated(EnumType.ORDINAL)
    private Perfil perfil;
}
