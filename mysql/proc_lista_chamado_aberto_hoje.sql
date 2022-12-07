USE helpr

DELIMITER //
 
CREATE PROCEDURE proc_lista_chamado_aberto_hoje()
BEGIN
    SELECT * FROM chamado WHERE data_abertura = CURRENT_DATE();
END//

CALL proc_lista_chamado_aberto_hoje()