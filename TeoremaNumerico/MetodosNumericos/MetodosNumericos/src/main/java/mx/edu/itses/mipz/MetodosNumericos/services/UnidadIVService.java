package mx.edu.itses.mipz.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;

import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;

/**
 *
 * @author marti
 */
public interface UnidadIVService {

    Lagrange algoritmoLagrange(Lagrange modelLagrange);

    public ArrayList<DDNewton> AlgoritmoDDNewton(DDNewton ddNewton);
}
