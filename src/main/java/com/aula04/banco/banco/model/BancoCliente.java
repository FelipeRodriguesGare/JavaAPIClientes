package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;

import java.util.*;

public class BancoCliente {
    private static List<Cliente> clientes = new ArrayList<>();

    public void adiciona(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> getClientes(){
        return BancoCliente.clientes;
    }

    public Cliente searchCliente(UUID id) throws Exception {
        Optional<Cliente> resultCliente =
                BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id)).findAny();

        if(resultCliente.isPresent()){
            return resultCliente.get();
        } else{
            throw new Exception("User não encontrado!");
        }
    }

    public Cliente atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception{
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(),id))
                .forEach(cliente -> {
                    cliente.setNome(requestCliente.getNome());
                    cliente.setEmail(requestCliente.getEmail());
                    cliente.setSenha(requestCliente.getSenha());
                });
        return searchCliente(id);
    }

    public Boolean deleteCliente(UUID id) throws Exception{
        Cliente cliente = searchCliente(id);
        BancoCliente.clientes.remove(cliente);
        return true;
    }

    public void deposita(UUID id, RequestDeposito requestDeposito) throws Exception{
        Cliente cliente = searchCliente(id);

        Optional<Conta> resultConta = cliente.getContas().stream()
                .filter(conta -> Objects.equals(conta.getId(),requestDeposito.getIdConta())).findAny();
        if(resultConta.isPresent()) {
            resultConta.get().setSaldo(resultConta.get().getSaldo() + requestDeposito.getValor());
        } else {
            try {
                throw new Exception("Conta não encontrada!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
