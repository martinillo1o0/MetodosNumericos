package mx.edu.itses.mipz.MetodosNumericos.domain;

import java.util.ArrayList;

import lombok.Data;

@Data   
public class Lagrange {
 private int n; // número de puntos
    private ArrayList<Double> xPoints; // puntos x
    private ArrayList<Double> yPoints; // puntos y
    private ArrayList<Double> result; 
    
}
