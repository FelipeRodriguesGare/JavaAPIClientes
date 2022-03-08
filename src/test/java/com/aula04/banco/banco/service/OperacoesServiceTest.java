package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.services.ClienteService;
import com.aula04.banco.banco.services.OperacoesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class OperacoesServiceTest {

    ClienteService clienteService = new ClienteService();
    OperacoesService operacoesService = new OperacoesService();

    @Test
    void testaDeposito() throws Exception {
        RequestCliente requestCliente = new RequestCliente(
                "gare",
                "gare@gare",
                "12345678911",
                "123",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);

        RequestDeposito deposito = new RequestDeposito(20.0, cliente.getContas().get(0).getId());

        operacoesService.deposita(cliente.getId(), deposito);

        Assertions.assertEquals(20.0, cliente.getContas().get(0).getSaldo());
    }

    @Test
    void erroNoDepositoNaoAchouConta(){
        UUID id = UUID.randomUUID();

        RequestDeposito deposito = new RequestDeposito(20.0, id);

        Assertions.assertThrows(Exception.class, ()->operacoesService.deposita(id, deposito));
    }
}
