

CREATE TABLE Cidadao(
    cpf VARCHAR(11) PRIMARY KEY NOT NULL,
    nomeCompleto VARCHAR(100) NOT NULL,
    nomePai VARCHAR(100) NOT NULL,
    nomeMae VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,

    cpfConjuje VARCHAR(11) NULL references Cidadao(cpf),
    dataCasamento DATE NULL 

);




