package mx.edu.itses.mipz.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
//Get : Obtiene los datos de la web para poder acceder
//Post: Envia los datos recabados al servidor
public class MainController {

    @GetMapping("/")
    public String inicio(Model model) {
        int i=1;
        log.info("Mensaje de salida: {}",i);
        model.addAttribute("valori", i);
        return "index";
        
    }
}
