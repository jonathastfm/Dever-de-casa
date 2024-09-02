
CREATE TABLE Correntista (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    logradouro1 VARCHAR(100) NOT NULL,
    numero1 INT NOT NULL,
    complemento1 VARCHAR(100) NOT NULL,
    logradouro2 VARCHAR(100)  NULL,
    numero2 INT  NULL,
    complemento2 VARCHAR(100)  NULL,
    ddd1 INT NOT NULL,
    ddi1 INT NOT NULL,
    numero_telefone1 INT NOT NULL,
    ddd2 INT NULL,
    ddi2 INT NULL,
    numero_telefone2 INT NULL,
    email1 VARCHAR(100) NOT NULL,
    email2 VARCHAR(100) NULL
);

CREATE TABLE Conta (
    agencia INT NOT NULL,
    numero INT NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    limite DECIMAL(10,2)  NULL,
    saldo DECIMAL(10,2) NOT NULL,
    cpf_correntista VARCHAR(11) NOT NULL,
    PRIMARY KEY (agencia, numero),
    FOREIGN KEY (cpf_correntista) REFERENCES Correntista(cpf)
);

