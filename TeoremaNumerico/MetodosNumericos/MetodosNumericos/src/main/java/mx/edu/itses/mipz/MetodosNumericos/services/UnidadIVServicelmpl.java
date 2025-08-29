
package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;

import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIVService;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UnidadIVServicelmpl implements UnidadIVService{
     @Override
 
    public Lagrange algoritmoLagrange(Lagrange modelLagrange) {
        int n = modelLagrange.getN();
        ArrayList<Double> x = modelLagrange.getXPoints();
        ArrayList<Double> y = modelLagrange.getYPoints();
        ArrayList<Double> result = new ArrayList<>();

        // Evaluar el polinomio en cada x (opcional, también puedes retornar coeficientes)
        for (int i = 0; i < n; i++) {
            double yi = 0;
            for (int j = 0; j < n; j++) {
                double term = y.get(j);
                for (int k = 0; k < n; k++) {
                    if (k != j) {
                        term *= (x.get(i) - x.get(k)) / (x.get(j) - x.get(k));
                    }
                }
                yi += term;
            }
            result.add(yi);
        }

        modelLagrange.setResult(result);
        return modelLagrange;
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
