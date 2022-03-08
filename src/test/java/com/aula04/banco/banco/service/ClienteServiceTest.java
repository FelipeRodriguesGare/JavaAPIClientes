package com.aula04.banco.banco.service;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.services.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class ClienteServiceTest {
    ClienteService clienteService = new ClienteService();

    @Test
    public void mustReturnEmptyClientList(){
        List<Cliente> clientes = clienteService.searchClients();
        Assertions.assertTrue(clientes.isEmpty());
    }

    @Test
    public void cadastraCliente(){
        RequestCliente requestCliente = new RequestCliente(
                "gare",
                "gare@gare",
                "12345678911",
                "123",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);

        Assertions.assertEquals(requestCliente.getNome(), cliente.getNome());
        Assertions.assertNotNull(cliente.getId());
    }

    @Test
    public void atualizaCliente() throws Exception {
        RequestCliente requestCliente = new RequestCliente(
                "gare",
                "gare@gare",
                "12345678911",
                "123",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);

        RequestCliente requestClienteAtualiza = new RequestCliente(
                "brubru",
                "gare@gare",
                "12345678911",
                "123",
                3);

        Cliente clienteAtualizado = clienteService.atualizaCliente(cliente.getId(), requestClienteAtualiza);

        Assertions.assertEquals("brubru",clienteAtualizado.getNome());
    }

    @Test
    void buscaClienteNaoExiste() throws Exception {
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(Exception.class, ()-> clienteService.clientDetails(id));
    }

    @Test
    void buscaClientenaoExiste2(){
        UUID id = UUID.randomUUID();
        try {
            clienteService.clientDetails(id);
            fail("não lanõu a exceção");
        } catch (Exception e){ }
    }
}