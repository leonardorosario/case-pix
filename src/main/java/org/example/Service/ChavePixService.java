package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.DTO.Atualizacao.ChavePixAtualizacaoDTO;
import org.example.DTO.Inclusao.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Entity.TipoChave;
import org.example.Repository.ChavePixRepository;
import org.example.Specification.ChavePixSpecification;
import org.example.Strategy.ValidadorChaveStrategy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ChavePixService {

    private final ChavePixRepository repository;
    private final List<ValidadorChaveStrategy> validadores;

    public ChavePixService(ChavePixRepository repository, List<ValidadorChaveStrategy> validadores){
        this.repository = repository;
        this.validadores = validadores;
    }

    public ChavePix incluirChave(ChavePixRequestDTO dto) {
        ValidadorChaveStrategy validador = validadores.stream()
                .filter(v -> v.getTipoChave() == dto.getTipoChave())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de chave não suportado"));

        validador.validar(dto.getValorChave());

        if(repository.existsByValorChave(dto.getValorChave())){
            throw new IllegalArgumentException("Esta chave Pix já está registrada");
        }

        ChavePix novaChave = new ChavePix();
        novaChave.setTipoChave(dto.getTipoChave());
        novaChave.setValorChave(dto.getValorChave());
        novaChave.setTipoConta(dto.getTipoConta());
        novaChave.setNumeroAgencia(dto.getNumeroAgencia());
        novaChave.setNumeroConta(dto.getNumeroConta());
        novaChave.setNomeCorrentista(dto.getNomeCorrentista());

        return repository.save(novaChave);
    }

    public ChavePix alterarChave(ChavePixAtualizacaoDTO dto){
        ChavePix chaveAlterada = repository.findById(dto.getId()).
                orElseThrow(() -> new EntityNotFoundException("Chave Pix não encontrada para esse ID"));

        if (chaveAlterada.getDataHoraInativacao() != null) {
            throw new IllegalArgumentException("Não é permitido alterar os dados de uma chave inativada.");
        }

        chaveAlterada.setTipoConta(dto.getTipoConta());
        chaveAlterada.setNumeroAgencia(dto.getNumeroAgencia());
        chaveAlterada.setNumeroConta(dto.getNumeroConta());
        chaveAlterada.setNomeCorrentista(dto.getNomeCorrentista());

        return repository.save(chaveAlterada);
    }

    public ChavePix inativarChave(UUID id){
        ChavePix chaveExistente = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Chave Pix não encontrada para esse Id"));

        if(chaveExistente.getDataHoraInativacao() != null){
            throw new IllegalArgumentException("Esta chave Pix já foi desativada");
        }

        chaveExistente.setDataHoraInativacao(LocalDateTime.now());

        return repository.save(chaveExistente);
    }

    public List<ChavePix> consultarChaves(UUID id, TipoChave tipoChave, Integer numeroAgencia, Integer numeroConta,
                                         String nomeCorrentista, LocalDate dataInclusao, LocalDate dataInativacao){
        Specification<ChavePix> spec = ChavePixSpecification.comFiltros(
                id, tipoChave, numeroAgencia, numeroConta, nomeCorrentista, dataInclusao, dataInativacao
        );

        return repository.findAll(spec);
    }
}
