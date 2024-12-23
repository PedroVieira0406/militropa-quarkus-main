package unitins.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Endereco;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    public PanacheQuery<Endereco> findByNome (String nome) {
        if(nome == null)
            return null;
        return find("UPPER (nome) LIKE?1","%" + nome.toUpperCase()+ "%") ;
    }
    
}