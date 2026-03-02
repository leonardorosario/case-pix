package org.example.Strategy;

import org.example.Entity.TipoChave;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCnpj implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if (valorChave == null || valorChave.length() != 14){
            throw new IllegalArgumentException("O CNPJ informado é invalido! Deve conter 14 dígitos");
        }
        if (valorChave.matches("(\\d)\\1{13}")) {
            throw new IllegalArgumentException("O CNPJ informado é inválido!");
        }

        try {
            int soma = 0;
            int peso = 2;
            for (int i = 11; i >= 0; i--) {
                soma += (valorChave.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            int r = soma % 11;
            char digito13 = (r == 0 || r == 1) ? '0' : (char) ((11 - r) + '0');

            soma = 0;
            peso = 2;
            for (int i = 12; i >= 0; i--) {
                soma += (valorChave.charAt(i) - '0') * peso;
                peso = (peso == 9) ? 2 : peso + 1;
            }
            r = soma % 11;
            char digito14 = (r == 0 || r == 1) ? '0' : (char) ((11 - r) + '0');

            if ((digito13 != valorChave.charAt(12)) || (digito14 != valorChave.charAt(13))) {
                throw new IllegalArgumentException("O CNPJ informado não é válido matematicamente.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao validar o CNPJ.");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.CNPJ;
    }
}
