package unitins.tp2.service.cliente;

import java.util.List;

import unitins.tp2.dto.cliente.ClienteCadastroDTO;
import unitins.tp2.dto.cliente.ClienteDTO;
import unitins.tp2.dto.cliente.ClienteResponseDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;

public interface ClienteService {

    public ClienteResponseDTO insert(ClienteDTO dto);
    public ClienteResponseDTO insertCadastro(ClienteCadastroDTO dto);

    public ClienteResponseDTO update(ClienteDTO dto, Long id);

    public void delete(Long id);

    public ClienteResponseDTO findById(Long id);

    public ClienteResponseDTO findByUsuario(String login);

    public List<ClienteResponseDTO> findByNome(String nome);

    public List<ClienteResponseDTO> findAll(int page, int pageSize);

    public long count();

    public UsuarioResponseDTO login(String login, String senha);
}