
package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;

import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;



/**
 *
 * @author marti
 */
public class UnidadIVServicelmpl {
    
 public ArrayList<Lagrange> AlgoritmoLagrange(Lagrange lagrange) {
        ArrayList<Lagrange> respuesta = new ArrayList<>();
        int n = lagrange.getX().length; // número de puntos
        double xr = 0;
        double ea = 100;

        for (int iter = 0; iter < lagrange.getIteracionesMaximas(); iter++) {
            double numerador, denominador;
            xr = 0;
            for (int i = 0; i < n; i++) {
                numerador = lagrange.getY()[i];
                denominador = 1;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        denominador *= (lagrange.getX()[i] - lagrange.getX()[j]);
                    }
                }
                xr += numerador / denominador; // suma término de Lagrange
            }

            // Error relativo aproximado
            if (iter != 0) {
                ea = Math.abs((xr - lagrange.getXR()) / xr) * 100;
            }

            lagrange.setXR(xr);
            lagrange.setEa(ea);

            // Guardamos cada iteración
            Lagrange renglon = new Lagrange();
            renglon.setX(lagrange.getX());
            renglon.setY(lagrange.getY());
            renglon.setXR(xr);
            renglon.setEa(ea);
            respuesta.add(renglon);

            if (ea <= lagrange.getEa()) break;
        }

        return respuesta;
    }

/////////////////////////////////////////////////////////////////////////////////
    
    public ArrayList<DDNewton> AlgoritmoDDNewton(DDNewton ddNewton) {
        ArrayList<DDNewton> respuesta = new ArrayList<>();
        int n = ddNewton.getX().length; // número de puntos
        double xr = 0;
        double ea = 100;

        // Creamos tabla de diferencias divididas
        double[][] tabla = new double[n][n];
        for (int i = 0; i < n; i++) {
            tabla[i][0] = ddNewton.getY()[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabla[i][j] = (tabla[i+1][j-1] - tabla[i][j-1]) / (ddNewton.getX()[i+j] - ddNewton.getX()[i]);
            }
        }

        for (int iter = 0; iter < ddNewton.getIteracionesMaximas(); iter++) {
            xr = tabla[0][0]; // primer término
            double producto;
            for (int i = 1; i < n; i++) {
                producto = 1;
                for (int j = 0; j < i; j++) {
                    producto *= (ddNewton.getX()[i] - ddNewton.getX()[j]);
                }
                xr += tabla[0][i] * producto;
            }

            // Error relativo
            if (iter != 0) {
                ea = Math.abs((xr - ddNewton.getXR()) / xr) * 100;
            }

            ddNewton.setXR(xr);
            ddNewton.setEa(ea);

            // Guardamos cada iteración
            DDNewton renglon = new DDNewton();
            renglon.setX(ddNewton.getX());
            renglon.setY(ddNewton.getY());
            renglon.setXR(xr);
            renglon.setEa(ea);
            respuesta.add(renglon);

            if (ea <= ddNewton.getEa()) break;
        }

        return respuesta;
    }

}
