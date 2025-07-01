package mx.edu.itses.mipz.MetodosNumericos.domain;

import lombok.Data;

@Data
public class NewtonRaphson {

    private int iteracion;
    private String FX;
    private double XI;
    private double fX;
    private double fDX;//Xi+1
    private double Ea;
    private int IteracionesMaximas;
    
    
}
