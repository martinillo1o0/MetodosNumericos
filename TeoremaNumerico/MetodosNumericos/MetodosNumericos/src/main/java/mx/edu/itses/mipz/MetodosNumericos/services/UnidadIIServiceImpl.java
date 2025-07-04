package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.mipz.MetodosNumericos.domain.Secante;
import mx.edu.itses.mipz.MetodosNumericos.domain.SecanteModificado;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIService {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion) {
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
            //renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

 

    @Override
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
/////////////////////////////////////////
    @Override
    public ArrayList<Secante> AlgoritmoSecante(Secante secante) {
        ArrayList<Secante> resultado = new ArrayList<>();

        double XI0 = secante.getXIAnterior();
        System.out.println("Valor de XI-1 fue recibido: " + secante.getXIAnterior());

        double XI1 = secante.getXI();
        double Ea = 100;
        int iteraciones = secante.getIteracionesMaximas();
        double tolerancia = secante.getTolerancia();
        String funcion = secante.getFuncion();
/////////
        for (int i = 1; i <= iteraciones; i++) {
            double fX0 = Funciones.Ecuacion(funcion, XI0);
            double fX1 = Funciones.Ecuacion(funcion, XI1);

            if ((fX1 - fX0) == 0) {
                break;
            }

            double numerador = fX1 * (XI1 - XI0);
            double denominador = fX1 - fX0;
            double xr = XI1 - numerador / denominador;

            if (i > 1) {
                Ea = Funciones.ErrorRelativo(xr, XI1);
            }

            Secante renglon = new Secante();
            renglon.setIteracion(i);
            renglon.setXIAnterior(XI0);
            renglon.setXI(XI1);
            renglon.setFXAnterior(fX0);
            renglon.setFX(fX1);
            renglon.setXR(xr);
            renglon.setEa(Ea);
            renglon.setTolerancia(tolerancia);
            renglon.getIteracionesMaximas();
            renglon.setFuncion(funcion);

            resultado.add(renglon);

            if (Ea <= tolerancia) {
                break;
            }

            XI0 = XI1;
            XI1 = xr;
        }
        return resultado;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secantemodificado) {
        ArrayList<SecanteModificado> resultado = new ArrayList<>();

        double xi = secantemodificado.getXI();
        double delta2 = secantemodificado.getDelta();
        double Ea = 100;
        int iteracionesMaximas = secantemodificado.getIteracionesMaximas();
        double tolerancia = secantemodificado.getTolerancia();
        String funcion = secantemodificado.getFuncion();

        System.out.println("Valor XI inicial recibido: " + xi);
        System.out.println("Valor de delta recibido: " + delta2);

        for (int i = 1; i <= iteracionesMaximas; i++) {
            double fxi = Funciones.Ecuacion(funcion, xi);
            double fxiDelta = Funciones.Ecuacion(funcion, xi + delta2 * xi);

            double denominador = (fxiDelta - fxi) / (delta2 * xi);

            if (denominador == 0 || Double.isInfinite(fxi) || Double.isNaN(fxi)
                    || Double.isInfinite(fxiDelta) || Double.isNaN(fxiDelta)) {
                System.out.println("La diivisión por cero o valor de función inválidus. Se dentendran las iteraciones");
                break;
            }

            double xr = xi - (fxi / denominador);

            if (i > 1) {
                Ea = Funciones.ErrorRelativo(xr, xi);
            }

            SecanteModificado renglon = new SecanteModificado();
            renglon.setIteracion(i);
            renglon.setXI(xi);
            renglon.setXIAnterior(0.0);
            renglon.setFX(fxi);
            renglon.setFXAnterior(fxiDelta);
            renglon.setXR(xr);
            renglon.setEa(Ea);
            renglon.setTolerancia(tolerancia);
            renglon.setIteracionesMaximas(iteracionesMaximas);
            renglon.setFuncion(funcion);
            renglon.setDelta(delta2);

            resultado.add(renglon);

            if (Ea <= tolerancia) {
                break;
            }

            xi = xr;
        }

        return resultado;

    }

}
