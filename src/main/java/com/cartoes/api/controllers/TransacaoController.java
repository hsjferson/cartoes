package com.cartoes.api.controllers;

import com.cartoes.api.dtos.TransacaoDto;
import com.cartoes.api.entities.Transacao;
import com.cartoes.api.response.Response;
import com.cartoes.api.services.TransacaoService;
import com.cartoes.api.utils.ConsistenciaException;
import com.cartoes.api.utils.ConversaoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    private static final Logger log = LoggerFactory.getLogger(TransacaoController.class);

    @Autowired
    private TransacaoService transacaoService;

    /**
     * Retorna as transações de um cartão em específico
     *
     * @param cartaoNumero do cartão a ser consultado
     * @return Lista de transações que o cartão possui
     */
    @GetMapping(value = "/cartao/{cartaoNumero}")
    public ResponseEntity<Response<List<TransacaoDto>>> buscarPorCartao(@PathVariable("cartaoNumero") String cartaoNumero) {

        Response<List<TransacaoDto>> response = new Response<List<TransacaoDto>>();

        try {

            log.info("Controller: buscando transações do cartão: {}", cartaoNumero);

            List<Transacao> listaTransacoes = transacaoService.buscarPorCartao(cartaoNumero);

            response.setDados(ConversaoUtils.ConverterListaTransacao(listaTransacoes));

            return ResponseEntity.ok(response);

        } catch (ConsistenciaException e) {

            log.info("Controller: Inconsistência de dados: {}", e.getMessage());
            response.adicionarErro(e.getMensagem());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
            response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
            return ResponseEntity.status(500).body(response);

        }

    }

    /**
     * Persiste uma transacao na base.
     *
     * @param result de entrada da transação
     * @return Dados da transação persistida
     */
    @PostMapping
    public ResponseEntity<Response<TransacaoDto>> salvar(@Valid @RequestBody TransacaoDto transacaoDto, BindingResult result) {

        Response<TransacaoDto> response = new Response<TransacaoDto>();

        try {

            log.info("Controller: salvando a transação: {}", transacaoDto.toString());

            if (result.hasErrors()) {

                for (int i = 0; i < result.getErrorCount(); i++) {
                    response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                }

                log.info("Controller: Os campos obrigatórios não foram preenchidos");
                return ResponseEntity.badRequest().body(response);

            }

            Transacao transacao = this.transacaoService.salvar(ConversaoUtils.Converter(transacaoDto));
            response.setDados(ConversaoUtils.Converter(transacao));

            return ResponseEntity.ok(response);

        } catch (ConsistenciaException e) {

            log.info("Controller: Inconsistência de dados: {}", e.getMessage());
            response.adicionarErro(e.getMensagem());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {

            log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
            response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
            return ResponseEntity.status(500).body(response);

        }

    }
}
