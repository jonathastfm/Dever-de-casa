
# Modelo Funcionario

Funcionario(
    cpf,
    nome,
    rua,
    numeroCasa
    email,
    ddd,
    ddi,
    numero,
    cpfChefe,

)

# Interpretação do modelo

E necessario uma variavel para que um funcionario referencie seu chefe,
a variavel nao será obrigatoria pois o relacionamento nao e obrigatorio,
sera usado o cpf do chefe pois esse tem cardinalidade maxima 1, diferente do funcionario comum