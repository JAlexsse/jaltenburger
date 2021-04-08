
SET SERVEROUTPUT ON;
/*
Llamada a funcion del package: practica_package.
*/
DECLARE
    oficina_seleccionada oficinas.ciudad%TYPE := 'Barcelona';
    empleados_seleccionados SYS_REFCURSOR;
    codigo_empleado empleados.codigoempleado%TYPE;
    puesto_empleado empleados.puesto%TYPE;
BEGIN
    empleados_seleccionados := 
        practica_package.empleados_oficina(oficina_seleccionada);
    
    LOOP
        FETCH empleados_seleccionados 
        INTO codigo_empleado, puesto_empleado;
        
        EXIT WHEN empleados_seleccionados%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(
            'Codigo empleado: ' || codigo_empleado || 
            ' Puesto: ' || puesto_empleado
        );
    END LOOP;
END;

/

SET SERVEROUTPUT ON;
/*
Llamada al procedure del package: practica_package
*/    
BEGIN
    practica_package.gama_mas_solicitada;
END;
