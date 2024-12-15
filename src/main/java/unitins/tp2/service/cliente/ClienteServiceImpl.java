package unitins.tp2.service.cliente;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp2.dto.cliente.ClienteDTO;
import unitins.tp2.dto.cliente.ClienteResponseDTO;
import unitins.tp2.model.Cliente;
import unitins.tp2.model.Endereco;
import unitins.tp2.model.Perfil;
import unitins.tp2.model.Usuario;
import unitins.tp2.repository.ClienteRepository;
import unitins.tp2.repository.EnderecoRepository;
import unitins.tp2.repository.UsuarioRepository;
import unitins.tp2.service.hash.HashService;
import unitins.tp2.validation.ValidationException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    public HashService hashService;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public ClienteResponseDTO insert(ClienteDTO dto) {
        validarEmailCliente(dto.email());
        validarCpfCliente(dto.cpf());
        validarLoginCliente(dto.login());

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.nome());
        novoCliente.setCpf(dto.cpf());
        novoCliente.setEmail(dto.email());
        novoCliente.setNumeroRegistro_posse_porte(dto.registro());

        Endereco endereco = new Endereco();
        endereco.setNome(dto.enderecoNome());
        endereco.setLogradouro(dto.enderecoLogradouro());
        endereco.setNumero(dto.enderecoNumero());
        endereco.setBairro(dto.enderecoBairro());
        endereco.setCep(dto.enderecoCep());
        endereco.setComplemento(dto.enderecoComplemento());
        endereco.setCidade(dto.enderecoCidade());
        endereco.setEstado(dto.enderecoEstado());
        novoCliente.setEndereco(endereco);

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.login());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setPerfil(Perfil.USER);

        usuarioRepository.persist(usuario);
        novoCliente.setUsuario(usuario);

        clienteRepository.persist(novoCliente);

        return ClienteResponseDTO.valueOf(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(ClienteDTO dto, Long id) {
        Cliente clienteUpdate = clienteRepository.findById(id);
        if (clienteUpdate == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        // Atualizando os campos do cliente
        clienteUpdate.setNome(dto.nome());
        clienteUpdate.setCpf(dto.cpf());
        clienteUpdate.setEmail(dto.email());
        clienteUpdate.setNumeroRegistro_posse_porte(dto.registro());

        // Persistindo as alterações no banco de dados
        clienteRepository.persist(clienteUpdate);
        return ClienteResponseDTO.valueOf(clienteUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!clienteRepository.deleteById(id))
            throw new NotFoundException();
    }

    public void validarEmailCliente(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente != null) {
            throw new ValidationException("email", "O email " + email + " já foi cadastrado");
        }
    }

    public void validarCpfCliente(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            throw new ValidationException("cpf", "O cpf " + cpf + " ja foi cadastrado");
        }
    }

    public void validarLoginCliente(String login) {
        Cliente cliente = clienteRepository.findByLogin(login);
        if (cliente != null) {
            throw new ValidationException("Login", "O Login " + login + " já foi cadastrado");
        }
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        return ClienteResponseDTO.valueOf(clienteRepository.findById(id));
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return clienteRepository.findByNome(nome).stream()
                .map(e -> ClienteResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<ClienteResponseDTO> findAll(int page, int pageSize) {
        List<Cliente> lista = clienteRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return lista
                .stream()
                .map(e -> ClienteResponseDTO.valueOf(e))
                .toList();
    }

    @Override
    public ClienteResponseDTO findByUsuario(String login) {
        return ClienteResponseDTO.valueOf(clienteRepository.findByLogin(login));
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }
}