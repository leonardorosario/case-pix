package org.example.Strategy;

import org.example.Entity.TipoChave;

public class ValidadorCpf implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if (valorChave == null || valorChave.length() != 11){
            throw new IllegalArgumentException("O CPF informado é invalido! Deve conter 11 dígitos");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.CPF;
    }
}
