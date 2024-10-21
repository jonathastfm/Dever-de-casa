

-- Tabela de Departamentos
CREATE TABLE Departamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ddi CHAR(3) NOT NULL,
    ddd CHAR(2) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    UNIQUE (ddi, ddd, numero),
    email VARCHAR(100) NOT NULL
);

-- Tabela de Especialidade Médica
CREATE TABLE EspecialidadeMedica (
    idEspecialidadeMedica SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE AreaEngenharia(
    idAreaEngenharia SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela de Colaboradores
CREATE TABLE Colaborador (
    cpf VARCHAR(11) PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    ddi CHAR(3) NOT NULL,
    ddd CHAR(2) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    UNIQUE (ddi, ddd, numero),
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('comum', 'motorista', 'engenheiro', 'medico')) NOT NULL,
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);

-- Tabela de Motoristas
CREATE TABLE Motorista (
    cpf CHAR(11) PRIMARY KEY,
    numero_cnh VARCHAR(20) NOT NULL,
    categoria VARCHAR(1) CHECK (categoria IN ('A', 'AB', 'AC', 'AD', 'AE', 'B', 'C', 'D', 'E')) NOT NULL,
    data_vencimento_cnh DATE NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Engenheiros
CREATE TABLE Engenheiro (
    cpf CHAR(11) PRIMARY KEY,
    crea VARCHAR(20) NOT NULL,
    area_engenharia VARCHAR(50) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf),
    FOREIGN KEY (area_engenharia) REFERENCES AreaEngenharia(idAreaEngenharia)
);

-- Tabela de Médicos
CREATE TABLE Medico (
    cpf VARCHAR(11) PRIMARY KEY,
    crm VARCHAR(20) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Colaborador(cpf)
);

CREATE TABLE EspecialidadesMedico(
    medico_cpf CHAR(11) PRIMARY KEY,
    especialidade_id INT,
    FOREIGN KEY (medico_cpf) REFERENCES Medico(cpf),
    FOREIGN KEY (especialidade_id) REFERENCES EspecialidadeMedica(idEspecialidadeMedica)
);

-- Tabela de Veículos
CREATE TABLE Veiculo (
    placa CHAR(7) PRIMARY KEY,
    chassi VARCHAR(17) NOT NULL,
    renavan VARCHAR(11) NOT NULL,
    ano_fabricacao INT NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    km_atual INT NOT NULL,
    motorista_cpf CHAR(11),    
    FOREIGN KEY (motorista_cpf) REFERENCES Motorista(cpf),
    FOREIGN KEY (modelo) REFERENCES Modelo(idModelo)
    );

-- Tabela de Modelos
CREATE TABLE Modelo (
    idModelo SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL
    FOREIGN KEY (marca) REFERENCES Marca(idMarca),
    
);

-- Tabela de Marcas
CREATE TABLE Marca (
    idMarca SERIAL PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
);


-- Tabela de Projetos
CREATE TABLE Projeto (
    codigo SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    orcamento_total DECIMAL(15, 2) NOT NULL,
    engenheiro_responsavel_cpf CHAR(11),
    idRegistroTecnico INT,
    FOREIGN KEY (engenheiro_responsavel_cpf) REFERENCES Engenheiro(cpf),
    FOREIGN KEY (idRegistroTecnico) REFERENCES RegistroTecnico(id)
);


-- Tabela de Consultas Médicas
CREATE TABLE ConsultaMedica (
    id SERIAL PRIMARY KEY,
    data_consulta DATE NOT NULL,
    descricao_atendimento TEXT NOT NULL,
    diagnostico TEXT NOT NULL,
    medico_cpf CHAR(11),
    colaborador_cpf CHAR(11),
    FOREIGN KEY (medico_cpf) REFERENCES Medico(cpf),
    FOREIGN KEY (colaborador_cpf) REFERENCES Colaborador(cpf)
);

-- Tabela de Sinistros
CREATE TABLE Sinistro (
    id SERIAL PRIMARY KEY,
    data_sinistro DATE NOT NULL,
    descricao TEXT NOT NULL,
    veiculo_placa CHAR(7),
    motorista_cpf CHAR(11),
    FOREIGN KEY (veiculo_placa) REFERENCES Veiculo(placa),
    FOREIGN KEY (motorista_cpf) REFERENCES Motorista(cpf)
);

-- Tabela de Registros Técnicos
CREATE TABLE RegistroTecnico (
    id SERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    projeto_codigo INT,
    data_registro DATE NOT NULL,
    engenheiro_cpf CHAR(11),
    FOREIGN KEY (projeto_codigo) REFERENCES Projeto(codigo),
    FOREIGN KEY (engenheiro_cpf) REFERENCES Engenheiro(cpf)
);