
# Serviço de Entrega de Mercadoria

##Sistema que compreende:
- Armazenar a malha logistica (mapa)
- Traçar o caminho com menor valor de entrega

##Tecnologias utilizadas:
- Spring 4
- Spring-data
- Hibernate
- Jackson 2
- Embedded Jetty 9 (br.com.munieri.entregando.mercadoria.boot.Main.java)

### Para os testes Unitarios
- Junit
- Spring-test

### Para os testes Integrados
- Junit
- Spring-test

### Banco de dados
O Banco de dados esta configurado no pacote [br.com.munieri.entregando.mercadoria.boot].
Foi utilizado um banco in-memory(hsqldb) para fins de teste e persistencia.


## Serviços
###Serviço para cadastrar novo Mapa
O servico esta documentado atraves do teste integrado em EntregaEndPointIT.java

###Serviço para cadastrar novo Mapa
O servico esta documentado atraves do teste integrado em EntregaEndPointIT.java

###Passo a passo:

* Criar banco de dados:
```
create database odara;

```

* Atualizar os dados do datasource (url, username, password) nos arquivos ./entregando-mercadorias/src/main/resources/application-dev.properties | application-test.properties
```
jdbc.url=jdbc:mysql://localhost:3307/odara
jdbc.user=root
jdbc.pass=
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
hibernate.driver=com.mysql.jdbc.Driver

```

* Compilar o projeto

Entre na pasta ./entregando-mercadoria 
Execute:
```
mvn package
```

* Execute o projeto:
```
java -jar target\entregando-mercadoria-0.0.1-SNAPSHOT.jar
```

Nesse momento o serviço está funcionando!

Serviços:
```
GET http://localhost:8080/services/menorValorDeEntrega
POST http://localhost:8080/services/novoMapa
```

**Exemplo de parâmetro do serviço "menorValorDeEntrega"**
```
?origem=A&destino=F&autonomia=10&valorLitroCombustivel=2.5
```

**Exemplo de request para o serviço "novoMapa"**
```
{
   "nome":"MAPA DE SP",
   "rotas":[
      {
         "origem":"A",
         "destino":"B",
         "distancia":5
      },
      {
         "origem":"A",
         "destino":"C",
         "distancia":10
      },
      {
         "origem":"B",
         "destino":"D",
         "distancia":3
      },
      {
         "origem":"B",
         "destino":"G",
         "distancia":10
      },
      {
         "origem":"C",
         "destino":"D",
         "distancia":10
      },
      {
         "origem":"C",
         "destino":"E",
         "distancia":10
      },
      {
         "origem":"D",
         "destino":"F",
         "distancia":10
      },
      {
         "origem":"D",
         "destino":"E",
         "distancia":5
      },
      {
         "origem":"E",
         "destino":"F",
         "distancia":4
      },
      {
         "origem":"E",
         "destino":"H",
         "distancia":1
      },
      {
         "origem":"G",
         "destino":"F",
         "distancia":10
      },
      {
         "origem":"H",
         "destino":"F",
         "distancia":1
      }
   ]
}
```

O sistema está disponivel na web através dos caminhos:

```
http://localhost:8080/services/novoMapa
http://localhost:8080/services/menorValorDeEntrega
```
