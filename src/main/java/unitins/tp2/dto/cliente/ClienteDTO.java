package unitins.tp2.dto.cliente;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteDTO(
                @NotBlank(message = "O campo nome não pode ficar em branco") String nome,
                @NotBlank(message = "O campo 'CPF' não pode estar em branco") @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido") String cpf,
                @NotBlank(message = "O campo 'e-mail' não pode estar em branco") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido") String email,
                @NotBlank(message = "O campo 'Numero de Registro de Posse ou Porte' não pode estar em branco") String registro,
                @NotBlank(message = "O campo telefone não pode ficar em branco") String telefone,
                @NotBlank(message = "O campo nome do endereço não pode ficar em branco") String enderecoNome,
                @NotBlank(message = "O campo logradouro não pode ficar em branco") String enderecoLogradouro,
                @NotBlank(message = "O campo número não pode ficar em branco") String enderecoNumero,
                @NotBlank(message = "O campo bairro não pode ficar em branco") String enderecoBairro,
                String enderecoComplemento,
                @NotBlank(message = "O campo cep não pode ficar em branco") String enderecoCep,
                @NotBlank(message = "O campo cidade não pode ficar em branco") String enderecoCidade,
                @NotBlank(message = "O campo estado não pode ficar em branco") String enderecoEstado,
                @NotBlank(message = "O campo login não pode ficar em branco") String login,
                @NotBlank(message = "O campo senha não pode ficar em branco") String senha) {
}