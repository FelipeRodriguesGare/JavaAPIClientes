package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.utils.CPF;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class RequestCliente {
    @NotNull @NotEmpty(message = "Batata") @Length(min = 2)
    private String nome;
    private String email;
    private String senha;
    @CPF
    private String cpf;
    private Integer agencia;
}
