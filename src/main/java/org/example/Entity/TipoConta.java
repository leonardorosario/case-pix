package org.example.Entity;

import lombok.Getter;

@Getter
public enum TipoConta {
    CORRENTE("corrente"),
    POUPANCA("poupança");

    private String tipoConta;

    TipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
