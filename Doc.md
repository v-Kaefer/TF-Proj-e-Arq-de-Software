# Documentação do Programa
### Sumário
1. Introdução
2. Arquitetura
3. Funcionalidades
4. Endpoints da API
5. Configuração do Projeto
6. Testes Automatizados
7. Scripts de Inicialização
8. Documentação da API com Swagger
9. Conclusão

### Introdução
Este documento descreve a implementação de um sistema de assinaturas usando a arquitetura CLEAN em Java com Spring Boot. O sistema permite gerenciar clientes, aplicativos, assinaturas e pagamentos, incluindo a lógica de extensão de validade das assinaturas e o tratamento de promoções.

### Arquitetura
A arquitetura CLEAN divide o sistema em camadas bem definidas:

1. Entities: Contém as entidades de domínio.
2. Use Cases: Contém a lógica de negócios.
3. Interface Adapters: Contém os controladores e gateways.
4. Frameworks & Drivers: Contém a infraestrutura, como banco de dados e frameworks web.

### Funcionalidades
* Gerenciamento de Clientes: Cadastro e manutenção de clientes.
* Gerenciamento de Aplicativos: Cadastro e manutenção de aplicativos.
* Gerenciamento de Assinaturas: Cadastro e manutenção de assinaturas.
* Processamento de Pagamentos: Processamento de pagamentos e extensão de validade das assinaturas.
* Promoções: Aplicação de promoções para estender o período de validade das assinaturas.

### Endpoints da API

**ClienteController**
- **GET /api/clientes**: Lista todos os clientes.
- **POST /api/clientes**: Cria um novo cliente.

**AplicativoController**
**GET /api/aplicativos**: Lista todos os aplicativos.
**POST /api/aplicativos**: Cria um novo aplicativo.

**AssinaturaController**
**POST /api/assinaturas**: Cria uma nova assinatura.

**PagamentoController**
**POST /api/pagamentos**: Processa um pagamento.