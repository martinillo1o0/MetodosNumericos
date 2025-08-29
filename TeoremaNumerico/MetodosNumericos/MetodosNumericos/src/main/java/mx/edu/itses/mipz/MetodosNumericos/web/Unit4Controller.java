/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itses.mipz.MetodosNumericos.web;

import mx.edu.itses.mipz.MetodosNumericos.domain.DDNewton;
import mx.edu.itses.mipz.MetodosNumericos.domain.Lagrange;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIIService;
import mx.edu.itses.mipz.MetodosNumericos.services.UnidadIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author marti
 */
public class Unit4Controller {

     @Autowired
    private UnidadIVService unidadIVsrv;

    @GetMapping("/unit4")
    public String index() {
        return "unit4/index";
    }

    @GetMapping("/unit4/formlagrange")
    public String formLagrange(Model model) {
        model.addAttribute("lagrange", new Lagrange());
        return "unit4/metododelagrange/formlagrange";
    }

    @PostMapping("/unit4/solvelagrange")
    public String solveLagrange(Lagrange lagrange, Model model) {
        model.addAttribute("solveLagrange", unidadIVsrv.AlgoritmoLagrange(lagrange));
        return "unit4/metododelagrange/solvelagrange";
    }

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
