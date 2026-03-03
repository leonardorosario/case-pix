package org.example.Controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.example.DTO.Atualizacao.ChavePixAtualizacaoDTO;
import org.example.DTO.Consulta.ChavePixResponseDTO;
import org.example.DTO.Inclusao.ChavePixRequestDTO;
import org.example.Entity.ChavePix;
import org.example.Entity.TipoChave;
import org.example.Service.ChavePixService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

            return ResponseEntity.ok().body("Chave validada e cadastrada com sucesso! " + chavePix.getId());
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
    @GetMapping
    public ResponseEntity<?> consultarChaves(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) TipoChave tipoChave,
            @RequestParam(required = false) Integer numeroAgencia,
            @RequestParam(required = false) Integer numeroConta,
            @RequestParam(required = false) String nomeCorrentista,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInclusao,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInativacao
            ){
        if(id != null){
            boolean outroFiltro = (tipoChave != null || numeroAgencia != null || numeroConta != null
                    || nomeCorrentista != null || dataInclusao != null || dataInativacao != null);

            if(outroFiltro){
                return ResponseEntity.unprocessableContent().body("Informando o ID nenhum outro filtro pode ser usado");
            }
        }

        if(dataInclusao != null && dataInativacao != null){
            return ResponseEntity.unprocessableContent().body("Não é permitida a combinação dos filtros de Data de inclusão " +
                                                                                "e Data de inativação. Utilize apenas um deles");
        }

        List<ChavePix> chavesEncontradas = service.consultarChaves(id, tipoChave, numeroAgencia, numeroConta, nomeCorrentista, dataInclusao, dataInativacao);

        if (chavesEncontradas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma chave Pix encontrada para os filtros utilizados");
        }

        List<ChavePixResponseDTO> resposta = chavesEncontradas.stream()
                .map(ChavePixResponseDTO::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }
}
