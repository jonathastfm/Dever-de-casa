
create table Consultor(
    cpf varchar(11) primary key,
    nome_completo varchar(100) not null,
    rua varchar(100) not null,
    numero_endereco int not null,
    ddi1 int not null,
    ddd1 int not null,
    numero_telefone1 int not null,
    ddi2 int  null,
    ddd2 int  null,
    numero_telefone2 int null,
    email1 varchar(100) not null,
    email2 varchar(100) null
);

create table Projeto(
    cnpj varchar(14) primary key,
    nome_projeto varchar(100) not null,
    descricao varchar(100) not null,
    orcamento float not null,
    data_finalizacao date null,
    cpf_consultor varchar(11) not null,
    foreign key (cpf_consultor) references Consultor(cpf)
);






