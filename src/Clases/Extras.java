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

    public static Integer[] convertToBinaryIntegerArray(int num, int numBits) {
        String binary = Integer.toBinaryString(num);
        Integer[] tmp = new Integer[numBits]; //crea el arreglo que almacenará a este número
        char[] p = binary.toCharArray();
        int punto = tmp.length - p.length, cont = 0;

        for (int i = 0; i < tmp.length; i++) {
            if (i >= punto) {
                tmp[i] = Integer.parseInt(String.valueOf(p[cont]));
                ++cont;
            } else {
                tmp[i] = 0;
            }
        }
        return tmp;
    }

}
