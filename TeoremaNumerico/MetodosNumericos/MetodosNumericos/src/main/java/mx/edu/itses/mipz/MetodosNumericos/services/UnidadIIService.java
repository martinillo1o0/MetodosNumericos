package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.NewtonRaphson;

public interface UnidadIIService {
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);
        
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson);

    
}
