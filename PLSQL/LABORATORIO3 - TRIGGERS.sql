
SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER cliente_tiene_pagos
AFTER UPDATE ON clientes
FOR EACH ROW
DECLARE
    tiene_pagos BOOLEAN; 
BEGIN
    
    tiene_pagos := laboratorio3.tiene_pagos(:OLD.codigocliente);
    
    IF (tiene_pagos = TRUE)
    THEN
        DBMS_OUTPUT.PUT_LINE(
            'Codigo cliente: ' || :old.codigocliente  || 
            ' Registra pagos anteriores.'
        );
        
    ELSE
        RAISE NO_DATA_FOUND;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE(
                'El cliente no tiene pagos anteriores.'
        );
END;

/

SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER aumento_limitecredito
AFTER UPDATE ON clientes
FOR EACH ROW
FOLLOWS cliente_tiene_pagos
ENABLE
DECLARE
    no_elegible EXCEPTION;
    PRAGMA EXCEPTION_INIT (no_elegible, -20015);
    
    nuevo_limite clientes.limitecredito%TYPE;
    
    /*
    Recordar: no se pueden declarar variables con los :OLD aqui.
    Los valores que se guardan son del anterior registro en ese caso.
    */
BEGIN
    
    IF (:OLD.pais = 'España' OR :OLD.pais = 'Spain')
    THEN
        nuevo_limite := laboratorio3.update_limite (:OLD.limitecredito);
        DBMS_OUTPUT.PUT_LINE(
                'El nuevo aumento que puede aplicarse es: ' || 
                nuevo_limite
        );
    ELSE
        RAISE no_elegible;
    END IF;
        
EXCEPTION
    
    WHEN no_elegible THEN
        DBMS_OUTPUT.PUT_LINE(
                'El cliente no es elegible para aumento'
        );
        
END;
