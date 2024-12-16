package unitins.tp2.service.usuario;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp2.dto.usuario.AlterarLoginUsuarioDTO;
import unitins.tp2.dto.usuario.AlterarSenhaUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;
import unitins.tp2.model.Perfil;
import unitins.tp2.model.Usuario;
import unitins.tp2.repository.UsuarioRepository;
import unitins.tp2.service.hash.HashService;
import unitins.tp2.validation.ValidationException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {

        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");

        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(dto.perfil()));

            repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {

        Usuario usuario = repository.findById(id);
        usuario.setLogin(dto.login());
        String hashSenha = hashService.getHashSenha(dto.senha());
        usuario.setSenha(hashSenha);
        usuario.setPerfil(Perfil.valueOf(dto.perfil()));

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void alterarSenha(AlterarSenhaUsuarioDTO dto) {
        Usuario usuario = repository.findById(Long.valueOf(jwt.getClaim("id").toString()));

        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        repository.persist(usuario);
    }

    @Override
    @Transactional
    public void alterarLogin(AlterarLoginUsuarioDTO dto) {

        Usuario usuario = repository.findById(Long.valueOf(jwt.getClaim("id").toString()));

        usuario.setLogin(dto.login());
        repository.persist(usuario);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = repository.findById(id);

        if(usuario != null){
            repository.delete(usuario);
        }else {
            throw new NotFoundException("Usuário não encontrado!");
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String login) {
        List<Usuario> usuarios = repository.find("UPPER(login) LIKE UPPER(?1)", "%" + login + "%").list();
        // Converte a lista de usuários para uma lista de DTOs de resposta
        return usuarios.stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<UsuarioResponseDTO> findAll(int page, int pageSize) {
        List<Usuario> lista = repository
                                    .findAll()
                                    .page(page, pageSize)
                                    .list();

        return lista
            .stream()
            .map(e -> UsuarioResponseDTO.valueOf(e))
            .toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        try {
            Usuario usuario = repository.findByLoginAndSenha(login, senha);
            if (usuario == null) {
                throw new ValidationException("login", "Login ou senha inválido");
            }
            return UsuarioResponseDTO.valueOf(usuario);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir a pilha de exceção no console
            throw new ValidationException("login", "Ocorreu um erro durante a autenticação. Consulte os logs para obter mais informações.");
        }
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null)
        throw new ValidationException("login", "Login inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findMyUser() {
        // Obtendo o login pelo token jwt
        String loginUsuarioLogado = jwt.getSubject();

        // Verificando se o usuário logado está tentando atualizar o próprio perfil
        Usuario usuarioLogado = repository.findByLogin(loginUsuarioLogado);
        return UsuarioResponseDTO.valueOf(usuarioLogado);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countByNome(String nome) {
        return repository.findByNome(nome).count();
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(int page, int pageSize, String login){
        List<Usuario> usuarios = repository
                                            .findByNome(login)
                                            .page(page, pageSize)
                                            .list();

        // Converte a lista de usuários para uma lista de DTOs de resposta
        return usuarios
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .toList();
    }

}
