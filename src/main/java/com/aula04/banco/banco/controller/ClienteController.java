package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public List<ResponseCliente> clientes(){
        return ResponseCliente.toResponse(clienteService.searchClients());
    }

    @PostMapping()
    public ResponseEntity<ResponseCliente> cadastraCliente(
            @RequestBody @Valid RequestCliente requestCliente,
            UriComponentsBuilder uriComponentsBuilder
    ){
        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        URI uri = uriComponentsBuilder.path("clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detailsCliente(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok().body(new ResponseCliente(clienteService.clientDetails(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseCliente> atualizaCliente(
            @PathVariable UUID id,
            @RequestBody RequestCliente requestCliente
    ) throws  Exception{
        Cliente cliente = clienteService.atualizaCliente(id, requestCliente);
        return ResponseEntity.ok().body(new ResponseCliente(cliente));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UUID> deleteCliente(@PathVariable UUID id) throws Exception {
        BancoAula04Application.bancoCliente.deleteCliente(id);
        return ResponseEntity.ok().body(id);
    }


}
