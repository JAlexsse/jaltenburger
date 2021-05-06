package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(){}

    public Fecha(int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }

    public LocalDate fechaNac() {
        return LocalDate.of(anio, mes, dia);
        
    }

    public boolean validarMayoriaEdad(){

        Long dias = ChronoUnit.DAYS.between(this.fechaNac(), LocalDate.now());

        return dias > 6570;
    }
}