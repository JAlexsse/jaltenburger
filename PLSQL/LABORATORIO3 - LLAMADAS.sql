SET SERVEROUTPUT ON;
DECLARE 
BEGIN
    UPDATE clientes 
    SET clientes.fax = 5556901736
    WHERE clientes.codigocliente = 8;
    
    DBMS_OUTPUT.PUT_LINE(' ');
    
    UPDATE clientes 
    SET clientes.codigopostal = 85495
    WHERE clientes.codigocliente = 4;
    
END;

/

