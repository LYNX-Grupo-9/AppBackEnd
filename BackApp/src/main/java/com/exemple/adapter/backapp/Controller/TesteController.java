package com.exemple.adapter.backapp.Controller;

import com.exemple.adapter.backapp.Dto.TesteDTO;
import com.exemple.adapter.backapp.Service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para testes
 */
@RestController
@RequestMapping("/api/teste")
public class TesteController {

    @Autowired
    private TesteService testeService;

    @GetMapping
    public ResponseEntity<List<TesteDTO>> listarTodos() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TesteDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<TesteDTO> criar(@RequestBody TesteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TesteDTO> atualizar(@PathVariable Long id, @RequestBody TesteDTO dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}

