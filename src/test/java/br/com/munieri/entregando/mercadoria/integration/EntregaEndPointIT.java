package br.com.munieri.entregando.mercadoria.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import br.com.munieri.entregando.mercadoria.AppConfWebTest;
import br.com.munieri.entregando.mercadoria.domain.Rota;
import br.com.munieri.entregando.mercadoria.domain.service.MapaService;
import br.com.munieri.entregando.mercadoria.view.endpoint.MapaDTO;
import br.com.munieri.entregando.mercadoria.view.endpoint.RotaDTO;
import br.com.munieri.entregando.mercadoria.view.endpoint.validator.MenorValorDeEntrega;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
public class EntregaEndPointIT extends AppConfWebTest {

    @Autowired
    private MapaService mapaService;

    @Test
    public void deve_cadastrar_malha_logistica() throws Exception {

        MapaDTO mapaDTO = new MapaDTO();
        mapaDTO.setNome("MAPA DE SP");

        List<RotaDTO> rotas = new ArrayList<RotaDTO>();
        rotas.add(new RotaDTO("São Paulo", "Santos", 80));
        rotas.add(new RotaDTO("São Paulo", "Bragança Paulista", 87));
        rotas.add(new RotaDTO("São Paulo", "Ourinhos", 380));

        mapaDTO.setRotas(rotas);
        this.mvc.perform(
                post("/services/novoMapa")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(gson.toJson((mapaDTO))))
                .andExpect(status().isOk());
    }

    @Test
    public void deve_invalidar_cadastro_de_mapa_sem_nome() throws Exception {
        MapaDTO mapaDTO = new MapaDTO();
        this.mvc.perform(
                post("/services/novoMapa")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(gson.toJson((mapaDTO))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deve_invalidar_cadastro_de_mapa_sem_rota() throws Exception {
        MapaDTO mapaDTO = new MapaDTO();
        mapaDTO.setNome("Mapa zero");
        this.mvc.perform(
                post("/services/novoMapa")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(gson.toJson((mapaDTO))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deve_retornar_o_menor_valor_de_entrega() throws Exception {

        popularRotas();

        MvcResult mvcReturn = this.mvc.perform(
                get("/services/menorValorDeEntrega")
                        .param("origem", "A")
                        .param("destino", "D")
                        .param("autonomia", "10")
                        .param("valorLitroCombustivel", "2.5")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andReturn();

        MenorValorDeEntrega menorValorDeEntrega = gson.fromJson(mvcReturn.getResponse().getContentAsString(), MenorValorDeEntrega.class);

        Assert.isTrue(menorValorDeEntrega.getValor().equals(6.25));
        Assert.isTrue(menorValorDeEntrega.getPontos().get(1).equals("B"));
    }

    private void popularRotas() {
        List<Rota> rotas = new ArrayList<Rota>();
        rotas.add(new Rota("A", "B", 10));
        rotas.add(new Rota("B", "D", 15));
        rotas.add(new Rota("A", "C", 20));
        rotas.add(new Rota("C", "D", 30));
        rotas.add(new Rota("B", "E", 50));
        rotas.add(new Rota("D", "E", 30));
        mapaService.adicionarMapa(rotas, "Mapa de teste");
    }
}
