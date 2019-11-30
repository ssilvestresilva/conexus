ALTER TABLE usuario ALTER COLUMN senha TYPE character varying(250);
DELETE FROM usuario WHERE email = 'adm@conexus.com.br';
INSERT INTO usuario (nome, cpf, email, senha, perfil) VALUES
('ADMINISTRADOR', '01010101011', 'adm@conexus.com.br', '$2a$04$EH9Krzw1LSQS804rOsZMgOc/Hn6uGP3B2p98roBnzK/2C2tXjiL2q','ROLE_ADMIN');

INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('HELLEN SILVERIO', '444444444444', 'hellen@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('ADERBAL CARVALHO', '11111111111', 'aderbal@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('MARILIA LOCATELLI', '22222222222', 'marilia@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('CRISTINA FERREIRA', '33333333333', 'cristina@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('ENI GUIMARÃES', '55555555555', 'eni@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('ANDERSON MONTEIRO', '66666666666', 'monteiro@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('PEDRO CAIRO', '77777777777', 'pedro@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('IGOR FRANÇA', '88888888888', 'igor@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('SERGIO SILVA', '99999999999', 'sergio@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, cpf, email, senha, perfil, dta_criacao) VALUES('JOÃO EDUARDO', '12121212121', 'joao@conexus.com', '123', 'ROLE_CLIENTE', now());