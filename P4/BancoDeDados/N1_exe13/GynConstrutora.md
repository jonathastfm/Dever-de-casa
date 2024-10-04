# Modelo Lógico do Banco de Dados - GYNContrutora

## Tabelas e Relacionamentos

### Departamento (id, nome, telefone, email)

### Colaborador (cpf, nome_completo, telefone, email, endereco, tipo, departamento_id)

### Motorista (cpf, numero_cnh, categoria, data_vencimento_cnh)

### Engenheiro (cpf, crea, area_engenharia)

### Medico (cpf, crm, especialidade)

### Veiculo (placa, chassi, renavan, ano_fabricacao, modelo, marca, km_atual, motorista_cpf)

### Projeto (codigo, descricao, endereco, orcamento_total, engenheiro_responsavel_cpf)

### ConsultaMedica (id, data_consulta, descricao_atendimento, diagnostico, medico_cpf, colaborador_cpf)

### Sinistro (id, data_sinistro, descricao, veiculo_placa, motorista_cpf)

### RegistroTecnico (id, descricao, projeto_codigo, data_registro, engenheiro_cpf)


Os relacionamentos (como motorista_cpf, engenheiro_responsavel_cpf, medico_cpf) permitem a associação entre diferentes entidades, refletindo a realidade onde, por exemplo, um veículo é dirigido por um motorista específico, ou um projeto é gerido por um engenheiro específico.
