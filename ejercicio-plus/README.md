Crear solución:
Empleados(superclass)
	•	Nombre
	•	Edad
	•	Salario

También tendremos una valor/clase constante llamada PLUS, que tendrá un valor de 200USD
Tenemos dos tipos de empleados: operario y encargado.
El encargado tiene una attr comisión (double).
El operario tiene un attr llamado zona (String).
Crea sus constructores, getters and setters y toString (implementar la herencia).
No se podrán crear objetos del tipo Empleado (la clase padre) pero si de sus hijas.
Las clases tendrán un método llamado plus, que según en cada clase tendrá una implementación distinta. Este plus aumenta el salario del empleado.
	•	En encargado, si tiene más de 30 años y cobra una comisión de más de 100 USD, se le aplicara el plus.
	•	En operario, si tiene menos de 25 y reparte en la “zona 3”, este recibirá el plus.
Opcional que devuelva un booleano o que no devuelva nada.
Crear una clase ejecutable donde crees distintos empleados y le apliques el plus para comprobar que funciona el codigo.
