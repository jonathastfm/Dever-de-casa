
CREATE TABLE Consultor (
    cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    nome_completo VARCHAR(100) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero_endereco INT NOT NULL,
    ddi1 VARCHAR(3) NOT NULL,
    ddd1 VARCHAR(3) NOT NULL,
    numero_telefone1 VARCHAR(10) NOT NULL,
    unique (ddi1, ddd1, numero_telefone1),

    ddi2 VARCHAR(3) NULL,
    ddd2 VARCHAR(3) NULL,
    numero_telefone2 VARCHAR(10) NULL,
    unique (ddi2, ddd2, numero_telefone2),

    email1 VARCHAR(100) NOT NULL,
    email2 VARCHAR(100) NULL,
    cnpj_Projeto VARCHAR(14) NOT NULL,
    FOREIGN KEY (cnpj_Projeto) REFERENCES Projeto(cnpj)
);


CREATE TABLE Projeto (
    cnpj VARCHAR(14) PRIMARY KEY NOT NULL,
    nome_projeto VARCHAR(100) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    orcamento DECIMAL(10, 2) NOT NULL,
    data_finalizacao DATE NULL
);