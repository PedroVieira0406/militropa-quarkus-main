package unitins.tp2.service.funcionario;

import java.util.List;

import unitins.tp2.dto.funcionario.FuncionarioDTO;
import unitins.tp2.dto.funcionario.FuncionarioResponseDTO;

import unitins.tp2.dto.usuario.UsuarioResponseDTO;


public interface FuncionarioService {
        public FuncionarioResponseDTO insert(FuncionarioDTO dto);

    public FuncionarioResponseDTO update(FuncionarioDTO dto, Long id);

    public void delete(Long id);

    public FuncionarioResponseDTO findById(Long id);

    public List<FuncionarioResponseDTO> findByNome(String nome);

    public List<FuncionarioResponseDTO> findAll(int page, int pageSize); 

    public List<FuncionarioResponseDTO> findByMatricula(String matricula);

    public long count();

    public UsuarioResponseDTO login(String login, String senha);  
}
