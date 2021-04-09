CREATE OR REPLACE PACKAGE laboratorio3 IS
    
    FUNCTION tiene_pagos(cliente clientes.codigocliente%TYPE)
    RETURN BOOLEAN;
    
    FUNCTION update_limite (
        anterior_limite clientes.limitecredito%TYPE
    ) RETURN clientes.limitecredito%TYPE;
    
END laboratorio3;

/

/*
    Package para evaluar el trigger.
*/
SET SERVEROUTPUT ON;

CREATE OR REPLACE PACKAGE BODY laboratorio3 IS

    FUNCTION tiene_pagos(cliente clientes.codigocliente%TYPE)
    RETURN BOOLEAN
    IS 
        total_pagos pagos.cantidad%TYPE := 0;
    BEGIN
    
        SELECT SUM(pagos.cantidad)
        INTO total_pagos
        FROM pagos
        WHERE pagos.codigocliente = cliente;
        
        IF total_pagos > 0
        THEN
            RETURN TRUE;
        ELSE    
            RETURN FALSE;
        END IF;

    END tiene_pagos;
    
    FUNCTION update_limite (
        anterior_limite clientes.limitecredito%TYPE
    ) RETURN clientes.limitecredito%TYPE
    IS 
        limite_nuevo clientes.limitecredito%TYPE;
    BEGIN
        IF (anterior_limite > 50000)
        THEN
            limite_nuevo := anterior_limite + 2000;
            RETURN limite_nuevo;
        ELSE
            limite_nuevo := anterior_limite + 500;
            RETURN limite_nuevo;
        END IF;
        
    END update_limite;

END laboratorio3;