/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS plano_conta CASCADE;
**********************************************************/

CREATE TABLE plano_conta /* planoConta */  (
	id UUID NOT NULL,
	code VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	parent_plano_conta UUID /* parentPlanoConta */,
	active BOOLEAN NOT NULL,
	kind VARCHAR(255) NOT NULL,
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

/* PRIMARY KEYS */
ALTER TABLE plano_conta ADD CONSTRAINT pk_plano_conta_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE plano_conta ADD CONSTRAINT fk_plano_conta_parent_plano_conta FOREIGN KEY (parent_plano_conta) REFERENCES plano_conta (id);

