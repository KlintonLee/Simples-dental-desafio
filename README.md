# Desafio-REST Controle de Cadastro
## Descrição
Este projeto visa testar conhecimentos técnicos ao desenvolver uma API REST em Java para 
gerenciar um sistema de controle de cadastro de profissionais e seus números de contato.

## Funcionalidades
Cadastro e gerenciamento de profissionais, incluindo informações como nome, cargo e data de nascimento.

Registro e manutenção de números de contato associados a cada profissional, categorizados por tipo (por exemplo: fixo, celular, escritório).

Armazenamento dos dados em um banco de dados com duas tabelas: contatos e profissionais.

## Principais Tecnologias Utilizadas
- Java 17+
- Spring Boot (para desenvolvimento da API REST)
- Banco de dados Postgres (para armazenamento dos dados)

## Estrutura do Banco de Dados
- Tabela contatos
  - id: Identificador único do contato (gerado automaticamente pelo banco de dados).
  - nome: Tipo de contato (por exemplo: fixo, celular, escritório).
  - contato: Número de contato.
  - created_date: Data de criação do registro.
- Tabela profissionais
  - id: Identificador único do profissional (gerado automaticamente pelo banco de dados).
  - nome: Nome do profissional.
  - cargo: Cargo do profissional (ENUM - Desenvolvedor, Designer, Suporte, Tester).
  - nascimento: Data de nascimento do profissional.
  - created_date: Data de criação do registro.

## Endpoints
Ao subir a aplicação, o swagger-ui estará disponível em http://localhost:8080/swagger-ui/index.html

## Como Contribuir
Faça um fork do repositório.

Crie uma branch com a sua feature: git checkout -b feature/nova-feature.

Faça commit das suas mudanças: git commit -m 'Adicionando nova feature'.

Faça push para a sua branch: git push origin feature/nova-feature.

Envie um pull request.