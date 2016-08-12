package br.com.munieri.entregando.mercadoria.calculo;

import static org.junit.Assert.assertTrue;

import br.com.munieri.entregando.mercadoria.domain.calculo.CalculaCustoViagem;
import org.junit.Test;

public class CalculaCustoViagemTest {

    @Test
    public void deve_retornar_custo_viagem() {
        Integer distancia = 100;
        Double valorLitro = 2.5;
        Double autonomia = 10.0;
        CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
        Double custoTotal = calculaCustoViagem.getCustoTotal();
        assertTrue("Custo Total: " + custoTotal, custoTotal.equals(25.0));
    }

    @Test
    public void deve_retornar_custo_viagem_valores_decimais() {
        Integer distancia = 33;
        Double valorLitro = 2.5;
        Double autonomia = 7.3;
        CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
        Double custoTotal = calculaCustoViagem.getCustoTotal();
        assertTrue("Custo Total: " + custoTotal, custoTotal.equals(11.3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deve_validar_parametro_nulo() {
        Integer distancia = 33;
        Double valorLitro = null;
        Double autonomia = 7.3;
        CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
        calculaCustoViagem.getCustoTotal();
    }
}