package mx.edu.itses.mipz.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j

public class Unit2Controller {
   
    @GetMapping("unit2/formbisection")
    public String formBisection(){
        
        
        return "unit2/bisection/formbisection";
    }
    
    
}
