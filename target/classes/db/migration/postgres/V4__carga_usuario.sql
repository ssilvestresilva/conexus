ALTER TABLE usuario ALTER COLUMN senha TYPE character varying(250);
DELETE FROM usuario WHERE email = 'adm@conexus.com.br';
INSERT INTO usuario (nome, email, senha, perfil) VALUES
('ADMINISTRADOR', 'adm@conexus.com.br', '$2a$10$mGENmHzO6auxgbLAXuixpOwQrxnmmOylY9uhHTQ0meLeP90FsFriq','ROLE_ADMIN');

INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('ADERBAL CARVALHO', 'aderbal@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('MARILIA LOCATELLI', 'marilia@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('CRISTINA FERREIRA', 'cristina@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('HELLEN SILVERIO', 'hellen@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('ENI GUIMARÃES', 'eni@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('ANDERSON MONTEIRO', 'monteiro@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('PEDRO CAIRO', 'pedro@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('IGOR FRANÇA', 'igor@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('SERGIO SILVA', 'sergio@conexus.com', '123', 'ROLE_CLIENTE', now());
INSERT INTO usuario(nome, email, senha, perfil, dta_criacao) VALUES('JOÃO EDUARDO', 'joao@conexus.com', '123', 'ROLE_CLIENTE', now());