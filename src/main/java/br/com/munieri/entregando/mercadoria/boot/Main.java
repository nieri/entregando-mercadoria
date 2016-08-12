package br.com.munieri.entregando.mercadoria.boot;

import br.com.munieri.entregando.mercadoria.boot.server.ApplicationServer;
import br.com.munieri.entregando.mercadoria.boot.server.WebServer;

public class Main {

    public static void main(String[] args) throws Exception {
        WebServer server = new ApplicationServer();
        server.start();
    }
}
