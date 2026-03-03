package org.example.DTO.Consulta;

import lombok.Getter;
import org.example.Entity.ChavePix;

import java.time.format.DateTimeFormatter;

@Getter
public class ChavePixResponseDTO {

    private String id;
    private String tipoChave;
    private String valorChave;
    private String tipoConta;
    private Integer numeroAgencia;
    private Integer numeroConta;
    private String nomeCorrentista;
    private String dataHoraInclusao;
    private String dataHoraInativacao;

    public ChavePixResponseDTO(ChavePix chave) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataHoraInclusao = chave.getDataHoraInclusao() != null ? chave.getDataHoraInclusao().format(formatter) : "";
        this.dataHoraInativacao = chave.getDataHoraInativacao() != null ? chave.getDataHoraInativacao().format(formatter) : "";

        this.id = chave.getId().toString();
        this.nomeCorrentista = chave.getNomeCorrentista();
        this.numeroAgencia = chave.getNumeroAgencia();
        this.numeroConta = chave.getNumeroConta();
        this.tipoChave = chave.getTipoChave().name();
        this.tipoConta = chave.getTipoChave().name();
        this.valorChave = chave.getValorChave();
    }
}
