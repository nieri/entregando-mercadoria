package br.com.munieri.entregando.mercadoria.view.endpoint;

import br.com.munieri.entregando.mercadoria.domain.Mapa;
import br.com.munieri.entregando.mercadoria.domain.Rota;
import br.com.munieri.entregando.mercadoria.domain.calculo.CalculaCustoViagem;
import br.com.munieri.entregando.mercadoria.domain.service.MapaService;
import br.com.munieri.entregando.mercadoria.view.endpoint.validator.MapaValidator;
import br.com.munieri.entregando.mercadoria.view.endpoint.validator.MenorValorDeEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/services")
public class EntregaEndPoint {

    @Autowired
    private MapaService mapaService;

    @RequestMapping(value = "/menorValorDeEntrega", method = RequestMethod.GET)
    public @ResponseBody
    MenorValorDeEntrega menorRota(@RequestParam("origem") String origem,
                                  @RequestParam("destino") String destino,
                                  @RequestParam("autonomia") Double autonomia,
                                  @RequestParam("valorLitroCombustivel") Double valorLitroCombustivel) {

        MenorValorDeEntrega menorValorDeEntrega = new MenorValorDeEntrega();
        List<Rota> menorRota = mapaService.getMenorRota(origem, destino);
        Integer distanciaTotal = 0;
        menorValorDeEntrega.getPontos().add(menorRota.get(0).getOrigem());
        for (Rota rota : menorRota) {
            menorValorDeEntrega.getPontos().add(rota.getDestino());
            distanciaTotal += rota.getDistancia();
        };

        CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitroCombustivel, distanciaTotal, autonomia);
        menorValorDeEntrega.setValor(calculaCustoViagem.getCustoTotal());
        return menorValorDeEntrega;
    }

    @RequestMapping(value = "/novoMapa", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> novoMapa(@RequestBody MapaDTO mapaDTO) {

        MapaValidator.check(mapaDTO);

        List<RotaDTO> rotasDTO = mapaDTO.getRotas();

        List<Rota> rotas = new ArrayList<Rota>();

        for (RotaDTO rotaDTO : rotasDTO) {
            Rota rota = new Rota(rotaDTO.getOrigem(), rotaDTO.getDestino(), rotaDTO.getDistancia());
            rotas.add(rota);
        }

        Mapa mapa = mapaService.adicionarMapa(rotas, mapaDTO.getNome());

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO errorHandler(IllegalArgumentException ex, HttpServletResponse response) {
        return new ErrorDTO(ex.getMessage(), "Informe o campo " + ex.getMessage());
    }
}
