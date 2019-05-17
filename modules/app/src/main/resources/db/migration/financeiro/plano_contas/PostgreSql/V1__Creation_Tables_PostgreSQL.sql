/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS plano_conta CASCADE;
**********************************************************/

CREATE TABLE plano_conta /* PlanoConta */  (
	id UUID NOT NULL,
	codigo VARCHAR(255) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	tipo_financeiro VARCHAR(255) NOT NULL /* tipoFinanceiro */,
	tipo_receita_despesa VARCHAR(255) /* tipoReceitaDespesa */,
	plano_conta_pai UUID /* planoContaPai */,
	ativo BOOLEAN NOT NULL DEFAULT true,
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

/* PRIMARY KEYS */
ALTER TABLE plano_conta ADD CONSTRAINT pk_plano_conta_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE plano_conta ADD CONSTRAINT fk_plano_conta_plano_conta_pai FOREIGN KEY (plano_conta_pai) REFERENCES plano_conta (id);

