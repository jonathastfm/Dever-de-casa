CREATE TABLE Funcionario (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,

    rua VARCHAR(100) NOT NULL,
    numeroCasa INT NOT NULL,
    unique (rua, numeroCasa),


    email VARCHAR(100) NOT NULL,

    ddd INT NOT NULL,
    ddi INT NOT NULL,
    numero INT NOT NULL,
    unique (ddi, ddd, numero),


    cpfChefe VARCHAR(11) NULL,
    FOREIGN KEY (cpfChefe) REFERENCES Funcionario(cpf)
);