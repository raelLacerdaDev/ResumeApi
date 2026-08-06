# Portfólio API

API RESTful desenvolvida para gerenciar e fornecer os dados do meu portfólio pessoal. A aplicação atende a múltiplos clientes, incluindo uma aplicação Kotlin Multiplatform (KMP) e um site em modo somente leitura.

## 🚀 Tecnologias Utilizadas

* **Backend:** Spring Boot (Java/Kotlin)
* **Banco de Dados:** PostgreSQL
* **Clientes:** KMP (Kotlin Multiplatform), Web Client (Read-only)
* **Arquitetura e Padrões:** Layered Architecture, DTOs, ORM

## 🏗️ Arquitetura do Sistema

### Visão Geral (Infraestrutura)
A comunicação do sistema foi projetada para separar requisições autenticadas de requisições públicas de leitura:

* O **Client KMP** realiza requisições autenticadas (via middleware de Auth) para a API.
* O **Site Client** realiza apenas requisições de leitura (GET) públicas para exibição do portfólio.
* A API gerencia a lógica e persiste os dados no **PostgreSQL**.

> **Nota:** Adicione a `image_8c6336.png` aqui no seu repositório.

### Arquitetura Interna da API
O projeto no Spring Boot segue uma arquitetura em camadas bem definida para garantir a separação de responsabilidades:

1. **Controller:** Ponto de entrada da aplicação que recebe e retorna `JSON` para o Client.
2. **Service:** Camada que contém a regra de negócio. A comunicação com o Controller é feita de forma isolada utilizando `DTOs` (Data Transfer Objects).
3. **Repository:** Camada responsável pela persistência de dados no banco, comunicando-se com o Service através de `Entities/ORM`.

> **Nota:** Adicione a `image_8c107f.png` aqui no seu repositório.

## ⚙️ Como Executar

1. Clone este repositório.
2. Configure as credenciais do PostgreSQL no arquivo `application.properties` ou `application.yml`.
3. Execute o projeto utilizando o Maven ou Gradle:
   `./mvnw spring-boot:run` ou `./gradlew bootRun`

## 📜 Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.