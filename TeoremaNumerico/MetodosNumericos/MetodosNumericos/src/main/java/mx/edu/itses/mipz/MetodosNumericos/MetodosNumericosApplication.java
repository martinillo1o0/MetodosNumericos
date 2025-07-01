package mx.edu.itses.mipz.MetodosNumericos;

import org.mariuszgromada.math.mxparser.License;
import org.mariuszgromada.math.mxparser.mXparser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MetodosNumericosApplication {

	public static void main(String[] args) {
            boolean isCalledSuccessful = License.iConfirmNonCommercialUse("Materia Metodos Numericos");
	String message =License.getUseTypeConfirmationMessage();
        
        mXparser.consolePrint("Message: " +message);
        
            SpringApplication.run(MetodosNumericosApplication.class, args);
                
                
                
            
	}

}
