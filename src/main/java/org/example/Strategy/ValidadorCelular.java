package org.example.Strategy;

import org.example.Entity.TipoChave;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCelular implements ValidadorChaveStrategy {

    @Override
    public void validar(String valorChave){
        if(!valorChave.matches("^\\+[0-9]{2}[0-9]{2,3}[0-9]{9}$")){
            throw new IllegalArgumentException("O celular informado é invalido! Deve conter o '+' no inicio seguido dos números");
        }
    }

    @Override
    public TipoChave getTipoChave(){
        return TipoChave.CELULAR;
    }
}
