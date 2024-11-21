package unitins.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Cliente;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public PanacheQuery<Cliente> findByNome (String nome) {
        if(nome == null)
            return null;
        return find("UPPER (nome) LIKE?1","%" + nome.toUpperCase()+ "%") ;
    }
    public Cliente findByCpf(String cpf) {
        return find("UPPER(cpf) LIKE ?1", "%" + cpf.toUpperCase() + "%").firstResult();
    }

    public Cliente findByEmail(String email){
        return find("UPPER(email) LIKE ?1", "%" + email.toUpperCase() + "%").firstResult();
    }

    public Cliente findByLoginAndSenha(String login, String senha) {
        return find("usuario.login = ?1 AND usuario.senha = ?2", login, senha).firstResult();
    }

    public Cliente findByLogin(String login) {
        return find("usuario.login", login).firstResult();
    }
}
