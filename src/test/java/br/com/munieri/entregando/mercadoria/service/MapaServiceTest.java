package br.com.munieri.entregando.mercadoria.service;

import java.util.ArrayList;
import java.util.List;

import br.com.munieri.entregando.mercadoria.AppConfWebTest;
import br.com.munieri.entregando.mercadoria.domain.Mapa;
import br.com.munieri.entregando.mercadoria.domain.Rota;
import br.com.munieri.entregando.mercadoria.domain.service.MapaService;
import br.com.munieri.entregando.mercadoria.infrastructure.repository.MapaRepository;
import br.com.munieri.entregando.mercadoria.infrastructure.repository.RotaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class MapaServiceTest extends AppConfWebTest {

    @Autowired
    private MapaService mapaService;

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private RotaRepository rotaRepository;

    @Test
    public void deveAdicionarMapa() {
        List<Rota> rotas = new ArrayList<Rota>();
        Mapa mapa = mapaService.adicionarMapa(rotas, "Novo Mapa");

        Mapa mapaBD = mapaRepository.findOne(mapa.getId());
        assertTrue(mapaBD.getNome().equals(mapa.getNome()));
    }


    @Test
    public void deveAdicionarRotasNoMapa() {
        List<Rota> rotas = new ArrayList<Rota>();
        for (int i = 0; i < 50; i++) {
            rotas.add(new Rota(String.valueOf(i), String.valueOf(i+1), i+100));
        }
        mapaService.adicionarMapa(rotas, "Novo Mapa 2");

        List<Rota> rotasBD = (List<Rota>) rotaRepository.findAll();
        assertTrue(rotasBD.size() == rotas.size());
    }
}