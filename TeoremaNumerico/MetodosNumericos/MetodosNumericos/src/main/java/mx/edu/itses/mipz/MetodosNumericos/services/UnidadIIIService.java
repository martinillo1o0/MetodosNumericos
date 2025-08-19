package mx.edu.itses.mipz.MetodosNumericos.services;

import mx.edu.itses.mipz.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.mipz.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaCramer;

public interface UnidadIIIService {

    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer);

    public EliminacionGaussiana AlgoritmoElimGaussiana(EliminacionGaussiana modelElimGaussiana);

    public GaussJordan AlgoritmoGaussJordan (GaussJordan modelGaussJordan);
    
}
