CREATE TABLE log_chamado(
    idLog_chamado INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_funcionario INTEGER NOT NULL,
    id_cliente INTEGER NOT NULL,
    id_chamado INTEGER NOT NULL,
    `status` VARCHAR(255) NOT NULL,
	titulo VARCHAR(120) NOT NULL,
    dataAlteracao DATE NOT NULL
);

DELIMITER //
 
CREATE TRIGGER tg_log_chamado
BEFORE UPDATE ON chamado
FOR EACH ROW
BEGIN
    
    INSERT INTO log_chamado VALUES(NULL, OLD.id_funcionario, OLD.id_cliente, OLD.id_chamado, NEW.`status`, OLD.titulo , CURRENT_DATE());
END//
