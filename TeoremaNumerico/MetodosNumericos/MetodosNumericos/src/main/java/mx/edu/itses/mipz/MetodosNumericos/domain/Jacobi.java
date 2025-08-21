package mx.edu.itses.mipz.MetodosNumericos.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Jacobi {
   private int MN;                       
    private ArrayList<Double> matrizA;   
    private ArrayList<Double> vectorB;   
    private ArrayList<Double> vectorX;
    private ArrayList<Double> vectorXPrev;
    private int iteracionesMaximas;       
    private double Ea;               
    private ArrayList<Double> errores; 
   
}
