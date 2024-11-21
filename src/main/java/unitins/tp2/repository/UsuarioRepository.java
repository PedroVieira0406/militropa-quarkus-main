package unitins.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.validation.ValidationException;
import unitins.tp2.model.Usuario;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    public PanacheQuery<Usuario> findByNome (String login) {
        if(login == null)
            return null;
        return find("UPPER(login) LIKE?1","%" + login.toUpperCase()+ "%") ;
    }

    public Usuario findByLogin(String login) {
        try {
            return find("login = ?1 ", login ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            return find("login = ?1 and senha = ?2", login, senha).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            throw new ValidationException("Login ou senha inválido");
        }
    }
}