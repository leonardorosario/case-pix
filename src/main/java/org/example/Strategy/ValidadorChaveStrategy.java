package org.example.Strategy;

import org.example.Entity.TipoChave;

public interface ValidadorChaveStrategy {
    void validar(String valorChave);

    TipoChave getTipoChave();
}
