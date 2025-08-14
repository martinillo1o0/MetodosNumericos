package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaCramer;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class UnidadIIIServiceImpl implements UnidadIIIService {

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        //Almacenamos los determinantes de las matrices
        ArrayList<Double> determinantes = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList();

        //Tamanio delsistema lineal
        switch (modelCramer.getMN()) {
            case 2 -> {

                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();
                //Vector de incognitas

                double[][] MatrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}

                };
                determinantes.add(Det2(MatrizA));

                /////Matriz 1
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1)},
                    {b.get(1), A.get(3)}

                };

                determinantes.add(Det2(MatrizX1));
                log.info("Determinante de X1: " + determinantes.get(1));
                //////////////Matriz 2
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0)},
                    {A.get(2), b.get(1)}

                };

                determinantes.add(Det2(MatrizX2));
                log.info("Determinante de X2: " + determinantes.get(2));

                //Resolviendo variables
                //Para X1
                vectorX.add(determinantes.get(1) / determinantes.get(0));
                //Para x2
                vectorX.add(determinantes.get(2) / determinantes.get(0));

            }

            case 3 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

                double[][] MatrizA = {
                    {A.get(0), A.get(1), A.get(2)},
                    {A.get(3), A.get(4), A.get(5)},
                    {A.get(6), A.get(7), A.get(8)}
                };
                determinantes.add(Det3(MatrizA)); // Determinante de A

                double[][] MatrizX1 = {
                    {b.get(0), A.get(1), A.get(2)},
                    {b.get(1), A.get(4), A.get(5)},
                    {b.get(2), A.get(7), A.get(8)}
                };
                determinantes.add(Det3(MatrizX1));

                double[][] MatrizX2 = {
                    {A.get(0), b.get(0), A.get(2)},
                    {A.get(3), b.get(1), A.get(5)},
                    {A.get(6), b.get(2), A.get(8)}
                };
                determinantes.add(Det3(MatrizX2));

                double[][] MatrizX3 = {
                    {A.get(0), A.get(1), b.get(0)},
                    {A.get(3), A.get(4), b.get(1)},
                    {A.get(6), A.get(7), b.get(2)}
                };
                determinantes.add(Det3(MatrizX3));

                vectorX.add(determinantes.get(1) / determinantes.get(0)); // X1
                vectorX.add(determinantes.get(2) / determinantes.get(0)); // X2
                vectorX.add(determinantes.get(3) / determinantes.get(0)); // X3

                //      ArrayList<Double> A = modelCramer.getMatrizA();
                //    ArrayList<Double> b = modelCramer.getVectorB();
                //Cambiar forma con un timelife ich para poder imprimir renglones y columnas, ocupamos 2, podemos agregar en el modelo una propiedad para agregar formato de matriz y no de vector(meterla como elementos dentro de las propiedades)
//Agregar en el main los diferentes metodos a resolver 
            }
            case 4 -> {

                
                
            }

        }

        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    private double Det2(double[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    public double Det3(double[][] m) {
        return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
    }
    
    
////////////////////////////////////////////////////////
    @Override
    public EliminacionGaussiana AlgoritmoElimGaussiana(EliminacionGaussiana modelElimGaussiana) {
        ArrayList<String> pasos = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList<>();

        
        ArrayList<Double> A = modelElimGaussiana.getMatrizA();
        ArrayList<Double> b = modelElimGaussiana.getVectorB();
        int n = modelElimGaussiana.getMN();

        double[][] matriz = new double[n][n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = A.get(index);
                index++;
            }
        }

        double[] resultados = new double[n];
        for (int i = 0; i < n; i++) {
            resultados[i] = b.get(i);
        }

        pasos.add("Sistema inicial:");
        pasos.add(formatearSistemaGauss(matriz, resultados, n));

        try {
            
            for (int i = 0; i < n - 1; i++) {
                // Buscar el pivote máximo
                int filaPivote = buscarPivoteMaximo(matriz, i, n);

            
                if (filaPivote != i) {
                    intercambiarFilas(matriz, resultados, i, filaPivote);
                    pasos.add("Intercambio de fila " + (i + 1) + " con fila " + (filaPivote + 1));
                    pasos.add(formatearSistemaGauss(matriz, resultados, n));
                }

             
                if (Math.abs(matriz[i][i]) < 1e-10) {
                    throw new ArithmeticException("El sistema no tiene solución única");
                }

            
                for (int j = i + 1; j < n; j++) {
                    if (Math.abs(matriz[j][i]) > 1e-10) {
                        double factor = matriz[j][i] / matriz[i][i];

                        pasos.add("Eliminando x" + (i + 1) + " de ecuación " + (j + 1)
                                + " (Factor: " + String.format("%.4f", factor) + ")");

                        for (int k = i; k < n; k++) {
                            matriz[j][k] -= factor * matriz[i][k];
                        }
                        resultados[j] -= factor * resultados[i];

                        pasos.add(formatearSistemaGauss(matriz, resultados, n));
                    }
                }
            }

           
            double[] solucion = new double[n];
            pasos.add("Sustitución hacia atrás:");

            for (int i = n - 1; i >= 0; i--) {
                double suma = 0;
                for (int j = i + 1; j < n; j++) {
                    suma += matriz[i][j] * solucion[j];
                }
                solucion[i] = (resultados[i] - suma) / matriz[i][i];

                pasos.add("x" + (i + 1) + " = " + String.format("%.6f", solucion[i]));
                vectorX.add(solucion[i]);
            }

            log.info("Solución Gauss: " + vectorX);

        } catch (Exception e) {
            log.error("Error en Gauss: " + e.getMessage());
            pasos.add("Error: " + e.getMessage());
            throw new RuntimeException("Error en eliminación Gaussiana: " + e.getMessage());
        }

        // Establecer resultados en el modelo
        modelElimGaussiana.setVectorX(vectorX);
        return modelElimGaussiana;
    }

    // Métodos auxiliares para eliminación Gaussiana
    private int buscarPivoteMaximo(double[][] matriz, int columna, int n) {
        int maxFila = columna;
        for (int i = columna + 1; i < n; i++) {
            if (Math.abs(matriz[i][columna]) > Math.abs(matriz[maxFila][columna])) {
                maxFila = i;
            }
        }
        return maxFila;
    }

    private void intercambiarFilas(double[][] matriz, double[] resultados, int fila1, int fila2) {
        double[] tempFila = matriz[fila1];
        matriz[fila1] = matriz[fila2];
        matriz[fila2] = tempFila;

        double tempResultado = resultados[fila1];
        resultados[fila1] = resultados[fila2];
        resultados[fila2] = tempResultado;
    }

    private String formatearSistemaGauss(double[][] matriz, double[] resultados, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && matriz[i][j] >= 0) {
                    sb.append(" + ");
                } else if (j > 0) {
                    sb.append(" ");
                }

                sb.append(String.format("%.4f", matriz[i][j]));
                sb.append("x").append(j + 1);
            }
            sb.append(" = ").append(String.format("%.4f", resultados[i])).append("\n");
        }
        return sb.toString();
    }
      

}
