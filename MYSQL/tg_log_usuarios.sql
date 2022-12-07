-- Crie uma trigger que é disparada ao atualizar os dados da tabela Usuário. As informações para se manter o registro são:
-- IdUsuario;
-- Nome;
-- Perfil;
-- DType;
-- Data da alteração;
-- Observação: o código da trigger deve ser entregue dentro da pasta sql na raiz do projeto seguindo o padrão de nome.
-- Sugestão de nome: tg_log_usuarios.sql

use helpr;
select * from usuarios;

CREATE DATABASE bkp_helpr;

CREATE TABLE lg_usuario(
idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(158) NOT NULL,
perfil VARCHAR(30) NOT NULL,
dtype VARCHAR(30) NOT NULL,
dataAlteracao DATE NOT NULL
);

DELIMITER //

DELETE FROM usuario WHERE id = 16;
UPDATE usuario SET perfil = "ADMIN" WHERE id = 2;

CREATE TRIGGER tg_lg_usuario 
BEFORE UPDATE ON usuario 
FOR EACH ROW 
BEGIN
    INSERT INTO lg_usuario VALUES(OLD.id, OLD.nome, OLD.perfil, OLD.dtype, CURRENT_DATE());
END//

select * from lg_usuario;
