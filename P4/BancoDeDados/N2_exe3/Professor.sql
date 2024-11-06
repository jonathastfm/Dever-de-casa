
CREATE TYPE SexoEnum AS ENUM ('Masculino', 'Feminino');

CREATE TABLE Professor (
    matriculaProfessor INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    Departamento VARCHAR(100),
    Email VARCHAR(100) UNIQUE,
    Telefone VARCHAR(15),
    cpf VARCHAR(11) UNIQUE,
    sexo sexoEnum NOT NULL,
    escola VARCHAR(100),

    titulacao varchar(15) CHECK (titulacao IN ('Graduado', 'Especialista', 'Mestre', 'Doutor')),

    logradouro VARCHAR(100),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cep VARCHAR(10),
    cidade VARCHAR(100),
    estado VARCHAR(2)
);