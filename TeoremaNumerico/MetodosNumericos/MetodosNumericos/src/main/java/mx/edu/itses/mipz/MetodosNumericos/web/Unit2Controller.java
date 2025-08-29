package mx.edu.itses.mipz.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.mipz.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.mipz.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.mipz.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.mipz.MetodosNumericos.domain.Secante;
import mx.edu.itses.mipz.MetodosNumericos.domain.SecanteModificado;
import mx.edu.itses.mipz.MetodosNumericos.services.Funciones;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j

public class Unit2Controller {

    @Autowired
    private UnidadIIService unidadIIsrv;

    @GetMapping("/unit2")
    public String index(Model model) {
        return "unit2/index";
    }
/////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("unit2/formbisection")
    public String formBisection(Model model) {

        Biseccion bisection = new Biseccion();

        model.addAttribute("bisection", bisection);

        return "unit2/bisection/formbisection";
    }

    @PostMapping("unit2/solvebisection")
    public String solvebisection(Biseccion bisection, Model model) {

        //  double valorFX = Funciones.Ecuacion(bisection.getFX(), bisection.getXL());
        //  log.info("Valor de FX: " + bisection.getFX());
        var solveBisection = unidadIIsrv.AlgoritmoBiseccion(bisection);

        //log.info("Arreglo: " + solveBisection);
        model.addAttribute("solveBisection", solveBisection);

        return "unit2/bisection/solvebisection";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/unit2/formreglafalsa")
    public String formReglaFalsa(Model model) {
        ReglaFalsa reglaFalsa = new ReglaFalsa();
        model.addAttribute("reglaFalsa", reglaFalsa);
        return "unit2/reglafalsa/formreglafalsa";
    }

// POST: resuelve la regla falsa
    @PostMapping("/unit2/solverreglafalsa")
    public String solveReglaFalsa(ReglaFalsa reglaFalsa, Model model) {
        var resultados = unidadIIsrv.AlgoritmoReglaFalsa(reglaFalsa);
        log.info("Resultados: " + resultados);

        model.addAttribute("solveReglaFalsa", resultados);
        return "unit2/reglafalsa/solveReglaFalsa";
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/unit2/formpuntofijo")
    public String formPuntoFijo(Model model) {
        PuntoFijo puntoFijo = new PuntoFijo();
        model.addAttribute("puntoFijo", puntoFijo);
        return "unit2/puntofijo/formpuntofijo";
    }

    @PostMapping("/unit2/solvepuntofijo")
    public String solvePuntoFijo(PuntoFijo puntoFijo, Model model) {
        var resultados = unidadIIsrv.AlgoritmoPuntoFijo(puntoFijo);
        log.info("Resultados: " + resultados);

        model.addAttribute("solvePuntoFijo", resultados);
        return "unit2/puntofijo/solvepuntofijo";
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/unit2/formnewtonrapshon")
    public String formNewton(Model model) {
        NewtonRaphson newtonRaphson = new NewtonRaphson();
        model.addAttribute("newtonRaphson", newtonRaphson);
        return "unit2/newtonraphson/formnewtonraphson";
    }

    @PostMapping("/unit2/solvenewtonrapshon")
    public String solveNewton(NewtonRaphson newtonRaphson, Model model) {
        var resultados = unidadIIsrv.AlgoritmoNewtonRaphson(newtonRaphson);
        model.addAttribute("solveNewton", resultados);
        return "unit2/newtonraphson/solvenewtonraphson";
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/unit2/formsecante")
    public String formSecante(Model model) {
        Secante secante = new Secante();
        model.addAttribute("secante", secante);
        return "unit2/secante/formsecante";
    }

    @PostMapping("/unit2/solvesecante")
    public String solveSecante(Secante secante, Model model) {
        var resultados = unidadIIsrv.AlgoritmoSecante(secante);
        log.info("Resultados: " + resultados);

        model.addAttribute("solveSecante", resultados);
        return "unit2/secante/solvesecante";
    }
//////////////////////////////////////////////////////////////////

    @GetMapping("/unit2/formsecantemodificado")
    public String formSecanteM(Model model) {
        SecanteModificado secanteM = new SecanteModificado();
        model.addAttribute("secanteM", secanteM);
        return "unit2/secantemodificado/formsecantemodificado";
    }

    @PostMapping("/unit2/solvesecantemodificado")
    public String solveSecanteM(SecanteModificado secanteM, Model model) {
        var resultados = unidadIIsrv.AlgoritmoSecanteModificado(secanteM);
        log.info("Resultados: " + resultados);

        model.addAttribute("solveSecanteM", resultados);
        return "unit2/secantemodificado/solvesecantemodificado";
    }

}
