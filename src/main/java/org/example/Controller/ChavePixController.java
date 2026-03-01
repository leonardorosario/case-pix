package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.ChavePixRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chaves")
public class ChavePixController {

    @PostMapping
    public ResponseEntity<?> incluirChave(@RequestBody @Valid ChavePixRequestDTO requestDTO){
        return ResponseEntity.ok().body("Chave validada com sucesso!");
    }
}
