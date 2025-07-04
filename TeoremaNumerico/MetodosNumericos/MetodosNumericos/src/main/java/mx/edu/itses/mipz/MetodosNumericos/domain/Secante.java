package mx.edu.itses.mipz.MetodosNumericos.domain;
import lombok.Data;

@Data
public class Secante {
    private int iteracion;
    private int iteracionesMaximas;
    private double XIAnterior;
    private double XI;
    private double fXAnterior;
    private double fX;
    private double XR;
    private double Ea;
    private double tolerancia;
    private String funcion;
    }
