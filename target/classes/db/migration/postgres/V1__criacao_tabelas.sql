DROP TABLE IF EXISTS curso_aluno;
DROP TABLE IF EXISTS curso;
DROP TABLE IF EXISTS resultado;
DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS produto;

CREATE TABLE IF NOT EXISTS curso (
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	segmento VARCHAR(50) NOT NULL,
	periodo VARCHAR(50) NOT NULL,
	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS aluno (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cpf SMALLINT NOT NULL,
	dta_nascimento DATE NOT NULL,
	endereco TEXT NOT NULL,
	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS resultado (
	id SERIAL PRIMARY KEY,
	id_aluno INTEGER NOT NULL,
	curso VARCHAR(75) NOT NULL,
	instituicao VARCHAR(75) NOT NULL,
	periodo VARCHAR(10) NOT NULL,
	resultado DECIMAL NOT NULL DEFAULT 0.0,
	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
	CONSTRAINT fk_aluno FOREIGN KEY (id_aluno)
		REFERENCES aluno (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS curso_aluno (
	id_curso INTEGER NOT NULL,
	id_aluno INTEGER NOT NULL,
	CONSTRAINT fk_curso FOREIGN KEY (id_curso)
		REFERENCES curso (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION,
	CONSTRAINT fk_aluno FOREIGN KEY (id_aluno)
		REFERENCES aluno (id) MATCH SIMPLE
		ON UPDATE NO ACTION
		ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS usuario (
	id SERIAL PRIMARY KEY,
	email VARCHAR(75) NOT NULL,
	senha VARCHAR(25) NOT NULL,
	perfil VARCHAR(25) NOT NULL,
	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS produto (
	id serial NOT NULL,
	cod_produto int4 NOT NULL,
	descricao varchar(100) NOT NULL,
	vlr_distribuidor NUMERIC(5,2) NULL DEFAULT 0,
	vlr_admin NUMERIC(5,2) NULL DEFAULT 0,
	quantidade int4 NOT NULL,
	dta_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
	dta_atualizacao TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
	ativo bool NOT NULL DEFAULT false,
	CONSTRAINT produto_cod_produto UNIQUE (cod_produto),
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);