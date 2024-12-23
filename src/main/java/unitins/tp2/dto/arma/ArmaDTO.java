package unitins.tp2.dto.arma;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ArmaDTO extends ProdutoDTO {
    @NotNull(message = "insira o tipo de arma corretamente")
    Integer tipoArma;
    @NotBlank(message = "insira o 'marca' corretamente")
    String marca;
    @NotNull(message = "Os ids das acabamentos devem ser informados.")
    List<Long> idsAcabamentos;
    @NotBlank(message = "insira o 'calibre' corretamente")
    String calibre;
    @NotBlank(message = "insira o 'comprimento do cano' corretamente")
    String comprimentoDoCano;
    @NotNull(message = "insira o 'capacidade de tiro' corretamente")
    int capacidadeDeTiro;
    @NotBlank(message = "insira o 'nomero sigma' corretamente")
    String numeroSigma;
    @NotBlank(message = "insira o 'numero da arma' corretamente")
    String numeroDaArma;
    @NotBlank(message = "insira o 'modelo' corretamente")
    String modelo;
    String rna;

    public ArmaDTO(@NotBlank(message = "insira o nome corretamente") String nome,
            @NotNull(message = "insira a qtdNoEstoque corretamente") int qtdNoEstoque,
            @NotNull(message = "insira o preco corretamente") double preco,
            @NotBlank(message = "insira a descrição corretamente") String descricao,
            @NotNull(message = "insira o tipo de arma corretamente") int tipoArma,
            @NotBlank(message = "insira o 'marca' corretamente") String marca,
            @NotBlank(message = "insira o 'acabamento' corretamente") List<Long> idsAcabamentos,
            @NotBlank(message = "insira o 'calibre' corretamente") String calibre,
            @NotBlank(message = "insira o 'comprimento do cano' corretamente") String comprimentoDoCano,
            @NotNull(message = "insira o 'capacidade de tiro' corretamente") int capacidadeDeTiro,
            @NotBlank(message = "insira o 'nomero sigma' corretamente") String numeroSigma,
            @NotBlank(message = "insira o 'numero da arma' corretamente") String numeroDaArma,
            @NotBlank(message = "insira o 'modelo' corretamente") String modelo, String rna) {
        super(nome, qtdNoEstoque, preco, descricao);
        this.tipoArma = tipoArma;
        this.marca = marca;
        this.idsAcabamentos = idsAcabamentos;
        this.calibre = calibre;
        this.comprimentoDoCano = comprimentoDoCano;
        this.capacidadeDeTiro = capacidadeDeTiro;
        this.numeroSigma = numeroSigma;
        this.numeroDaArma = numeroDaArma;
        this.modelo = modelo;
        this.rna = rna;
    }

    
}

