package unitins.tp2.dto.arma;

import java.util.List;

import unitins.tp2.dto.acabamento.AcabamentoResponseDTO;
import unitins.tp2.model.Arma;
import unitins.tp2.model.TipoArma;

public record ArmaResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    int qtdNoEstoque,
    TipoArma tipoArma,
    String marca,
    String calibre,
    String comprimentoDoCano,
    int capacidadeDeTiro,
    String numeroSigma,
    String numeroDaArma,
    String modelo,
    String rna,
    String nomeImagem,
    List<AcabamentoResponseDTO> listaAcabamento
    ){
        public static ArmaResponseDTO valueOf(Arma arma){

            List<AcabamentoResponseDTO> listaAcabamento = arma.getListaAcabamento()
                                                .stream()
                                                .map(AcabamentoResponseDTO:: valueOf)
                                                .toList();

            return new ArmaResponseDTO(
                arma.getId(),
                arma.getNome(),
                arma.getDescricao(),
                arma.getPreco(),
                arma.getQtdNoEstoque(),
                arma.getTipoArma(),
                arma.getMarca(),
                arma.getCalibre(),
                arma.getComprimentoDoCano(),
                arma.getCapacidadeDeTiro(),
                arma.getNumeroSigma(),
                arma.getNumeroDaArma(),
                arma.getModelo(),
                arma.getRna(),
                arma.getNomeImagem(),
                listaAcabamento);
    }
}