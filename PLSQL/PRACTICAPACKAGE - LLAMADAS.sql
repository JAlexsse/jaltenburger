
/*
Llamada a funcion del package: laboratorio3.
*/
DECLARE
    oficina_seleccionada oficinas.ciudad%TYPE := 'Barcelona';
    empleados_seleccionados SYS_REFCURSOR;
    codigo_empleado empleados.codigoempleado%TYPE;
    puesto_empleado empleados.puesto%TYPE;
BEGIN
    empleados_seleccionados := 
        laboratorio3.empleados_oficina(oficina_seleccionada);
    
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

/*
Llamada al procedure del package: laboratorio3.
*/    
BEGIN
    laboratorio3.gama_mas_solicitada;
END;
