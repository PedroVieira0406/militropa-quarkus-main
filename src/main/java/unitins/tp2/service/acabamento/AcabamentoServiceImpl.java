package unitins.tp2.service.acabamento;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp2.dto.acabamento.AcabamentoDTO;
import unitins.tp2.dto.acabamento.AcabamentoResponseDTO;
import unitins.tp2.model.Acabamento;
import unitins.tp2.repository.AcabamentoRepository;


@ApplicationScoped
public class AcabamentoServiceImpl implements AcabamentoService {

    @Inject
    AcabamentoRepository repository;

    @Override
    public AcabamentoResponseDTO insert(AcabamentoDTO dto) {
        Acabamento novoAcabamento = new Acabamento();
        novoAcabamento.setMaterial(dto.material());
        repository.persist(novoAcabamento);
        return AcabamentoResponseDTO.valueOf(novoAcabamento);
    }

    @Override
    @Transactional
    public AcabamentoResponseDTO update(AcabamentoDTO dto, Long id) {
        Acabamento acabamento = repository.findById(id);
        if (acabamento != null) {
            acabamento.setMaterial(dto.material());;
           
        } else
            throw new NotFoundException();

        return AcabamentoResponseDTO.valueOf(acabamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public AcabamentoResponseDTO findById(Long id) {
        return AcabamentoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<AcabamentoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(e -> AcabamentoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<AcabamentoResponseDTO> findAll(int page, int pageSize) {
        List<Acabamento> listAcabamento = repository
                                    .findAll()
                                    .page(page, pageSize)
                                    .list();

        return listAcabamento
            .stream()
            .map(e -> AcabamentoResponseDTO.valueOf(e))
            .toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

}