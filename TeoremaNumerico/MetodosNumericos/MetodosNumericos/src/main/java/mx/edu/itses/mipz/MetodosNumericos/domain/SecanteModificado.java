package mx.edu.itses.mipz.MetodosNumericos.domain;

import lombok.Data;

@Data
public class SecanteModificado {

   private int iteracion;
    private int iteracionesMaximas;
    private double XIAnterior;
    private double XI;
    private double FXAnterior;
    private double FX;
    private double XR;
    private double Ea;
    private double tolerancia;
    private String funcion;
    private double delta;
}
