package mx.edu.itses.mipz.MetodosNumericos.domain;

import lombok.Data;

@Data
public class Biseccion {

    private String FX;//Funcion evaluar.
    private double XL;
    private double XR;
    private double XU;
    private double FXL;
    private double FXU;
    private double FXR;
    private double Ea;
    private int IteraccionesMaximas;
    
    
    
    
}
