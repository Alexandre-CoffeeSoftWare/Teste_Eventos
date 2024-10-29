## Pré requisitos

* JDK 17
* Maven 3.8.3
* lombok

## Descrição do projeto

O Projeto foi desenvolvido para teste de CRUD, por Alexandre E. C.
O objetivo do projeto é possibilitar o cadastro de instituições e eventos

### Rodando o projeto
1. Execute o comando para realizar o download das dependencias
```bash
  mvn clean install
```
2. Execute o projeto pela IDE ou no diretorio do projeto via terminal com o comando:
```bash
  mvn spring-boot:run
```
3. Acesso o Swagger do projeto pela URL
```bash
  http://localhost:8080/swagger-ui/index.html#/
```
4. Acesso o banco de dados pela URL e credenciais
```bash
  URL: http://localhost:8080/h2-console/login.jsp
  Usuario: teste
  Senha: teste
```

### Entendendo a estrutura projeto
1. resouce - Classes responsaveis pela entrada de dados (endpoints)
 api - Interfaces de configuração do OpenAPI para exebição do swagger
2. service - Classes responsaveis pelas classes de serviço, onde as regras negociais são executadas.
3. repository - Classes responsáveis pelo acesso ao banco de dados
4. entity - Classes que representam as entidades do banco de dados e contém as configurações de mapeamento do JPA/Jarkarta
5. dto - Classes que representam a troca de dados entre o client e a API (requests e responses)
6. constantes - Classes de contantes (Enum)
7. config - Classes de configuração da aplicação, como o caso do agendador de eventos Cro

### Inativando eventos vencidos
1. Foi adicionado a anotação @EnableScheduling na classe EventosApplication para que o spring permita o agendamento de eventos
2. Foi criado a classe Agendador que possue uma anotação @Scheduled e configuração para rodar o agendamento a cada 1 minuto
3. Na interface EventoRepository foi criado um metodo chamado updateEventosVencidos que executa um update no banco inativando todos os eventos
com data final menor que a data atual.

OBS: Para testar a rotina basta rodar o projeto, cadastrar uma instituição, cadastrar um evento com a data inferior a data atual e aguardar a proxima execução da cron.


# Guia do Projeto

## Índice
- [Pré-requisitos](#pré-requisitos)
- [Descrição do Projeto](#descrição-do-projeto)
- [Rodando o Projeto](#rodando-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Funcionalidade de Inativação de Eventos Vencidos](#funcionalidade-de-inativação-de-eventos-vencidos)
  - [Testando a Rotina de Inativação](#testando-a-rotina-de-inativação)

## Pré-requisitos

- **JDK 17** - Ambiente de execução para o Java Development Kit na versão 17.
- **Maven 3.8.3** - Ferramenta de automação de compilação para gerenciamento de dependências e execução do projeto.
- **Lombok** - Biblioteca Java usada para simplificar o código, especialmente com anotações de boilerplate.

## Descrição do Projeto

Este projeto foi desenvolvido por Alexandre E. C. como um teste de CRUD (Create, Read, Update, Delete). Ele permite o cadastro e gerenciamento de instituições e eventos.

## Rodando o Projeto

1. Realize o download das dependências com o comando abaixo:
    ```bash
    mvn clean install
    ```
2. Para iniciar o projeto, use sua IDE ou execute o comando no diretório do projeto:
    ```bash
    mvn spring-boot:run
    ```
3. Acesse a documentação Swagger da API pela URL:
    ```bash
    http://localhost:8080/swagger-ui/index.html#/
    ```
4. Acesse o console do banco de dados H2 pela URL e credenciais abaixo:
    - **URL**: `http://localhost:8080/h2-console/login.jsp`
    - **Usuário**: `teste`
    - **Senha**: `teste`

## Estrutura do Projeto

Abaixo, uma breve descrição das principais pastas e pacotes:

1. **resource** - Classes responsáveis pelos endpoints de entrada de dados da API.
   - **api** - Interfaces de configuração do OpenAPI para exibição da documentação Swagger.

2. **service** - Classes de serviço onde as regras de negócio são implementadas.

3. **repository** - Classes responsáveis pelo acesso ao banco de dados.

4. **entity** - Classes que representam as entidades do banco de dados e contêm as configurações de mapeamento do JPA/Jakarta.

5. **dto** - Classes que representam os dados de entrada e saída entre o cliente e a API (Requests e Responses).

6. **constantes** - Definições de constantes (enums) utilizadas no projeto.

7. **config** - Classes de configuração do sistema, como a configuração do agendador de eventos (Cron).

## Funcionalidade de Inativação de Eventos Vencidos

Para automatizar a inativação de eventos com data vencida, foi implementado um agendador com as seguintes etapas:

1. **Agendamento**: A anotação `@EnableScheduling` foi adicionada na classe `EventosApplication` para permitir agendamentos no Spring.

2. **Classe Agendador**: Foi criada a classe `Agendador` com o método `@Scheduled` configurado para rodar a cada 1 minuto. Este método chama o serviço que atualiza o status dos eventos vencidos.

3. **Repositório Evento**: A interface `EventoRepository` inclui o método `updateEventosVencidos`, que executa uma atualização no banco para inativar todos os eventos cuja data final é anterior à data atual.

### Testando a Rotina de Inativação

Para testar a rotina de inativação de eventos:

1. Inicie o projeto.
2. Cadastre uma instituição e em seguida, um evento com data final anterior à data atual.
3. A cada minuto, o agendador executará a rotina de verificação, e o evento será automaticamente inativado na próxima execução do cron.

---