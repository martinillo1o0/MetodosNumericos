
package mx.edu.itses.mipz.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class Unit4Controller {

     @Autowired
    private UnidadIVService unidadIVsrv;

    @GetMapping("/unit4")
    public String index() {
        return "unit4/index";
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   @GetMapping("/unit4/formlagrange")
    public String formLagrange(Model model) {
        Lagrange modelLagrange = new Lagrange();
        model.addAttribute("modelLagrange", modelLagrange);
        return "unit4/metododelagrange/formlagrange";
    }

    @PostMapping("/unit4/solvelagrange")
    public String solveLagrange(Lagrange modelLagrange, Model model) {
        var solveLagrange = unidadIVsrv.algoritmoLagrange(modelLagrange);
        model.addAttribute("solveLagrange", solveLagrange);
        return "unit4/metododelagrange/solvelagrange";
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/unit4/formddnewton")
    public String formDDNewton(Model model) {
        model.addAttribute("ddNewton", new DDNewton());
        return "unit4/diferenciasdivididasdenewton/formddnewton";
    }

    @PostMapping("/unit4/solveddnewton")
    public String solveDDNewton(DDNewton ddNewton, Model model) {
        model.addAttribute("solveDDNewton", unidadIVsrv.AlgoritmoDDNewton(ddNewton));
        return "unit4/diferenciasdivididasdenewton/solveddnewton";
    }
}
