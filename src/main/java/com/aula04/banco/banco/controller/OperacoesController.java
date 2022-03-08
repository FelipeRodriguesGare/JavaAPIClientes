package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.services.OperacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    @Autowired
    private OperacoesService operacoesService;

    @PatchMapping("/deposita")
    public ResponseEntity deposita(
            @RequestHeader("id") UUID id,
            @RequestBody RequestDeposito requestDeposito
    ) throws Exception {
        operacoesService.deposita(id, requestDeposito);
        return ResponseEntity.ok().build();
    }
}
