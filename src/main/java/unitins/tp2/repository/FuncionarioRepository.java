package unitins.tp2.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.tp2.model.Funcionario;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
public PanacheQuery<Funcionario> findByNome (String nome) {
        if(nome == null)
            return null;
        return find("UPPER (nome) LIKE?1","%" + nome.toUpperCase()+ "%") ;
    }

    public List<Funcionario> findByMatricula(String matricula) {
        return find("UPPER(matricula) LIKE UPPER(?1) ", "%" + matricula + "%").list();
    }
}
