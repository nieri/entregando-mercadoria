package br.com.munieri.entregando.mercadoria.domain.service;

import br.com.munieri.entregando.mercadoria.domain.Mapa;
import br.com.munieri.entregando.mercadoria.domain.Rota;

import java.util.List;

public interface MapaService {

    public Mapa adicionarMapa(List<Rota> rotas, String nome);

    public List<Rota> getMenorRota(String origem, String destino);

}
