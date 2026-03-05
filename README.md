# Case Itaú - Módulo de Cadastro de Chaves PIX

## 💻 Sobre o Projeto
Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar o cadastro, alteração, inativação e consulta de chaves PIX. O projeto foi construído como resolução de um *case* técnico focado em arquitetura bancária, aplicando rigorosas regras de negócio, validações de formato e boas práticas de engenharia de software.

## 🛠️ Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.2.3** (Web, Data JPA, Validation)
* **H2 Database** (Banco de dados em memória)
* **Maven** (Gerenciador de dependências)

## ⚙️ Arquitetura e Boas Práticas Aplicadas
* **Padrão Strategy:** Utilizado para isolar e aplicar as regras complexas de validação matemática de cada formato de chave (CPF, CNPJ, Celular, E-mail), respeitando o princípio *Open-Closed* do SOLID.
* **Padrão DTO (Data Transfer Object):** Implementado para separar a camada de visualização/entrada de dados da camada de persistência (Entidades), garantindo segurança e controle sobre o contrato da API.
* **Consultas Dinâmicas (Specification):** Utilização da interface `JpaSpecificationExecutor` do Spring Data JPA para permitir buscas combinadas e opcionais no banco de dados com alta performance e código limpo.
* **Inativação Lógica (Soft Delete):** Deleção de registros feita de forma lógica, preenchendo a data e hora da inativação para fins de auditoria, ao invés de apagar o dado fisicamente.
