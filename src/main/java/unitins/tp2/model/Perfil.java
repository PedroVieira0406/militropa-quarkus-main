package unitins.tp2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Perfil {

    USER(1, "User"),
    ADMIN(2, "Admin");

    private final Integer id;
    private final String descricao;

    Perfil(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Perfil valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido" + id);
    }
}