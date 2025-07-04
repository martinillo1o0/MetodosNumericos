package mx.edu.itses.mipz.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.Biseccion;
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

}
