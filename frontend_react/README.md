
# Avaliação React

Este projeto foi desenvolvido como um teste prático em React, visando implementar um CRUD básico para gerenciar instituições e eventos, integrando um backend para armazenamento e consulta dos dados.

## Índice
- [Sobre o Projeto](#sobre-o-projeto)
- [Instalação e Configuração](#instalacao-e-configuracao)
- [Utilização das Telas](#utilizacao-das-telas)
  - [Instituição](#instituicao)
  - [Evento](#evento)
- [Observações](#observacoes)

## Sobre o Projeto
O projeto foi criado para proporcionar uma interface leve e funcional que permita realizar operações de CRUD (Criar, Ler, Atualizar, Excluir) sobre instituições e eventos. Com isso, o projeto explora conceitos de interação com backend e apresentação dos dados de maneira intuitiva.

## Instalação e Configuração

### Requisitos
- [Node.js](https://nodejs.org/) instalado na máquina (inclui o npm).

### Passos para Instalação

1. **Instalar Dependências**
   ```bash
   npm install
   ```

2. **Iniciar o Servidor de Desenvolvimento**
   ```bash
   npm start
   ```

3. **Parar o Servidor de Desenvolvimento**
   Pressione `Ctrl + C` no terminal para interromper o servidor.

4. **Recriar a Aplicação (Build de Produção)**
   ```bash
   npm run build
   ```

## Utilização das Telas
No menu lateral esquerdo, você encontrará acesso às telas de cadastro de Instituições e Eventos. As funcionalidades das telas incluem:

### Instituição
- **Abrir**: Clique uma vez no menu para abrir a tela de Instituições.
- **Fechar**: Clique novamente no menu ou feche usando o "X" no formulário.
- **Consultar**: A consulta é exibida automaticamente ao abrir a tela.
- **Novo**: Clique em "Novo" para abrir um formulário em branco para adicionar uma nova instituição.
- **Salvar**: Clique em "Salvar" para enviar os dados ao backend, que salvará as informações.
- **Editar**: Selecione um item da lista para exibir e editar seus dados no formulário.
- **Excluir**: Selecione um item para excluí-lo, com confirmação simplificada para garantir rapidez no desenvolvimento.
  
> Algumas validações, como campos obrigatórios, foram implementadas para garantir a integridade dos dados.

### Evento
- **Abrir**: Clique uma vez no menu para abrir a tela de Eventos.
- **Fechar**: Clique novamente no menu ou feche usando o "X" no formulário.
- **Consultar**: A consulta é exibida automaticamente ao abrir a tela.
- **Novo**: Clique em "Novo" para abrir um formulário em branco para adicionar um novo evento.
- **Salvar**: Clique em "Salvar" para enviar os dados ao backend, que salvará as informações.
- **Editar**: Selecione um item da lista para exibir e editar seus dados no formulário.
- **Excluir**: Selecione um item para excluí-lo, com confirmação simplificada para garantir rapidez no desenvolvimento.

> Algumas validações foram implementadas, incluindo mensagens para campos vazios.

## Observações
Para focar no desenvolvimento do backend e reduzir o tempo total de execução, nem todas as boas práticas e metodologias foram aplicadas de forma completa. A prioridade foi dada à implementação das funcionalidades principais e ao design simplificado.
