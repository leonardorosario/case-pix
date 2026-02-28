package org.example.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chaves_pix")
public class ChavePix {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_chave", nullable = false, length = 9)
    private TipoChave tipoChave;

    @Column(name = "valor_chave", nullable = false, length = 77, unique = true)
    private String valorChave;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta", nullable = false, length = 10)
    private TipoConta tipoConta;

    @Column(name = "numero_agencia", nullable = false)
    private Integer numeroAgencia;

    @Column(name = "numero_conta", nullable = false)
    private Integer numeroConta;

    @Column(name = "nome_correntista", nullable = false, length = 30)
    private String nomeCorrentista;

    @Column(name = "data_hora_inclusao", nullable = false)
    private LocalDateTime dataHoraInclusao;

    @Column(name = "data_hora_inativacao")
    private LocalDateTime dataHoraInativacao;

    @PrePersist
    protected void onCreate() {
        this.dataHoraInclusao = LocalDateTime.now();
    }
}
