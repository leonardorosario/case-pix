package org.example.Strategy;

import org.example.Entity.TipoChave;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmail implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if(!valorChave.contains("@")){
            throw new IllegalArgumentException("O e-mail informado é invalido! Deve conter o '@'");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.EMAIL;
    }
}
