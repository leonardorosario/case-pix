import Entity.TipoChave;
import Entity.TipoConta;

public class Cadastro {

    private TipoChave tipoChave;
    private TipoConta tipoConta;
    private int numeroAgencia;
    private int numeroConta;
    private String nomeCorrentista;

    public Cadastro(String nomeCorrentista, int numeroAgencia, int numeroConta, TipoChave tipoChave, TipoConta tipoConta) {
        this.nomeCorrentista = nomeCorrentista;
        this.numeroAgencia = numeroAgencia;
        this.numeroConta = numeroConta;
        this.tipoChave = tipoChave;
        this.tipoConta = tipoConta;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(int numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public TipoChave getTipoChave() {
        return tipoChave;
    }

    public void setTipoChave(TipoChave tipoChave) {
        this.tipoChave = tipoChave;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
