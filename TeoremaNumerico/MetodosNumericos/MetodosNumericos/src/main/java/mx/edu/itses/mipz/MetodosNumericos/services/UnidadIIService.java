package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.mipz.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.mipz.MetodosNumericos.domain.Secante;
import mx.edu.itses.mipz.MetodosNumericos.domain.SecanteModificado;

public interface UnidadIIService {

    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);

    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson);

    public ArrayList<Secante> AlgoritmoSecante(Secante secante);

    public ArrayList<SecanteModificado> AlgoritmoSecanteModificado(SecanteModificado secantemodificado);

    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);

    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo);
}
