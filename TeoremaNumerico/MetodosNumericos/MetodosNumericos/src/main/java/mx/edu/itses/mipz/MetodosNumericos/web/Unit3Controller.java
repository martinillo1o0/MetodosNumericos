package mx.edu.itses.mipz.MetodosNumericos.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.EliminacionGaussiana;
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
        model.addAttribute("solveCramer",solveCramer);

        return "unit3/reglacramer/solvecramer";
    }
    ////////////////////////////////
    
    
    
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
    
    
    
    
    
    
    
    
    
    
    
    
}
