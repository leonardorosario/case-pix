package org.example.Service;

import org.example.DTO.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Repository.ChavePixRepository;
import org.springframework.stereotype.Service;

@Service
public class ChavePixService {

    private final ChavePixRepository repository;

    public ChavePixService(ChavePixRepository repository){
        this.repository = repository;
    }

    public ChavePix incluirChave(ChavePixRequestDTO dto) {
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
