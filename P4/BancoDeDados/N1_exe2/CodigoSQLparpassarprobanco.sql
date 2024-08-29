create table Cliente(
    cpf varchar(11),
    nome varchar(60) not null,
    
    logradouro varchar(40) null,
    numero int null,
    complemento varchar(20) null,

    ddi1 int not null,
    ddd1 int not null,
    telefone1 int not null,

    ddi2 int null,
    ddd2 int null,
    telefone2 int null,

    ddi3 int null,
    ddd3 int null,
    telefone3 int null,

    email1 varchar(60) not null,
    email2 varchar(60) null,
    email3 varchar(60) null,

    primary key(cpf)

);

SELECT * FROM Cliente;

INSERT INTO Cliente (
    cpf,
    nome,
    logradouro,
    numero,
    complemento,
    ddi1,
    ddd1,
    telefone1,
    ddi2,
    ddd2,
    telefone2,
    ddi3,
    ddd3,
    telefone3,
    email1,
    email2,
    email3
) VALUES (
    '12345678901',
    'João da Silva',
    'Rua das Flores',
    123,
    'Apto 101',
    55,
    11,
    12345678,
    55,
    11,
    87654321,
    55,
    11,
    87654321,
);

INSERT INTO Cliente (
    cpf,
    nome,
    logradouro,
    numero,
    complemento,
    ddi1,
    ddd1,
    telefone1,
    ddi2,
    ddd2,
    telefone2,
    ddi3,
    ddd3,
    telefone3,
    email1,
    email2,
    email3
) VALUES (
    '98765432109',
    'Maria da Silva',
    'Rua das Flores',
    123,
    'Apto 101',
    55,
    11,
    12345678,
    55,
    11,
    87654321,
    55,
    11,
    87654321
    
);

INSERT INTO Cliente (
    cpf,
    nome,
    logradouro,
    numero,
    complemento,
    ddi1,
    ddd1,
    telefone1,
    ddi2,
    ddd2,
    telefone2,
    ddi3,
    ddd3,
    telefone3,
    email1,
    email2,
    email3
) VALUES (
    '12345678901',
    'Lucas da Silva',
    'Rua das Flores',
    123,
    'Apto 101',
    55,
    11,
    12345678,
    55,
    11,
    87654321,
    55,
    11,
    87654321,

);

INSERT INTO Cliente (
    cpf,
    nome,
    logradouro,
    numero,
    complemento,
    ddi1,
    ddd1,
    telefone1,
    ddi2,
    ddd2,
    telefone2,
    ddi3,
    ddd3,
    telefone3,
    email1,
    email2,
    email3
) VALUES (
    '12345678901',
    'Felipe da Silva',
    'Rua das Flores',
    123,
    'Apto 101',
    55,
    11,
    12345678,
    55,
    11,
    87654321,
    55,
    11,
    87654321,

);

INSERT INTO Cliente (
    cpf,
    nome,
    logradouro,
    numero,
    complemento,
    ddi1,
    ddd1,
    telefone1,
    ddi2,
    ddd2,
    telefone2,
    ddi3,
    ddd3,
    telefone3,
    email1,
    email2,
    email3
) VALUES (
    '12345678901',
    'Ana da Silva',
    'Rua das Flores',
    123,
    'Apto 101',
    55,
    11,
    12345678,
    55,
    11,
    87654321,
    55,
    11,
    87654321,

);