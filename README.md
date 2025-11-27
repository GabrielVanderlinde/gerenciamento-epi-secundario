# Sistema de Gerenciamento de EPIs (SGE) - Versão 1.0
Sistema corporativo em Java/Spring Boot para controle de segurança do trabalho.
## Alunos

Gabriel Vanderlinde
Jonathan
Matheus Bagatolli
Rhudsson

Sistema desenvolvido em Java com Spring Boot.

## Funcionalidades Principais

- **Gestão de Colaboradores:** Cadastro, atualização e inativação de funcionários.
- **Controle de Estoque:** Cadastro de EPIs e monitoramento de disponibilidade.
- **Empréstimos Seguros:**
    - O sistema impede empréstimos se o EPI estiver indisponível.
    - O sistema impede empréstimos para colaboradores inativos.
- **Histórico:** Registro de data de empréstimo e devolução.
- **Interface:** Menu interativo via Terminal (CLI) limpo e intuitivo.

## Tecnologias Utilizadas

- **Linguagem:** Java 17 (LTS)
- **Framework:** Spring Boot 3
- **Persistência:** Spring Data JPA & Hibernate
- **Banco de Dados:** MySQL
- **Gerenciador de Dependências:** Maven
- **Design Pattern:** MVC (Model-View-Controller) e DTO (Data Transfer Object)

## Como rodar o projeto

### Pré-requisitos
Certifique-se de ter instalado:
1. **Java JDK 17**
2. **MySQL Server** rodando na porta `3306`
