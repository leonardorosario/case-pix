package org.example.Strategy;

import org.example.Entity.TipoChave;

public class ValidadorCnpj implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if (valorChave == null || valorChave.length() != 14){
            throw new IllegalArgumentException("O CNPJ informado é invalido! Deve conter 14 dígitos");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.CNPJ;
    }
}
