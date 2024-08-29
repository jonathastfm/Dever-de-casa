CREATE TABLE Professor (
    matricula INT PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    escola VARCHAR(100) NOT NULL,
    titulacao VARCHAR(50) NOT NULL,
    ddi1 INT NOT NULL,
    ddd1 INT NOT NULL,
    numero1 INT NOT NULL,
    ddi2 INT NULL,
    ddd2 INT NULL,
    numero2 INT  NULL,
    ddi3 INT NULL,
    ddd3 INT NULL,
    numero3 INT NULL,
    email1 VARCHAR(100) NOT NULL,
    email2 VARCHAR(100)  NULL,
    email3 VARCHAR(100)  NULL,
    email4 VARCHAR(100)  NULL,
    lougradouro1 VARCHAR(100) NOT NULL,
    numero_casa1 INT NOT NULL,
    complemento1 VARCHAR(100) NOT NULL,
    lougradouro2 VARCHAR(100) NULL,
    numero_casa2 INT NULL,
    complemento2 VARCHAR(100) NULL,
    sexo CHAR(1) NOT NULL
);

-- Incluir 3 professores na tabela
INSERT INTO professores (matricula, nome, cpf, escola, titulacao, ddi1, ddd1, numero1, ddi2, ddd2, numero2, ddi3, ddd3, numero3, email1, email2, email3, email4, lougradouro1, numero_casa1, complemento1, lougradouro2, numero_casa2, complemento2, sexo)
VALUES
    (1, 'Professor 1', '12345678901', 'Escola 1', 'Titulação 1', 55, 11, 12345678, 55, 11, 87654321, 55, 11, 13579246, 'email1@example.com', 'email2@example.com', 'email3@example.com', 'email4@example.com', 'Rua 1', 123, 'Complemento 1', 'Rua 2', 456, 'Complemento 2', 'M'),
    (2, 'Professor 2', '98765432109', 'Escola 2', 'Titulação 2', 55, 11, 23456789, 55, 11, 98765432, 55, 11, 24681357, 'email5@example.com', 'email6@example.com', 'email7@example.com', 'email8@example.com', 'Rua 3', 789, 'Complemento 3', 'Rua 4', 987, 'Complemento 4', 'F'),
    (3, 'Professor 3', '45678901234', 'Escola 3', 'Titulação 3', 55, 11, 34567890, 55, 11, 12345678, 55, 11, 35792468, 'email9@example.com', 'email10@example.com', 'email11@example.com', 'email12@example.com', 'Rua 5', 321, 'Complemento 5', 'Rua 6', 654, 'Complemento 6', 'M');

-- Alterar os dados de 2 professores na tabela
UPDATE professores
SET nome = 'Novo Nome', cpf = '98765432100'
WHERE matricula = 1;

UPDATE professores
SET nome = 'Outro Nome', cpf = '12345678900'
WHERE matricula = 2;

-- Consultar todos os dados na tabela
SELECT * FROM professores;

-- Excluir os dados de 2 professores da tabela
DELETE FROM professores
WHERE matricula IN (1, 2);

-- Excluir a tabela
DROP TABLE professores;

-- Excluir o banco
DROP DATABASE nome_do_banco;