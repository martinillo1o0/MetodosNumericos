package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;

import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIVService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIVServicelmpl implements UnidadIVService {

    @Override

    public Lagrange algoritmoLagrange(Lagrange modelLagrange) {
        ArrayList<Double> x = modelLagrange.getXValues();
        ArrayList<Double> y = modelLagrange.getYValues();
        double xp = modelLagrange.getPuntoEvaluar();
        int n = modelLagrange.getN();
        double resultado = 0.0;

        for (int i = 0; i < n; i++) {
            double li = 1.0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    li *= (xp - x.get(j)) / (x.get(i) - x.get(j));
                }
            }
            resultado += li * y.get(i);
        }

        modelLagrange.setResultado(resultado);
        return modelLagrange;
    }

/////////////////////////////////////////////////////////////////////////////////
    public ArrayList<DDNewton> AlgoritmoDDNewton(DDNewton ddNewton) {
        ArrayList<DDNewton> respuesta = new ArrayList<>();
        int n = ddNewton.getX().length; // número de puntos
        double xr = 0;
        double ea = 100;

        double[][] tabla = new double[n][n];
        for (int i = 0; i < n; i++) {
            tabla[i][0] = ddNewton.getY()[i];
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                tabla[i][j] = (tabla[i + 1][j - 1] - tabla[i][j - 1]) / (ddNewton.getX()[i + j] - ddNewton.getX()[i]);
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

            if (ea <= ddNewton.getEa()) {
                break;
            }
        }

        return respuesta;
    }

}
