package com.cartoes.api.services;

import java.text.ParseException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cartoes.api.entities.Cartao;
import com.cartoes.api.entities.Cliente;
import com.cartoes.api.entities.Transacao;
import com.cartoes.api.repositories.CartaoRepository;
import com.cartoes.api.repositories.ClienteRepository;
import com.cartoes.api.repositories.TransacaoRepository;
import com.cartoes.api.utils.ConsistenciaException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransacaoServiceTest {

    @MockBean
    private TransacaoRepository transacaoRepository;

    @MockBean
    private CartaoRepository cartaoRepository;

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteService clienteService;

    @Test(expected = NullPointerException.class)
    public void testBuscarPorCartaoNaoExistente() throws ConsistenciaException {

        BDDMockito.given(transacaoRepository.findByCartaoNumero(Mockito.anyString())).willReturn(null);

        transacaoService.buscarPorCartao("2948239847263123");

    }

    @Test
    public void testSalvarComSucesso() throws ConsistenciaException, ParseException {

        BDDMockito.given(clienteRepository.save(Mockito.any(Cliente.class))).willReturn(new Cliente());
        BDDMockito.given(cartaoRepository.save(Mockito.any(Cartao.class))).willReturn(new Cartao());
        BDDMockito.given(transacaoRepository.save(Mockito.any(Transacao.class))).willReturn(new Transacao());

    }

    @Test(expected = ConsistenciaException.class)
    public void testSalvarCartaoNaoEncontrado() throws ConsistenciaException, ParseException {

        BDDMockito.given(transacaoRepository.findByCartaoNumero(Mockito.anyString())).willReturn(Optional.empty());

        Transacao trans = new Transacao();
        Cartao cartao = new Cartao();

        cartao.setNumero("321123");
        trans.setCartao(cartao);

        transacaoService.salvar(trans);

    }
}
