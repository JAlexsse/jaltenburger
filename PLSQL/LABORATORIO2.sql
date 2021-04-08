
/*
    EJERCICIO 1
    Crear un cursor que muestre todos los clientes que no hayan realizado pagos.
    Implementar FOR.
*/
DECLARE
    
    CURSOR clientes_sin_pagos IS
        SELECT clientes.codigocliente, clientes.nombrecliente
        FROM clientes
        LEFT OUTER JOIN pagos 
        ON (clientes.codigocliente = pagos.codigocliente)
        WHERE pagos.codigocliente is null;
    
BEGIN
    
    DBMS_OUTPUT.PUT_LINE(
            'Clientes sin pagos:'
        );
    
    FOR cliente IN clientes_sin_pagos
    LOOP
        
        DBMS_OUTPUT.PUT_LINE(
            cliente.codigocliente || ' ' || cliente.nombrecliente
        );
        
    END LOOP;
      
END;

/

/*
    EJERCICIO 2
    Crear una función que devuelva la suma de pagos que realizó un cliente dado. 
    Pasar por param el codigo. Considerar cuando no se encuentre registros.
*/

CREATE OR REPLACE 
FUNCTION pagos_realizados(cliente clientes.codigocliente%TYPE)
RETURN pagos.cantidad%TYPE
IS 
    total_pagos pagos.cantidad%TYPE := 0;
BEGIN

    SELECT SUM(pagos.cantidad)
    INTO total_pagos
    FROM pagos
    WHERE pagos.codigocliente = cliente;
    
    IF total_pagos IS NULL 
    THEN
        RETURN 0;
    ELSE    
        RETURN total_pagos;
    END IF;
    
END;

/

--Llamado de ejercicio 2
DECLARE
    cliente_solicitado clientes.codigocliente%TYPE := &codigo;
    total_pagos pagos.cantidad%TYPE;
BEGIN
    total_pagos := pagos_realizados(cliente_solicitado);
    
    IF total_pagos = 0
    THEN
        DBMS_OUTPUT.PUT_LINE(
            'El cliente no registra pagos.'
        );
    ELSE
        DBMS_OUTPUT.PUT_LINE(
            'El cliente registra pagos por el monto: ' || total_pagos
        );
    END IF;
END;

/

/*

EJERCICIO 3
Realizar un método o procedimiento que muestre el total en euros de un pedido.
Codigo pasado por param.
*/

CREATE OR REPLACE PROCEDURE total_en_euros 
(pedido_requerido IN pedidos.codigopedido%TYPE)
IS
    total_pedido detallepedidos.preciounidad%TYPE := 0;
    
    CURSOR pedido IS
        SELECT detallepedidos.cantidad, detallepedidos.preciounidad
        FROM detallepedidos
        WHERE detallepedidos.codigopedido = pedido_requerido;
BEGIN
    FOR articulo IN pedido
    LOOP
        total_pedido := total_pedido + 
        (articulo.cantidad * articulo.preciounidad);
    END LOOP;
    
    total_pedido := total_pedido * 109.49;
    
    DBMS_OUTPUT.PUT_LINE(
        'El total en euros es: ' || total_pedido
    );
END;

/

--Llamado de ejercicio 3
DECLARE
    pedido_solicitado pedidos.codigopedido%TYPE := &codigo;
BEGIN
    total_en_euros(pedido_solicitado);
END;

/

/*
EJERCICIO 4
Realizar un procedimiento que muestre el total en euros de un pedido. 
Codigo pasado por param. Considerar caso de que no se encuentren registros. 
Pasar un segundo param que va a ser el limite y si supera ese limite lanzamos 
una excepcion propia y devolveremos un 0.

ERROR HANDLING:
https://docs.oracle.com/cd/B10501_01/appdev.920/a96624/07_errs.htm#943
*/

CREATE OR REPLACE PROCEDURE total_en_euros_con_limite (
    pedido_requerido IN pedidos.codigopedido%TYPE,
    limite IN pedidos.codigopedido%TYPE
)
IS
    total_pedido detallepedidos.preciounidad%TYPE := 0;
    
    CURSOR pedido IS
        SELECT detallepedidos.cantidad, detallepedidos.preciounidad
        FROM detallepedidos
        WHERE detallepedidos.codigopedido = pedido_requerido;
        
    fuera_limite EXCEPTION;
    PRAGMA EXCEPTION_INIT (fuera_limite, -20000);
BEGIN
    FOR articulo IN pedido
    LOOP
        total_pedido := total_pedido + 
        (articulo.cantidad * articulo.preciounidad);
    END LOOP;
    
    IF total_pedido = 0
    THEN 
        RAISE no_data_found;
    END IF;
    
    total_pedido := total_pedido * 109.49;
    
    IF total_pedido > limite
    THEN
        RAISE fuera_limite;
    ELSE
        DBMS_OUTPUT.PUT_LINE(
            'El total en euros del pedido es: ' || total_pedido
        );
    END IF;
EXCEPTION
    WHEN no_data_found THEN
        DBMS_OUTPUT.PUT_LINE(
            'El cliente no registra pagos.'
        );
    WHEN fuera_limite THEN
        DBMS_OUTPUT.PUT_LINE(
            'El pedido supera el limite.'
        );
END;

/

--Llamado de ejercicio 4
DECLARE
    pedido_solicitado pedidos.codigopedido%TYPE := &codigo;
    limite detallepedidos.preciounidad%TYPE := &limite;
    
BEGIN
    total_en_euros_con_limite(pedido_solicitado, limite);

END;

/