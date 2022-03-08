package com.aula04.banco.banco.services;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestDeposito;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperacoesService {

    public void deposita(UUID id, RequestDeposito requestDeposito) throws Exception {
        BancoAula04Application.bancoCliente.deposita(id, requestDeposito);
    }
}
