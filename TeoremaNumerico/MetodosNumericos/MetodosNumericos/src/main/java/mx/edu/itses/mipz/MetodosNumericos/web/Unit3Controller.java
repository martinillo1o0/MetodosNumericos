package mx.edu.itses.mipz.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.EliminacionGaussiana;
import mx.edu.itses.mipz.MetodosNumericos.domain.GaussJordan;
import mx.edu.itses.mipz.MetodosNumericos.domain.GaussSeidel;
import mx.edu.itses.mipz.MetodosNumericos.domain.Jacobi;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaCramer;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit3Controller {

    @Autowired
    private UnidadIIIService unidadIIIsrv;

    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index";
    }
////////////////////////////////////////////////

    @GetMapping("/unit3/formcramer")
    public String formBisection(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer", modelCramer);
        return "unit3/reglacramer/formcramer";
    }

    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer, Errors errores, Model model) {
        // log.info("Objetos: " + modelCramer.getMatrizA());
        var solveCramer = unidadIIIsrv.AlgoritmoReglaCramer(modelCramer);
        //log.info("Determinantes: " + solveCramer.getDeterminantes());
        model.addAttribute("solveCramer", solveCramer);

        return "unit3/reglacramer/solvecramer";
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/unit3/formgauss")
    public String formGauss(Model model) {
        EliminacionGaussiana modelGauss = new EliminacionGaussiana();
        model.addAttribute("modelGauss", modelGauss);
        return "unit3/eliminaciongaussiana/formgauss";
    }

    @PostMapping("/unit3/solvegauss")
    public String solveGauss(EliminacionGaussiana modelGauss, Errors errores, Model model) {
        //log.info("OBJECTOS:" + modelGauss.getMatrizA());
        ArrayList<Double> A = modelGauss.getMatrizA();
        var solveElimGauss = unidadIIIsrv.AlgoritmoElimGaussiana(modelGauss);
        //log.info("Solución: " + solveGauss.getVectorX());
        //log.info("Pasos: " + solveGauss.getPasos());
        model.addAttribute("solveElimGauss", solveElimGauss);
        return "unit3/eliminaciongaussiana/solvegauss";
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/unit3/formgaussjordan")
    public String formGaussJordan(Model model) {
        GaussJordan modelGaussJordan = new GaussJordan();
        model.addAttribute("modelGaussJordan", modelGaussJordan);
        return "unit3/gaussjordan/formgaussjordan";
    }

    @PostMapping("/unit3/solvegaussjordan")
    public String solveGaussJordan(GaussJordan modelGaussJordan, Errors errores, Model model) {

        var solveGaussJordan = unidadIIIsrv.AlgoritmoGaussJordan(modelGaussJordan);

        model.addAttribute("solveGaussJordan", solveGaussJordan);
        return "unit3/gaussjordan/solvegaussjordan";
    }
    ////////////////////////////////////////////////////////   

    @GetMapping("/unit3/formjacobi")
    public String formJacobi(Model model) {
        Jacobi modelJacobi = new Jacobi();
        model.addAttribute("modelJacobi", modelJacobi);
        return "unit3/jacobi/formjacobi";
    }

    @PostMapping("/unit3/solvejacobi")
    public String solveJacobi(Jacobi modelJacobi, Model model) {
        var resultado = unidadIIIsrv.AlgoritmoJacobi(modelJacobi);
        model.addAttribute("solveJacobi", resultado);
        return "unit3/jacobi/solvejacobi";
    }
//////////////////////////////////////////////////////////

    @GetMapping("/unit3/formgaussseidel")
    public String formGaussSeidel(Model model) {
        GaussSeidel modelGS = new GaussSeidel();
        model.addAttribute("modelGaussSeidel", modelGS);
        return "unit3/gaussseidel/formgaussseidel";
    }

    @PostMapping("/unit3/solvegaussseidel")
    public String solveGaussSeidel(GaussSeidel modelGaussSeidel, Model model) {
        var resultado = unidadIIIsrv.AlgoritmoGaussSeidel(modelGaussSeidel);
        model.addAttribute("solveGaussSeidel", resultado);
        return "unit3/gaussseidel/solvegaussseidel";
    }

}
