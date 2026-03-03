package org.example.Controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.example.DTO.Atualizacao.ChavePixAtualizacaoDTO;
import org.example.DTO.Inclusao.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Service.ChavePixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chaves")
public class ChavePixController {

    private final ChavePixService service;

    public ChavePixController(ChavePixService service){

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> incluirChave(@RequestBody @Valid ChavePixRequestDTO requestDTO){
        try {
            ChavePix chavePix = service.incluirChave(requestDTO);

            return ResponseEntity.ok().body("Chave validada com sucesso! " + chavePix.getId());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.unprocessableContent().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> alterarChave(@RequestBody @Valid ChavePixAtualizacaoDTO dto){
        try {
            ChavePix chaveAtualizada = service.alterarChave(dto);

            return ResponseEntity.ok().body(chaveAtualizada);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.unprocessableContent().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarChave(@PathVariable UUID id) {
        try{
            ChavePix chaveInativada = service.inativarChave(id);

            return ResponseEntity.ok().body(chaveInativada);
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.unprocessableContent().body(e.getMessage());
        }
    }
}
