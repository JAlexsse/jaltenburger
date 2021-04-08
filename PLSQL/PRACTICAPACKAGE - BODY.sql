SET SERVEROUTPUT ON;

CREATE OR REPLACE PACKAGE BODY practica_package IS
   
    FUNCTION empleados_oficina(oficina_seleccionada oficinas.ciudad%TYPE)
    RETURN SYS_REFCURSOR
    IS
        empleados_seleccionados SYS_REFCURSOR;
    BEGIN
        OPEN empleados_seleccionados FOR
            SELECT empleados.codigoempleado, empleados.puesto
            FROM empleados
            INNER JOIN oficinas
            ON empleados.codigooficina = oficinas.codigooficina
            WHERE oficinas.ciudad = oficina_seleccionada
            ORDER BY empleados.codigoempleado DESC;
        RETURN empleados_seleccionados;
    END empleados_oficina;
    
    PROCEDURE gama_mas_solicitada
    IS
        codigo_producto productos.codigoproducto%TYPE;
        cantidad detallepedidos.cantidad%TYPE;
        gama gamasproductos.gama%TYPE;
    BEGIN 
        SELECT * 
        INTO codigo_producto, cantidad
        FROM(
            SELECT detallepedidos.codigoproducto, sum(detallepedidos.cantidad)
            FROM detallepedidos
            INNER JOIN productos
            ON detallepedidos.codigoproducto = productos.codigoproducto
            GROUP BY detallepedidos.codigoproducto
            ORDER BY sum(detallepedidos.cantidad) DESC
        ) 
        WHERE ROWNUM = 1;
        
        SELECT productos.gama
        INTO gama
        FROM productos
        WHERE productos.codigoproducto = codigo_producto;
        
        DBMS_OUTPUT.PUT_LINE(
            'La gama más solicitada es: ' || gama || ' con ' 
            || cantidad || ' unidades solicitadas.'
        );
    END gama_mas_solicitada;
END practica_package;


