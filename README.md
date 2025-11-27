# Sistema de Gerenciamento de EPIs (SGE) - Versão 1.0
Sistema corporativo em Java/Spring Boot para controle de segurança do trabalho.

## Autores
- Desenvolvido por Gabriel Vanderlinde, Jonathan, Matheus Bagatolli e Rhudsson
- SENAI 2025

Sistema desenvolvido em Java com Spring Boot.

## Funcionalidades Principais

- **Gestão de Colaboradores:** Cadastro, atualização e inativação de funcionários.
- **Controle de Estoque:** Cadastro de EPIs e monitoramento de disponibilidade.
- **Empréstimos Seguros:**
    - O sistema impede empréstimos se o EPI estiver indisponível.
    - O sistema impede empréstimos para colaboradores inativos.
- **Histórico:** Registro de data de empréstimo e devolução.
- **Interface:** Menu interativo via Terminal (CLI) limpo e intuitivo.

## Tecnologias
- **Java:** 17
- **Spring Boot:** 4.0.0
- **Banco:** MySQL
- **Build:** Maven

## Como rodar o projeto

### Pré-requisitos
Certifique-se de ter instalado:
1. **Java JDK 17**
2. **MySQL Server** rodando na porta `3306`
