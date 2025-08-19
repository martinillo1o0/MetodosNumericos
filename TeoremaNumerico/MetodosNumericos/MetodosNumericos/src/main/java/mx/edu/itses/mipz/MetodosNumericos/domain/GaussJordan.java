
package mx.edu.itses.mipz.MetodosNumericos.domain;
import java.util.ArrayList;
import lombok.Data;

@Data 


public class GaussJordan {
private int MN;
private ArrayList<Double> matrizA;
private ArrayList<Double> vectorB;
private ArrayList<Double> vectorX;
private ArrayList<Double> determinantes; 

}
