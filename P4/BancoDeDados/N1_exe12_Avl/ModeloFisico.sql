CREATE TABLE Professor (
    cpfProfessor INT PRIMARY KEY,
    nomeCompleto VARCHAR(100),
    email VARCHAR(100),
    sexo CHAR(1),
    nascimento DATE,
    maiorTitulacao VARCHAR(50),
    estadoCivil VARCHAR(20),
    id INT
);

CREATE TABLE Curso (
    nomeCurso VARCHAR(100) PRIMARY KEY,
    dataCriacao DATE,
    numeroPortaria INT
);

CREATE TABLE Formacao (
    cpf INT,
    tituloFormacao VARCHAR(100),
    dataTitulo DATE,
    tituloTrabalho VARCHAR(100),
    descricao TEXT,
    FOREIGN KEY (cpf) REFERENCES Professor(cpf)
);

CREATE TABLE ProducaoAcademica (
    cpf INT,
    tituloTrabalho VARCHAR(100),
    tipoProducao VARCHAR(50),
    dataPublicacao DATE,
    FOREIGN KEY (cpf) REFERENCES Professor(cpf)
);

CREATE TABLE Evento (
    dataEvento DATE,
    nome VARCHAR(100),
    classificacao VARCHAR(50),
    cpf INT,
    descricao TEXT,
    FOREIGN KEY (cpf) REFERENCES Professor(cpf)
);

CREATE TABLE ParticipacaoEvento (
    cpf INT,
    idEvento INT, -- Assumindo um campo id para a tabela Evento
    FOREIGN KEY (cpf) REFERENCES Professor(cpf),
    FOREIGN KEY (idEvento) REFERENCES Evento(id)
);

-- Tabela Relacionamento (pode ser dispensável dependendo da interpretação do diagrama)
CREATE TABLE Relacionamento (
    id INT PRIMARY KEY,
    -- Outros campos para armazenar informações sobre o relacionamento
);