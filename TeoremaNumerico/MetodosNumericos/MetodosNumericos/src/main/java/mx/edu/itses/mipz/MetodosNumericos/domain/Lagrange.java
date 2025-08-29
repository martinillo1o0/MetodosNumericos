package mx.edu.itses.mipz.MetodosNumericos.domain;

import java.util.List;
import lombok.Data;

@Data   
public class Lagrange {
   private String FX; // función a evaluar
    private double[] X; // valores de xi
    private double[] Y; // valores de f(xi)
    private double XR; // raíz aproximada
    private double Ea; // error estimado
    private int IteracionesMaximas;
    
    
}
