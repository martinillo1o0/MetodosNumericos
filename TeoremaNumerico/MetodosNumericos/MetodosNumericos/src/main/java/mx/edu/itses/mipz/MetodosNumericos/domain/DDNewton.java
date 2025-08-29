package mx.edu.itses.mipz.MetodosNumericos.domain;

import java.util.List;
import lombok.Data;

@Data

public class DDNewton {

   private String FX;     
    private double[] X;     
    private double[] Y;  
    private double XR;     
    private double Ea;      
    private int IteracionesMaximas;
}
