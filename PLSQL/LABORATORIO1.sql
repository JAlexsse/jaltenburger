SET SERVEROUTPUT ON;
--CLASE 1 : PLSQL
DECLARE
    -- Declaracion de variables
    codigo_cliente clientes.codigocliente%TYPE := &codigo;
    nombre_contacto clientes.nombrecontacto%TYPE;
    apellido_contacto clientes.apellidocontacto%TYPE;
BEGIN
    /*
    PL/SQL executable statement.
    */
    SELECT nombrecontacto, apellidocontacto 
    INTO nombre_contacto, apellido_contacto
    FROM clientes 
    WHERE  codigocliente = codigo_cliente;
    
    --print
    DBMS_OUTPUT.PUT_LINE('El cliente es:' 
    || nombre_contacto || ' ' ||apellido_contacto);
END;

/

SET SERVEROUTPUT ON;
--CLASE 2: PLSQL - CURSOR IMPLICITO

DECLARE
  empleado_seleccionado empleados%ROWTYPE;
BEGIN
  SELECT *
  INTO empleado_seleccionado
  FROM empleados
  WHERE codigoempleado = 18;
  
  IF SQL%FOUND AND empleado_seleccionado.codigooficina = 'SFC-USA'
  THEN
    DBMS_OUTPUT.PUT_LINE('Nombre:' || empleado_seleccionado.nombre);
    DBMS_OUTPUT.PUT_LINE('Puesto:' || empleado_seleccionado.puesto);

  END IF;
  
EXCEPTION
    WHEN no_data_found
    THEN
        DBMS_OUTPUT.PUT_LINE
        ('No hay empleado con el codigo de empleado solicitado.');
  
END;

/

SET SERVEROUTPUT ON;
--CLASE 2: PLSQL - CURSOR EXPLICITO
DECLARE
  -- Declaracion de variables
    ciudad_seleccionada clientes.ciudad%TYPE := &ciudad;
    CURSOR clientes_seleccionados IS
        SELECT * 
        FROM clientes 
        WHERE ciudad = ciudad_seleccionada;
    
    cliente_record clientes%ROWTYPE;

BEGIN
    /*
    Trae los records correspondientes a una misma region.
    */
    OPEN clientes_seleccionados;
            
        FETCH clientes_seleccionados INTO cliente_record;
        WHILE clientes_seleccionados%FOUND
        LOOP
            DBMS_OUTPUT.PUT_LINE('Codigo:' || cliente_record.codigocliente);
            DBMS_OUTPUT.PUT_LINE('Cliente:' || cliente_record.nombrecliente);
            FETCH clientes_seleccionados INTO cliente_record;
        END LOOP;
    
        /* 
        %ROWCOUNT contiene la cantidad de rows que han sido 
        procesadas hasta el momento.
        */
        IF clientes_seleccionados%ROWCOUNT > 0 THEN 
            DBMS_OUTPUT.PUT_LINE(
            'Ciudad Seleccionada:' 
            || ciudad_seleccionada
            );
            
            DBMS_OUTPUT.PUT_LINE(
            'Cantidad de clientes encontrados ' 
            || clientes_seleccionados%ROWCOUNT
            );
        ELSE
                DBMS_OUTPUT.PUT_LINE(
                'Ningun cliente perteneciente a la region.'
                );
        END IF;
    
    CLOSE clientes_seleccionados;

END;

/

SET SERVEROUTPUT ON;
--CLASE 2: CURSOR EXPLICITO PARAMETRIZADO

DECLARE
    /*
    Trae todos los codigos y nombre de producto correspondientes
    a un pedido pasado como parametro.
    */
    CURSOR productos_pedido
    (pedido_seleccionado detallepedidos.codigopedido%TYPE) IS
        SELECT productos.codigoproducto, productos.nombre 
        FROM productos
        INNER JOIN detallepedidos
        ON productos.codigoproducto = detallepedidos.codigoproducto
        WHERE detallepedidos.codigopedido = pedido_seleccionado;
    
    producto_codigo productos.codigoproducto%TYPE;
    producto_nombre productos.nombre%TYPE;
BEGIN
    OPEN productos_pedido(1);
    
    FETCH productos_pedido INTO producto_codigo, producto_nombre;
    
    LOOP
        DBMS_OUTPUT.PUT_LINE( 
            'Codigo: ' || producto_codigo ||
            ' Producto: ' || producto_nombre
        );
        
        EXIT WHEN productos_pedido%NOTFOUND;
        
        FETCH productos_pedido INTO producto_codigo, producto_nombre;
       
    END LOOP;
    
    CLOSE productos_pedido;
    
END;

/

SET SERVEROUTPUT ON;

DECLARE
    --cliente del cual se quiere buscar pedidos
    cliente_seleccionado pedidos.codigocliente%TYPE := &cliente;
    
    --variables para iterar el cursor
    cantidad_record detallepedidos.cantidad%TYPE;
    preciounidad_record detallepedidos.preciounidad%TYPE;
    
    total_pedidos detallepedidos.preciounidad%TYPE := 0;
    
    /*
    cursor que trae los records de detalles de pedidos 
    correspondientes al cliente seleccionado.
    */
    CURSOR detalle_pedido IS
        SELECT cantidad, preciounidad 
        FROM detallepedidos 
        INNER JOIN pedidos
        ON detallepedidos.codigopedido = pedidos.codigopedido
        WHERE pedidos.codigocliente = cliente_seleccionado;
BEGIN
  OPEN detalle_pedido;
  
  FETCH detalle_pedido INTO cantidad_record, preciounidad_record;
  WHILE detalle_pedido%FOUND
  LOOP
    total_pedidos := total_pedidos + (cantidad_record * preciounidad_record);
    FETCH detalle_pedido INTO cantidad_record, preciounidad_record;
  END LOOP;
  
  CLOSE detalle_pedido;
  
  DBMS_OUTPUT.PUT_LINE( 
            'Total: ' || total_pedidos
        );
  
END;
