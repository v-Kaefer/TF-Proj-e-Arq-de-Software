# TF-Proj-e-Arq-de-Software
 Trabalho Final pt1, da matéria de Projeto e Arquitetura de Software


#### Testes unitários com perfil e/ou db separada:

- Utilizar `@ActiveProfiles("test")`, para identificar como perfil de teste.
    - Então, preciso configurar um `application-test.properties`, que vai indicar o db separado, para testes. [Considerar teste - perguntar para o professor].




# Apresentação T.F. Parte 2


### Eureka
http://localhost:8761

### Teste Câmbio (Exchange)
http://localhost:8000/currency-exchange/from/USD/to/INR

### Teste Conversão de Moeda (Conversion) + Name Server (Proxy)
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

### Teste Information Collector
http://localhost:8200/consultas

### Teste Actuator
http://localhost:8000/actuator/health


#### Remove imagens inutilizadas
docker rmi $(docker images --filter "dangling=true" -q --no-trunc)