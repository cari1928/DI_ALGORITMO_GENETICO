package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Tenistas
 */
public class Tools {

    List<String> lEpoch;
    List<String> lLabels;
    List<String> lCromoBin;
    List<String> lCromoInt;
    List<String> lFitnes;
    List<String> lRatio;
    List<String> lCrossover;
    List<String> lMutation;

    public Tools() {
        lEpoch = new ArrayList<>();
        lLabels = new ArrayList<>();
        lCromoBin = new ArrayList<>();
        lCromoInt = new ArrayList<>();
        lFitnes = new ArrayList<>();
        lRatio = new ArrayList<>();
        lCrossover = new ArrayList<>();
        lMutation = new ArrayList<>();
    }

    public List<String> getlEpoch() {
        return lEpoch;
    }

    public void setlEpoch(List<String> lEpoch) {
        this.lEpoch = lEpoch;
    }

    public List<String> getlLabels() {
        return lLabels;
    }

    public void setlLabels(List<String> lLabels) {
        this.lLabels = lLabels;
    }

    public List<String> getlFitnes() {
        return lFitnes;
    }

    public void setlFitnes(List<String> lFitnes) {
        this.lFitnes = lFitnes;
    }

    public List<String> getlRatio() {
        return lRatio;
    }

    public void setlRatio(List<String> lRatio) {
        this.lRatio = lRatio;
    }

    public void addEpoch(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i);
            lEpoch.add(tmp);
        }
    }

    public void addLabel(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i);
            lLabels.add(tmp);
        }
    }

    public void addCromoBin(List<Integer[]> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = Arrays.toString(list.get(i));
            lCromoBin.add(tmp);
        }

        System.out.println("TOOLSCROMOBIN");
        for (int i = 0; i < lCromoBin.size(); i++) {
            System.out.println(lCromoBin.get(i).toString());
        }
    }

    public void addCromoInt(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i).toString();
            lCromoInt.add(tmp);
        }
    }

    public void addFitnes(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i).toString();
            lFitnes.add(tmp);
        }
    }

    public void addRatio(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = list.get(i).toString();
            lRatio.add(tmp);
        }
    }

    public void addCrossover(List<Integer[]> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = Arrays.toString(list.get(i));
            lCrossover.add(tmp);
        }
    }

    public void addMutation(List<Integer[]> list) {
        for (int i = 0; i < list.size(); i++) {
            String tmp = Arrays.toString(list.get(i));
            lMutation.add(tmp);
        }
    }

    public static double[] addMutation(int limite, double incremento) {
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

    public static double[] generaRango(int limite, double incremento) {
        double tmp = 0;
        double[] arrayTmp = new double[limite];
        for (int i = 0; i < limite; i++) {
            tmp += incremento;
            arrayTmp[i] = tmp;
        }
        return arrayTmp;
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

    public static int binToDec(String numeroBinario) {
        return Integer.parseInt(numeroBinario, 2);
    }

    public void finalArray() {
        String[] tmp;
        List<String[]> datos = new ArrayList<>();
        for (int i = 0; i < lFitnes.size(); i++) {
            tmp = new String[8];
            tmp[0] = lEpoch.get(i);
            tmp[1] = lLabels.get(i);
            tmp[2] = lCromoInt.get(i);
            tmp[3] = lCromoBin.get(i);
            tmp[4] = lFitnes.get(i);
            tmp[5] = lRatio.get(i);
            tmp[6] = lCrossover.get(i);
            tmp[7] = lMutation.get(i);

            datos.add(tmp);
        }

        Tabla objT = new Tabla(
                new String[]{"Epoch", "Labels", "Integer", "Binary", "Fitness", "Ratio", "Crossover", "Mutation"},
                datos);
    }

    public static List<Integer[]> pasaList(List<Integer[]> list) {
        List<Integer[]> lTmp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Integer[] tmp = list.get(i);
            lTmp.add(tmp);
        }
        return lTmp;
    }

}
