package unitins.tp2.service.funcionario;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp2.dto.funcionario.FuncionarioDTO;
import unitins.tp2.dto.funcionario.FuncionarioResponseDTO;
import unitins.tp2.model.Endereco;
import unitins.tp2.model.Funcionario;
import unitins.tp2.model.Usuario;
import unitins.tp2.repository.EnderecoRepository;
import unitins.tp2.repository.FuncionarioRepository;
import unitins.tp2.repository.UsuarioRepository;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository repository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public FuncionarioResponseDTO insert(FuncionarioDTO dto) {
        Funcionario insertFuncionario = new Funcionario();
        insertFuncionario.setNome(dto.nome());
        insertFuncionario.setCpf(dto.cpf());
        insertFuncionario.setEmail(dto.email());
        insertFuncionario.setMatricula(dto.matricula());
        insertFuncionario.setTelefone(dto.telefone());

        Endereco endereco = enderecoRepository.findById(dto.endereco());
        insertFuncionario.setEndereco(endereco);
        Usuario usuario = usuarioRepository.findById(dto.usuario());
        insertFuncionario.setUsuario(usuario);

        repository.persist(insertFuncionario);

        return FuncionarioResponseDTO.valueOf(insertFuncionario);

    }

    @Override
    @Transactional
    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id) {
        Funcionario funcionarioUpdate = repository.findById(id);
        if(funcionarioUpdate != null){

            funcionarioUpdate.setNome(dto.nome());
            funcionarioUpdate.setCpf(dto.cpf());
            funcionarioUpdate.setEmail(dto.email());
            funcionarioUpdate.setMatricula(dto.matricula());
            funcionarioUpdate.setTelefone(dto.telefone());

            //já veio assim, vou nem mexer
            Endereco endereco = enderecoRepository.findById(dto.endereco());
            funcionarioUpdate.setEndereco(endereco);
            Usuario usuario = usuarioRepository.findById(dto.usuario());
            funcionarioUpdate.setUsuario(usuario);

            repository.persist(funcionarioUpdate);
        }else
        throw new NotFoundException();

        return FuncionarioResponseDTO.valueOf(funcionarioUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        return FuncionarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(n -> FuncionarioResponseDTO.valueOf(n)).toList();
    }

@Override
    public List<FuncionarioResponseDTO> findAll(int page, int pageSize) {
        List<Funcionario> lista = repository
                                    .findAll()
                                    .page(page, pageSize)
                                    .list();

        return lista
            .stream()
            .map(e -> FuncionarioResponseDTO.valueOf(e))
            .toList();
    }

    @Override
    public List<FuncionarioResponseDTO> findByMatricula(String matricula) {
        return repository.findByMatricula(matricula).stream()
            .map(m -> FuncionarioResponseDTO.valueOf(m)).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public UsuarioResponseDTO login(String login, String senha) {
        Funcionario funcionario = repository.findByLoginAndSenha(login, senha);
        return UsuarioResponseDTO.valueOf(funcionario.getUsuario());
    }
}
