CREATE OR REPLACE PACKAGE practica_package IS
    
    FUNCTION empleados_oficina(oficina_seleccionada oficinas.ciudad%TYPE)
    RETURN SYS_REFCURSOR;
    
    PROCEDURE gama_mas_solicitada;
    
    
END practica_package;

