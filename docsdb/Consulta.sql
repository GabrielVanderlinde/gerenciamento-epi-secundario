--
-- 1. CONFIGURAÇÃO E CRIAÇÃO DO BANCO DE DADOS
--
-- Remove o banco de dados se ele já existir (para garantir um ambiente limpo)
DROP DATABASE IF EXISTS gerenciamento_epi;

-- Cria o banco de dados usando a codificação compatível (utf8mb4_general_ci)
CREATE DATABASE IF NOT EXISTS gerenciamento_epi
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

-- Seleciona o banco de dados para as operações subsequentes
USE gerenciamento_epi;

--
-- 2. CRIAÇÃO DAS TABELAS (SCHEMA DDL)
--

-- Tabela: colaborador
CREATE TABLE IF NOT EXISTS colaborador (
    id_colaborador INT AUTO_INCREMENT PRIMARY KEY,
    nome_colaborador VARCHAR(120) NOT NULL COMMENT 'Nome completo do colaborador.',
    setor_colaborador VARCHAR(80) COMMENT 'Setor ou departamento.',
    status_colaborador CHAR(1) NOT NULL DEFAULT '1' COMMENT 'Status: 1 (Ativo) ou 0 (Inativo).',
    UNIQUE INDEX idx_nome_colaborador_unique (nome_colaborador),
    CONSTRAINT chk_status_colaborador CHECK (status_colaborador IN ('0', '1'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabela: epi
CREATE TABLE IF NOT EXISTS epi (
    id_epi INT AUTO_INCREMENT PRIMARY KEY,
    nome_epi VARCHAR(120) NOT NULL COMMENT 'Nome do EPI.',
    descricao VARCHAR(255) COMMENT 'Breve descrição do EPI.',
    status_epi BOOLEAN NOT NULL COMMENT 'Disponibilidade: 1 (Disponível) ou 0 (Indisponível/Em Uso).',
    UNIQUE INDEX idx_nome_epi_unique (nome_epi)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabela: emprestimo
CREATE TABLE IF NOT EXISTS emprestimo (
    id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    data_emprestimo DATE NOT NULL COMMENT 'Data em que o EPI foi emprestado.',
    data_devolucao DATE COMMENT 'Data em que o EPI foi devolvido.',
    status VARCHAR(20) NOT NULL COMMENT 'Status do empréstimo: ATIVO, DEVOLVIDO, ATRASADO.',
    id_colaborador INT NOT NULL COMMENT 'Chave estrangeira para a tabela colaborador.',
    id_epi INT NOT NULL COMMENT 'Chave estrangeira para a tabela epi.',
    CONSTRAINT fk_emprestimo_colaborador FOREIGN KEY (id_colaborador)
        REFERENCES colaborador (id_colaborador)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_emprestimo_epi FOREIGN KEY (id_epi)
        REFERENCES epi (id_epi)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_status_emprestimo CHECK (status IN ('ATIVO', 'DEVOLVIDO', 'ATRASADO'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 3. INSERÇÃO DE DADOS INICIAIS (DML - Seed Data)
--

-- Colaboradores (ID: 1 a 4)
INSERT INTO colaborador (nome_colaborador, setor_colaborador, status_colaborador) VALUES
('João Silva', 'Produção', '1'),
('Maria Souza', 'Administrativo', '1'),
('Pedro Costa', 'Manutenção', '1'),
('Ana Lúcia', 'Recursos Humanos', '0');

-- EPIs (ID: 1 a 4)
INSERT INTO epi (nome_epi, descricao, status_epi) VALUES
('Capacete de Segurança', 'Capacete de uso geral, branco.', 1),
('Luvas de Raspa', 'Luvas de proteção para solda.', 0),
('Óculos de Proteção', 'Óculos transparentes anti-risco.', 0),
('Abafador de Ruído', 'Protetor auricular tipo concha.', 1);

-- Empréstimos (Simulando 3 cenários)
INSERT INTO emprestimo (data_emprestimo, data_devolucao, status, id_colaborador, id_epi) VALUES
(DATE_SUB(CURDATE(), INTERVAL 10 DAY), NULL, 'ATIVO', 1, 2),
(DATE_SUB(CURDATE(), INTERVAL 30 DAY), DATE_SUB(CURDATE(), INTERVAL 25 DAY), 'DEVOLVIDO', 2, 1),
(DATE_SUB(CURDATE(), INTERVAL 30 DAY), DATE_SUB(CURDATE(), INTERVAL 15 DAY), 'ATIVO', 3, 3);