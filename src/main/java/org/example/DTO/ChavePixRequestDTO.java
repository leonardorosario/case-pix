package org.example.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.example.Entity.TipoChave;
import org.example.Entity.TipoConta;

@Getter
@Setter
public class ChavePixRequestDTO {

    @NotNull(message = "O tipo da chave é obrigatório")
    private TipoChave tipoChave; //

    @NotBlank(message = "O valor da chave não pode ser vazio")
    @Size(max = 77, message = "O valor da chave deve ter no máximo 77 caracteres")
    private String valorChave; //

    @NotNull(message = "O tipo da conta é obrigatório")
    private TipoConta tipoConta; //

    @NotNull(message = "O número da agência é obrigatório")
    @Max(value = 9999, message = "A agência deve ter no máximo 4 dígitos")
    private Integer numeroAgencia; //

    @NotNull(message = "O número da conta é obrigatório")
    @Max(value = 99999999, message = "A conta deve ter no máximo 8 dígitos")
    private Integer numeroConta; //

    @NotBlank(message = "O nome do correntista é obrigatório")
    @Size(max = 30, message = "O nome deve ter no máximo 30 caracteres")
    private String nomeCorrentista;
}
