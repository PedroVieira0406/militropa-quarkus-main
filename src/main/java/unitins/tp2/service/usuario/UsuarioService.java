package unitins.tp2.service.usuario;

import java.util.List;

import unitins.tp2.dto.usuario.AlterarLoginUsuarioDTO;
import unitins.tp2.dto.usuario.AlterarSenhaUsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioDTO;
import unitins.tp2.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {

    public UsuarioResponseDTO insert(UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public void alterarSenha(AlterarSenhaUsuarioDTO dto);
    public void alterarLogin(AlterarLoginUsuarioDTO dto);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    public UsuarioResponseDTO findByLogin(String login);

    public UsuarioResponseDTO findMyUser();

    public long count();
    public long countByNome(String nome);
    
    public List<UsuarioResponseDTO> findAll(int page, int pageSize);

    public List<UsuarioResponseDTO> findByNome(int page, int pageSize, String nome);

}