package com.aula04.banco.banco.services;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import com.aula04.banco.banco.model.TipoConta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ClienteService {
    Random random = new Random();

    public Cliente cadastraCliente(RequestCliente requestCliente){
        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta(UUID.randomUUID(),random.nextInt(),requestCliente.getAgencia(), TipoConta.CONTA_CORRENTE, 0.0));
        Cliente cliente = new Cliente(
                UUID.randomUUID(),
                requestCliente.getNome(),
                requestCliente.getEmail(),
                requestCliente.getSenha(),
                requestCliente.getCpf(),
                contas
        );
        BancoAula04Application.bancoCliente.adiciona(cliente);

        return cliente;
    }

    public List<Cliente> searchClients(){
        return BancoAula04Application.bancoCliente.getClientes();
    }

    public Cliente clientDetails(UUID id) throws Exception {
        return BancoAula04Application.bancoCliente.searchCliente(id);
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        Cliente cliente = BancoAula04Application.bancoCliente.atualizaCliente(id, requestCliente);
        return cliente;
    }
}
