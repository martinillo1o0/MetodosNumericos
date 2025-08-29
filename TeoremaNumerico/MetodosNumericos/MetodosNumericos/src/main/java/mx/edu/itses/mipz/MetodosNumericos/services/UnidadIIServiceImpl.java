package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.mipz.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaFalsa;
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
        ArrayList<NewtonRaphson> respuesta = new ArrayList<>();

        double XI = newtonRaphson.getXI();
        double Ea = 100;
        String FX = newtonRaphson.getFX();

        for (int i = 1; i <= newtonRaphson.getIteracionesMaximas(); i++) {
            double FXi = Funciones.Ecuacion(FX, XI);
            double FDXi = Funciones.Derivada(FX, XI);

            if (FDXi == 0) {
                System.err.println("Error: Derivada es cero en XI = " + XI + ". No se puede continuar.");
                break;
            }

            double XR = XI - (FXi / FDXi);

            if (i != 1) {
                Ea = Funciones.ErrorRelativo(XR, XI);
            }

            NewtonRaphson renglon = new NewtonRaphson();
            renglon.setXI(XI);
            renglon.setFX(String.valueOf(FXi));
            renglon.setFDX(String.valueOf(FDXi));
            renglon.setXR(XR);
            renglon.setEa(Ea);
            renglon.setIteracion(i);

            respuesta.add(renglon);

            if (Ea <= newtonRaphson.getEa()) {
                break;
            }

            XI = XR;
        }

        return respuesta;
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
      for (int i = 1; i <= iteraciones; i++) {
    double fX0 = Funciones.Ecuacion(funcion, XI0);
    double fX1 = Funciones.Ecuacion(funcion, XI1);

    // Evitar división entre cero
    if ((fX1 - fX0) == 0) {
        System.out.println("División entre cero detectada en iteración " + i);
        break;
    }

    double xr = XI1 - (fX1 * (XI1 - XI0)) / (fX1 - fX0);

    if (i > 1) {
        // Evitar error relativo con denominador 0
        if (xr != 0) {
            Ea = Math.abs((xr - XI1) / xr) * 100;
        } else {
            Ea = 0; // o ponle un valor grande
        }
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
            System.out.println("La división por cero o valor de función inválido. Se detendrán las iteraciones");
            break;
        }

        double xr = xi - (fxi / denominador);

        if (i > 1) {
            Ea = Funciones.ErrorRelativo(xr, xi);
        }

        SecanteModificado renglon = new SecanteModificado();
        renglon.setIteracion(i);
        renglon.setXI(xi);
        renglon.setXIAnterior(0.0); // opcional: puedes guardar xi-1 si lo necesitas
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

    @Override
    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa) {
        ArrayList<ReglaFalsa> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = reglafalsa.getXL();
        XU = reglafalsa.getXU();
        XRa = 0;
        Ea = 100;
        // Verificamos que en el intervalo definido haya un cambio de signo
        FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
        FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);

        if (FXL * FXU < 0) {
            for (int i = 1; i <= reglafalsa.getIteracionesMaximas(); i++) {
                XRn = XU - (FXU * (XL - XU)) / (FXL - FXU);
                FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
                FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);
                FXR = Funciones.Ecuacion(reglafalsa.getFX(), XRn);

                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                ReglaFalsa renglon = new ReglaFalsa();
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
                if (Ea <= reglafalsa.getEa()) {
                    break;
                }
            }
        } else {
            ReglaFalsa renglon = new ReglaFalsa();
            // renglon.setIntervaloInvalido(true);
            respuesta.add(renglon);
        }

        return respuesta;

    }

    @Override
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo) {
         ArrayList<PuntoFijo> respuesta = new ArrayList<>();
    double xi, xr, Ea;
    double errorAprox = 100;
    int iterMax = puntofijo.getIteracionesMaximas();

    xi = puntofijo.getXi();
    Ea = puntofijo.getEa(); 

    for (int i = 1; i <= iterMax; i++) {
        xr = Funciones.EvaluarG(puntofijo.getGx(), xi);  
        if (i != 1) {
            errorAprox = Funciones.ErrorRelativo(xr, xi);
            if (Double.isNaN(errorAprox)) {
                log.error("Error relativo inválido en iteración {}. xi={}, xr={}", i, xi, xr);
                break;
            }
        }

        PuntoFijo paso = new PuntoFijo();
        paso.setIteracion(i);            
        paso.setXi(xi);                
        paso.setXr(xr);                 
        paso.setErrorAprox(errorAprox);  //  Aquí guardamos el error aproximado
        paso.setGx(puntofijo.getGx());
        paso.setIteracionesMaximas(iterMax);
        respuesta.add(paso);

        if (errorAprox <= Ea) {
            break;
        }

        xi = xr; 
    }

    return respuesta;

    }
}
