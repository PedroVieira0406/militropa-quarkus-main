package unitins.tp2.dto.funcionario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FuncionarioDTO(
        @NotBlank(message = "O campo nome não pode ficar em branco") 
        String nome,
        @NotBlank(message = "O campo 'CPF' não pode estar em branco")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
        String cpf,
        @NotBlank(message = "O campo 'e-mail' não pode estar em branco")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
        String email,
        @NotBlank(message = "O campo 'telefone' não pode estar em branco")
        @Pattern(regexp = "(\\(?\\d{2}\\)?\\s?)?(\\d{4,5}-\\d{4})", message = "Telefone inválido")
        String telefone,
        @NotNull(message = "O campo 'endereço' não pode ser nulo")
        Long endereco,
        @NotBlank(message = "O campo 'matrícula' não pode estar em branco")
        String matricula,
        @NotNull(message = "O campo 'usuário' não pode ser nulo")
        Long usuario){

}