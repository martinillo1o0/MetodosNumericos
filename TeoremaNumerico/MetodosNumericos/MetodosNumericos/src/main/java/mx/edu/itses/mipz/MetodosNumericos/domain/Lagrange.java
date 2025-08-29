package mx.edu.itses.mipz.MetodosNumericos.domain;

import java.util.ArrayList;

import lombok.Data;

@Data   
public class Lagrange {
 private int n; 
    private ArrayList<Double> xValues;
    private ArrayList<Double> yValues;
    private ArrayList<Double> lagrangeCoefficients;
    private double resultado;
    private double puntoEvaluar;
}
