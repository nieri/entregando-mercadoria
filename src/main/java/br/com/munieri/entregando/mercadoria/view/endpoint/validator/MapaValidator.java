package br.com.munieri.entregando.mercadoria.view.endpoint.validator;

import br.com.munieri.entregando.mercadoria.view.endpoint.MapaDTO;
import br.com.munieri.entregando.mercadoria.view.endpoint.RotaDTO;
import org.eclipse.jetty.util.StringUtil;

public class MapaValidator {

    public static void check(MapaDTO dto) {

        checkCampo(dto.getNome(), "nome");
        ckeckRotas(dto);

        for (RotaDTO rota : dto.getRotas()) {

            checkCampo(rota.getOrigem(), "origem");
            checkCampo(rota.getDestino(), "destino");
            checkCampo(rota.getDistancia().toString(), "distancia");
        }
    }

    private static void ckeckRotas(MapaDTO dto) {
        if (dto.getRotas() == null){
            throw new IllegalArgumentException("rotas");
        }
    }

    private static void checkCampo(String campo, String codigo) {
        if (StringUtil.isBlank(campo)) {
            throw new IllegalArgumentException(codigo);
        }
    }
}
