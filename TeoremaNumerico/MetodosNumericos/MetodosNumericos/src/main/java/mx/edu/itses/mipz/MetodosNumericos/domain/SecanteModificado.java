package mx.edu.itses.mipz.MetodosNumericos.domain;
import lombok.Data;
@Data
public class SecanteModificado {
    private double XI;
    private double fX;
    private double XR;
    private double fXAnterior;
    private double XIAnterior;
    private int iteracion;
    private int iteracionesMaximas;
    private double tolerancia;
    private String funcion;
    private double delta;
            private double Ea;
}
