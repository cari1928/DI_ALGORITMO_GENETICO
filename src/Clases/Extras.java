package Clases;

import java.util.Random;

/**
 *
 * @author Tenistas
 */
public class Extras {

    public static double[] generaRango(int limite, double incremento) {
        double tmp = 0;
        double[] arrayTmp = new double[limite];
        for (int i = 0; i < limite; i++) {
            tmp += incremento;
            arrayTmp[i] = tmp;
        }
        return arrayTmp;
    }

    /**
     * Obtiene un número aleatorio
     */
    public static double getRandom() {
        Random random = new Random();
        return random.nextDouble();
    }

}
