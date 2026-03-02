package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Service.ChavePixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            return ResponseEntity.ok().body("Chave validada com sucesso!");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
