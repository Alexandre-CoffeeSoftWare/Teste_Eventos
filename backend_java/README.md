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









