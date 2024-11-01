-- GynLivros.sql
-- Criação da tabela Cliente
CREATE TABLE Cliente (
    ID_Cliente SERIAL PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Telefone VARCHAR(20),
    Email VARCHAR(100),
    Endereco VARCHAR(255),
    Tipo_do_Cliente VARCHAR(50),
    CPF VARCHAR(14),
    Sexo CHAR(1),
    Data_Nascimento DATE,
    CNPJ VARCHAR(18),
    Razao_Social VARCHAR(255),
    Inscricao_Estadual VARCHAR(20)
);

-- Criação da tabela Editora
CREATE TABLE Editora (
    ID_Editora SERIAL PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Endereco VARCHAR(255),
    Telefone VARCHAR(20),
    Nome_Gerente VARCHAR(100)
);

-- Criação da tabela Autor
CREATE TABLE Autor (
    ID_Autor SERIAL PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL
);

-- Criação da tabela Assunto
CREATE TABLE Assunto (
    ID_Assunto SERIAL PRIMARY KEY,
    Descricao VARCHAR(255) NOT NULL
);

-- Criação da tabela Livro
CREATE TABLE Livro (
    ISBN VARCHAR(13) PRIMARY KEY,
    Titulo VARCHAR(255) NOT NULL,
    Quantidade INT,
    ID_Editora INT,
    ID_Assunto INT,
    FOREIGN KEY (ID_Editora) REFERENCES Editora(ID_Editora),
    FOREIGN KEY (ID_Assunto) REFERENCES Assunto(ID_Assunto)
);

-- Criação da tabela Autoria
CREATE TABLE Autoria (
    ID_Autor INT,
    ISBN VARCHAR(13),
    PRIMARY KEY (ID_Autor, ISBN),
    FOREIGN KEY (ID_Autor) REFERENCES Autor(ID_Autor),
    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN)
);

-- Criação da tabela Compra
CREATE TABLE Compra (
    ID_Compra SERIAL PRIMARY KEY,
    ISBN VARCHAR(13),
    ID_Cliente INT,
    FOREIGN KEY (ISBN) REFERENCES Livro(ISBN),
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente)
);
