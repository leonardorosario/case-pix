package org.example.Service;

import org.example.DTO.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Repository.ChavePixRepository;
import org.example.Strategy.ValidadorChaveStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
