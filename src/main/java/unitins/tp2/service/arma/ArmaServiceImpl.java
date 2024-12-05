package unitins.tp2.service.arma;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import unitins.tp2.dto.arma.ArmaDTO;
import unitins.tp2.dto.arma.ArmaResponseDTO;
import unitins.tp2.model.Acabamento;
import unitins.tp2.model.Arma;
import unitins.tp2.model.TipoArma;
import unitins.tp2.repository.AcabamentoRepository;
import unitins.tp2.repository.ArmaRepository;

@ApplicationScoped
public class ArmaServiceImpl implements ArmaService {
    @Inject
    ArmaRepository repository;

    @Inject
    Validator validator;
    
    @Inject
    AcabamentoRepository acabamentoRepository;


    @Override
    @Transactional
    public ArmaResponseDTO insert(ArmaDTO dto) {
        Arma novaArma = new Arma();
            novaArma.setNome(dto.getNome());
            novaArma.setDescricao(dto.getDescricao());
            novaArma.setPreco(dto.getPreco());
            novaArma.setQtdNoEstoque(dto.getQtdNoEstoque());
            novaArma.setTipo(TipoArma.valueOf(dto.getTipo()));
            novaArma.setListaAcabamento(acharAcabamentos(dto.getIdsAcabamentos()));
            novaArma.setCalibre(dto.getCalibre());
            novaArma.setCapacidadeDeTiro(dto.getCapacidadeDeTiro());
            novaArma.setComprimentoDoCano(dto.getComprimentoDoCano());
            novaArma.setMarca(dto.getMarca());
            novaArma.setModelo(dto.getModelo());
            novaArma.setNumeroDaArma(dto.getNumeroDaArma());
            novaArma.setNumeroSigma(dto.getNumeroSigma());
            novaArma.setRna(dto.getRna());

        repository.persist(novaArma);

        return ArmaResponseDTO.valueOf(novaArma);
    }

    @Override
    @Transactional
    public ArmaResponseDTO update(ArmaDTO dto, Long id) {
        validar(dto);

        Arma arma = (Arma) repository.findById(id);
        if (arma != null) {
            arma.setNome(dto.getNome());
            arma.setDescricao(dto.getDescricao());
            arma.setPreco(dto.getPreco());
            arma.setQtdNoEstoque(dto.getQtdNoEstoque());
            arma.setTipo(TipoArma.valueOf(dto.getTipo()));
            arma.getListaAcabamento().clear();
            arma.setListaAcabamento(acharAcabamentos(dto.getIdsAcabamentos()));
            arma.setCalibre(dto.getCalibre());
            arma.setCapacidadeDeTiro(dto.getCapacidadeDeTiro());
            arma.setComprimentoDoCano(dto.getComprimentoDoCano());
            arma.setMarca(dto.getMarca());
            arma.setModelo(dto.getModelo());
            arma.setNumeroDaArma(dto.getNumeroDaArma());
            arma.setNumeroSigma(dto.getNumeroSigma());
            arma.setRna(dto.getRna());
        } else
            throw new NotFoundException();
        return ArmaResponseDTO.valueOf(arma);
    }


    //TRATAR ERRO DE ID INVALIDO
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
        throw new NotFoundException("Arma não encontrada para o ID: " + id);
    }

    @Override
    public ArmaResponseDTO findById(Long id) {
        if(repository.findById(id) != null)
            return ArmaResponseDTO.valueOf(repository.findById(id));
        else{
            throw new NotFoundException("Arma não encontrada para o ID: " + id);
        }
    }

    private Acabamento acharAcabamento(long idAcabamento) {
        Acabamento acabamento = acabamentoRepository.findById(idAcabamento);
        if (acabamento == null) {
            throw new NotFoundException("Acabamento não encontrada.");
        }
        return acabamento;
    }

    private List<Acabamento> acharAcabamentos(List<Long> idsAcabamentos) {
        return idsAcabamentos.stream()
            .map(this::acharAcabamento)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public List<ArmaResponseDTO> findByNome(String nome) {
        if(nome != null || nome == ""){
        return repository.findByNome(nome).stream()
            .map(p -> ArmaResponseDTO.valueOf(p)).toList();
        }
        throw new NotFoundException("Nome não encontrado!");
    }

    @Override
    public List<ArmaResponseDTO> findAll(int page, int pageSize) {
        List<Arma> lista = repository
                                    .findAll()
                                    .page(page, pageSize)
                                    .list();

        return lista
            .stream()
            .map(e -> ArmaResponseDTO.valueOf(e))
            .toList();
    }


    private void validar(ArmaDTO armaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ArmaDTO>> violations = validator.validate(armaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public ArmaResponseDTO salveImage(Long id, String nomeImagem) {

        Arma entity = repository.findById(id);
        entity.setNomeImagem(nomeImagem);

        return ArmaResponseDTO.valueOf(entity);
    }

    @Override
    public ArmaResponseDTO updateNomeImagem(Long id, String nomeImagem) {
        Arma arma = repository.findById(id);
        arma.setNomeImagem(nomeImagem);
        return ArmaResponseDTO.valueOf(arma);
    }
    
    @Override
    public long count() {
        return repository.count();
    }

        @Override
    public List<ArmaResponseDTO> findByNome(int page, int pageSize, String login){
        List<Arma> armas = repository
                                            .findByNome(login)
                                            .page(page, pageSize)
                                            .list();

        // Converte a lista de usuários para uma lista de DTOs de resposta
        return armas
                .stream()
                .map(ArmaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public long countByNome(String nome) {
        return repository.findByNome(nome).count();
    }
}
