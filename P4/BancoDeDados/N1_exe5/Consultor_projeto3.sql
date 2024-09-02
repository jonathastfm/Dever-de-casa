CREATE TABLE Consultor_Projeto (
    cpf VARCHAR(11) NOT NULL,
    nome_completo VARCHAR(255) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero_endereco INT NOT NULL,
    ddi1 VARCHAR(3) NOT NULL,
    ddd1 VARCHAR(3) NOT NULL,
    numero_telefone1 VARCHAR(20) NOT NULL,
    unique (ddi1, ddd1, numero_telefone1),

    ddi2 VARCHAR(3) NULL,
    ddd2 VARCHAR(3) NULL,
    numero_telefone2 VARCHAR(20) NULL,
    unique (ddi2, ddd2, numero_telefone2),


    email1 VARCHAR(255) NOT NULL,
    email2 VARCHAR(255) NULL,
    cnpj VARCHAR(14) NOT NULL,
    nome_projeto VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    orcamento DECIMAL(10, 2) NOT NULL,
    data_finalizacao DATE NULL
);