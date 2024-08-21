CREATE TABLE Dados_Piloto (
    nome VARCHAR(50) not null,
    matricula varchar(3) not null,
    pais VARCHAR(50) not null,
    idade INT not null,
    equipe VARCHAR(50) not null,
    fabricante VARCHAR(50) not null,
    pontos INT not null,

    primary key(matricula)
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Adrian Sutil',
    '121',
    'Alemanha',
    24,
    'Force India',
    'Mercedes',
    22
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Felipe Massa',
    '122',
    'Brasil',
    27,
    'Ferrari',
    'Ferrari',
    97
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Bruno Senna',
    '136',
    'Brasil',
    21,
    'Hispanica',
    'Cosworth',
    22
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Carlos Sainz Jr',
    '240',
    'Espanha',
    22,
    'Toro Rosso',
    'Renault',
    65
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Christian Klien',
    '135',
    'Australia',
    20,
    'Hispanica',
    'Cosworth',
    13
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Daniel Ricciardo',
    '175',
    'Australia',
    28,
    'Red Bull',
    'Renault',
    0
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Daniil Kvyat',
    '225',
    'Russia',
    28,
    'Red Bull',
    'Renault',
    78
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Felipe Massa',
    '210',
    'Brasil',
    27,
    'Williams',
    'Mercedes',
    12
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Felipe Nasr',
    '200',
    'Brasil',
    20,
    'Sauber',
    'Ferrari',
    34
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Fernando Alonso',
    '112',
    'Espanha',
    28,
    'Ferrari',
    'Ferrari',
    30
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Jarno Trulli',
    '132',
    'Italia',
    18,
    'Lotus',
    'Cosworth',
    4
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Jenson Button',
    '220',
    'Reino Unido',
    25,
    'McLaren',
    'Honda',
    56
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Kevin Magnussen',
    '215',
    'Dinamarca',
    26,
    'McLaren',
    'Honda',
    34
);

INSERT INTO Dados_Piloto (
    nome,
    matricula,
    pais,
    idade,
    equipe,
    fabricante,
    pontos
) VALUES (
    'Kimi Raikkonen',
    '180',
    'Finlandia',
    29,
    'Ferrari',
    'Ferrari',
    0
);

SELECT * FROM Dados_Piloto;

UPDATE Dados_Piloto
SET pontos = 50
WHERE matricula IN ('121', '122', '136', '240', '135');

DELETE FROM Dados_Piloto WHERE matricula IN ('121', '122', '136');

DROP TABLE Dados_Piloto;