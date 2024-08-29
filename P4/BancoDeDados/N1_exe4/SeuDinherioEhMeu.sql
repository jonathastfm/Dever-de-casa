CREATE TABLE correntista (
    cpf VARCHAR(11) PRIMARY KEY,

    nome VARCHAR(100) NOT NULL,
    
    ddd1 INT NOT NULL,
    ddi1 INT NOT NULL,
    numero1 INT NOT NULL,
    unique(ddd1,ddi1,numero1),

    ddd2 INT,
    ddi2 INT,
    numero2 INT,
    
    email VARCHAR(100) NOT NULL,
    
    logradouro VARCHAR(100) NOT NULL,
    numero_endereco INT NOT NULL,
    complemento VARCHAR(100) NOT NULL,
    
    
    );



CREATE TABLE Conta (
    agencia INT NOT NULL,
    numero INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    limite DECIMAL(10, 2) NULL,
    saldo DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (agencia, numero)
);

