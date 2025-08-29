package mx.edu.itses.mipz.MetodosNumericos.domain;

import lombok.Data;


@Data
public class ReglaFalsa {
    private String FX; //funcion a evaluar
    private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double Ea;
    private int IteracionesMaximas;   
}
