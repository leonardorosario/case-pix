package org.example.Strategy;

import org.example.Entity.TipoChave;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCpf implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if (valorChave == null || valorChave.length() != 11){
            throw new IllegalArgumentException("O CPF informado é invalido! Deve conter 11 dígitos");
        }
        if (valorChave.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("O CPF informado é inválido!");
        }

        try {
            int soma = 0;
            int peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += (valorChave.charAt(i) - '0') * peso--;
            }
            int r = 11 - (soma % 11);
            char digito10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += (valorChave.charAt(i) - '0') * peso--;
            }
            r = 11 - (soma % 11);
            char digito11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

            if ((digito10 != valorChave.charAt(9)) || (digito11 != valorChave.charAt(10))) {
                throw new IllegalArgumentException("O CPF informado não é válido matematicamente");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao validar o CPF");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.CPF;
    }
}
