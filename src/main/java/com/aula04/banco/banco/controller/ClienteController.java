package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.BancoCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    BancoCliente bancoCliente = new BancoCliente();
    Random random = new Random();

    @GetMapping()
    public List<ResponseCliente> clientes(){
        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta(UUID.randomUUID(),1234,23, TipoConta.CONTA_CORRENTE));
        Cliente cliente = new Cliente(UUID.randomUUID(),"teste","Teste@Teste","1234",contas);
        Cliente cliente2 = new Cliente(UUID.randomUUID(),"teste2","Teste@Teste2","1234",contas);

        bancoCliente.adiciona(cliente);
        bancoCliente.adiciona(cliente2);

        return ResponseCliente.toResponse(bancoCliente.getClientes());
    }

    @GetMapping("/cadastro/cliente")
    public String formCliente(){
        return "formCliente";
    }

    @PostMapping()
    public ResponseEntity<ResponseCliente> cadastraCliente(
            @RequestBody RequestCliente requestCliente,
            UriComponentsBuilder uriComponentsBuilder
    ){
        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta(UUID.randomUUID(),random.nextInt(),requestCliente.getAgencia(), TipoConta.CONTA_CORRENTE));
        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                requestCliente.getNome(),
                requestCliente.getEmail(),
                requestCliente.getSenha(),
                contas
        );
        bancoCliente.adiciona(cliente);

        URI uri = uriComponentsBuilder.path("clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detailsCliente(@PathVariable UUID id) throws Exception {
        Cliente cliente = this.bancoCliente.searchCliente(id);
        return ResponseEntity.ok().body(new ResponseCliente(cliente));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseCliente> atualizaCliente(
            @PathVariable UUID id,
            @RequestBody RequestCliente requestCliente
    ) throws  Exception{
        Cliente cliente = bancoCliente.atualizaCliente(id, requestCliente);

        return ResponseEntity.ok().body(new ResponseCliente(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UUID> deleteCliente(@PathVariable UUID id) throws Exception {
        bancoCliente.deletCliente(id);
        return ResponseEntity.ok().body(id);
    }

}
