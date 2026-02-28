package Entity;

public enum TipoConta {
    CORRENTE("corrente"),
    POUPANCA("poupança");

    private String tipoConta;

    TipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }
}
