package org.example.Entity;

import lombok.Getter;

@Getter
public enum TipoChave {
    CELULAR("celular"),
    EMAIL("email"),
    CPF("cpf"),
    CNPJ("cnpj");

    private String tipoChave;

    TipoChave(String tipoChave) {
        this.tipoChave = tipoChave;
    }
}
