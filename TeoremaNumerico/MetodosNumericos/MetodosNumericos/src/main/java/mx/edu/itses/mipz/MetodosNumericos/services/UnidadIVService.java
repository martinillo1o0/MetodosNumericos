package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;

import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;

/**
 *
 * @author marti
 */
public interface UnidadIVService {

    public ArrayList<Lagrange> AlgoritmoLagrange(Lagrange lagrange);

    public ArrayList<DDNewton> AlgoritmoDDNewton(DDNewton ddNewton);
}
