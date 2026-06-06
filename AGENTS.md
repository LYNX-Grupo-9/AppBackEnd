# Orientacao do Projeto

## Modelagem de banco de referencia

Use a modelagem enviada pelo usuario nesta conversa como referencia canonica para este backend. Ao criar ou alterar entidades, DTOs, repositories, migrations, queries, controllers e casos de uso, mantenha nomes, relacoes e responsabilidades alinhados a este modelo.

### Tabelas principais

- `advogado`
  - `idAdvogado INT`
  - `nome VARCHAR(45)`
  - `registro_oab VARCHAR(45)`
  - `cpf VARCHAR(45)`
  - `email VARCHAR(45)`
  - `senha VARCHAR(45)`

- `cliente`
  - `idCliente INT`
  - `nome VARCHAR(45)`
  - `documento_identificacao VARCHAR(45)`
  - `tipo_documentacao VARCHAR(45)`
  - `email VARCHAR(45)`
  - `telefone VARCHAR(45)`
  - `endereco VARCHAR(45)`
  - `genero VARCHAR(45)`
  - `dtNasc VARCHAR(45)`
  - `estado_civil VARCHAR(45)`
  - `profissao VARCHAR(45)`
  - `passaporte VARCHAR(45)`
  - `cnh VARCHAR(45)`
  - `naturalidade VARCHAR(45)`
  - `fkAdvogado INT`

- `cliente_app`
  - `idCliente_app INT`
  - `nome VARCHAR(60)`
  - `email VARCHAR(45)`
  - `cpf VARCHAR(11)`
  - `senha VARCHAR(45)`

- `caso`
  - `idCaso INT`
  - `area_direito VARCHAR(45)`
  - `titulo VARCHAR(45)`
  - `descricao_input VARCHAR(45)`
  - `geracao_ia_cliente VARCHAR(45)`
  - `dt_criacao DATE`
  - `geracao_ia_advogado VARCHAR(45)`
  - `status VARCHAR(45)`
  - `fkCliente_app INT`

- `advogado_interessado`
  - `fkAdvogado INT`
  - `fkCaso INT`
  - `definitivo TINYINT`

- `conversa`
  - `idConversa INT`
  - `fkCliente_app INT`
  - `fkAdvogado INT`
  - `fkCaso INT`
  - `criado_em DATETIME`
  - `atualizado_em DATETIME`

- `mensagem`
  - `idMensagem INT`
  - `fkConversa INT`
  - `remetente_tipo VARCHAR(45)`
  - `remetente_id VARCHAR(45)`
  - `conteudo TEXT`
  - `enviado_em DATETIME`

- `processo`
  - `idProcesso INT`
  - `titulo VARCHAR(45)`
  - `numero VARCHAR(45)`
  - `status VARCHAR(45)`
  - `classe VARCHAR(45)`
  - `assunto VARCHAR(45)`
  - `tribunal VARCHAR(45)`
  - `valor VARCHAR(45)`
  - `autor VARCHAR(45)`
  - `advRequerente VARCHAR(45)`
  - `reu VARCHAR(45)`
  - `advReu VARCHAR(45)`
  - `fkAdvogado INT`
  - `fkCliente INT`

- `lancamentos`
  - `idLancamentos INT`
  - `FkCliente INT`
  - `FkProcesso INT`
  - `titulo VARCHAR(45)`

- `parcelas`
  - `idParcelas INT`
  - `fkLancamento INT`
  - `valor DECIMAL`
  - `vencimento DATE`
  - `status VARCHAR(45)`

- `anexo`
  - `idAnexo INT`
  - `nome VARCHAR(45)`
  - `link_bucket VARCHAR(45)`
  - `fkCliente INT`
  - `fkProcesso INT`

- `evento`
  - `idEvento INT`
  - `nome VARCHAR(45)`
  - `descricao VARCHAR(45)`
  - `local VARCHAR(45)`
  - `link_reuniao VARCHAR(45)`
  - `data DATE`
  - `hora_inicio TIME`
  - `hora_fim TIME`
  - `idProcesso INT`
  - `fkCliente INT`
  - `fkAdvogado INT`
  - `fkCategoria INT`

- `categoria_evento`
  - `idCategoria_evento INT`
  - `nome VARCHAR(45)`
  - `cor VARCHAR(45)`
  - `fkAdvogado INT`

- `lead`
  - `idLead INT`
  - `nome VARCHAR(45)`
  - `telefone VARCHAR(45)`
  - `email VARCHAR(45)`
  - `assunto VARCHAR(45)`
  - `mensagem VARCHAR(45)`
  - `fkAdvogado INT`

- `solicitacao_agendamento`
  - `idSolicitacao_agendamento INT`
  - `nome VARCHAR(45)`
  - `telefone VARCHAR(45)`
  - `assunto VARCHAR(45)`
  - `mensagem VARCHAR(45)`
  - `data DATETIME`
  - `status VARCHAR(45)`
  - `visualizado TINYINT`
  - `fkAdvogado INT`

### Relacoes de referencia

- Um `advogado` possui muitos `clientes`, `processos`, `eventos`, `leads`, `categorias_evento`, `solicitacoes_agendamento`, `conversas` e registros em `advogado_interessado`.
- Um `cliente_app` possui muitos `casos` e `conversas`.
- Um `caso` pertence a um `cliente_app`, pode possuir muitas `conversas` e muitos `advogados_interessados`.
- Uma `conversa` pertence a `cliente_app`, `advogado` e `caso`, e possui muitas `mensagens`.
- Um `cliente` pertence a um `advogado` e pode ter muitos `processos`, `anexos`, `eventos` e `lancamentos`.
- Um `processo` pertence a `advogado` e `cliente`, e pode ter muitos `anexos`, `eventos`, `parcelas` via `lancamentos`.
- Um `lancamento` pertence a `cliente` e `processo`, e possui muitas `parcelas`.

### Diretriz para evolucao

- A modelagem acima prevalece como referencia funcional mesmo quando o codigo atual ainda usa tipos ou nomes diferentes, como `UUID` em entidades ja existentes.
- Antes de alterar uma entidade persistente, confira se a mudanca aproxima o codigo dessa modelagem.
- Quando houver divergencia entre codigo existente e diagrama, preserve compatibilidade somente quando necessario e documente a transicao.
