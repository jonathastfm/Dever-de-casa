Solucao 1:

Consultor(
    "cpf",
    nome_completo,
    rua,
    numero_endereco,

    ddi1,
    ddd1,
    numero_telefone1,

    ddi2,
    ddd2,
    numero_telefone2,

    email1,
    email2,
);

Projeto(
    "cnpj",
    nome_projeto
    descricao
    orcamento
    data_finalizacao
    cpf_consultor(Consultor)

)

Solucao2:

Consultor(
    "cpf",
    nome_completo,
    rua,
    numero_endereco,

    ddi1,
    ddd1,
    numero_telefone1,

    ddi2,
    ddd2,
    numero_telefone2,

    email1,
    email2,

    cnpj_Projeto(Projeto)
);

Projeto(
    "cnpj",
    nome_projeto
    descricao
    orcamento
    data_finalizacao
    

)

Solucao 3:

Consultor_Projeto(
    cpf,
    nome_completo,
    rua,
    numero_endereco,

    ddi1,
    ddd1,
    numero_telefone1,

    ddi2,
    ddd2,
    numero_telefone2,

    email1,
    email2,

    "cnpj",
    nome_projeto
    descricao
    orcamento
    data_finalizacao


)