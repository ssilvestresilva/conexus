ALTER TABLE usuario ALTER COLUMN senha TYPE character varying(250);
DELETE FROM usuario WHERE email = 'adm@conexus.com.br';
INSERT INTO usuario (email, nome, senha, perfil) VALUES
('adm@conexus.com.br', 'ADMINISTRADOR', '$2a$10$mGENmHzO6auxgbLAXuixpOwQrxnmmOylY9uhHTQ0meLeP90FsFriq','ROLE_ADMIN');