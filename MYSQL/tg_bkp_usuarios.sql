USE helpr;

SELECT * FROM usuarios;

CREATE DATABASE bkp_helpr;

CREATE TABLE bkp_helpr.bkp_usuarios(
dtype VARCHAR(31),
idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
cpf VARCHAR(11) UNIQUE NOT NULL,
email VARCHAR(120) UNIQUE NOT NULL,
nome VARCHAR(158) NOT NULL,
perfil VARCHAR(30) NOT NULL,
senha VARCHAR(255) NOT NULL,
telefone VARCHAR(25),
foto VARCHAR(255)
);

DELIMITER //

CREATE TRIGGER tg_bkp_usuarios
BEFORE DELETE ON usuarios
FOR EACH ROW
BEGIN
    INSERT INTO bkp_helpr.bkp_usuarios VALUES (OLD.dtype, OLD.id, OLD.cpf, OLD.email, OLD.nome, OLD.perfil, OLD.senha, OLD.telefone, OLD.foto);
    END//
    
DELIMITER ;

-- Teste do trigger:
DELETE FROM usuarios WHERE id=22;
SELECT * FROM bkp_helpr.bkp_usuarios;