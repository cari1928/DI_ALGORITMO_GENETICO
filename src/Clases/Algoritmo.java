package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algoritmo {

    //datos ingresados por el usuario
    private int limInf, limSup;
    private double probMutación; //probabilidad de mutación 
    private int numCromosomas;

    //datos calculados por el programa
    private int numBits;
    //población actual y temporal de cromosomas
    private List<Integer[]> lCromoBin; //lista de cromosomas binarios
    private List<Integer> lCromoInt; //lista de cromosomas enteros, por si se necesita
    private List<Integer[]> lCromoTmp;  //poblacion temporal de cromosomas
    private int[] fitness; //fórmula a evaluar

    public Algoritmo(int limInf, int limSup, double probMutación, int numCromosomas, int[] fitness) {
        this.limInf = limInf;
        this.limSup = limSup;
        this.probMutación = probMutación;
        this.numCromosomas = numCromosomas;
        this.fitness = fitness;

        init();
    }

    public void init() {
        setNumBits(); //asigna el número de bits para cada cromosoma
        cromFamily(); //Genera la población actual de cromosomas

    }

    /**
     * Calcula el número de bits para cada cromosoma
     */
    private void setNumBits() {
        int numCombinaciones = limSup - limInf + 1;
        numBits = (int) Math.exp(Math.log(numCombinaciones / 2));
    }

    /**
     * Genera la población actual de cromosomas
     */
    private void cromFamily() {
        List<Integer> tmp = new ArrayList<>();
        Random rnd = new Random();
        int num;
        for (int i = 0; i < numCromosomas; i++) {
            num = limInf + rnd.nextInt(limSup);

            if (!tmp.contains(num)) {
                tmp.add(num);
                lCromoBin.add(Extras.convertToBinaryIntegerArray(num, numBits)); //guarda el cromo binario
                lCromoInt.add(num); //guarda cromo entero, por si se necesita
            }
        }
    }

    /**
     * ???
     */
    private int[] generaCromosomaRandom(int noCromo, int rangoI, int rangoF) {
        int cromosomas[] = new int[noCromo];
        Random rnd = new Random();
        for (int i = 0; i < cromosomas.length; i++) {
            cromosomas[i] = (int) (rnd.nextDouble() * rangoI + rangoF);
        }
        return cromosomas;
    }

    /**
     * ???
     */
    private String[] convertirBinarios(int cromosomas[]) {
        String[] binarios = new String[cromosomas.length];
        for (int i = 0; i < binarios.length; i++) {
            binarios[i] = Integer.toBinaryString(cromosomas[i]);
        }
        return binarios;
    }

    private int[] evaluar(int[] cromosomas, double A, double B, double C) {
        int[] valores = new int[cromosomas.length];
        for (int i = 0; i < valores.length; i++) {
            valores[i] = 0;
            if (A != 0) {
                valores[i] += A * cromosomas[i];
            }
            if (B != 0) {
                valores[i] += B * (cromosomas[i] * cromosomas[i]);
            }
            if (C != 0) {
                valores[i] += C;
            }
        }
        return valores;
    }

    private double[] obtenerPorcentaje(int[] valores) {
        double porcentaje[] = new double[valores.length];
        double total = 0;
        for (int i = 0; i < valores.length; i++) {
            total += valores[i];
        }
        for (int i = 0; i < valores.length; i++) {
            porcentaje[i] = valores[i] * total;
        }
        return porcentaje;
    }

    private void crossover(Integer[] madre, Integer[] padre) {
        //double rand = Extras.getRandom(); //final

        double rand = 0.85;//pruebas

        double probabilidad = ((double) (1) / (madre.length - 1));
        double[] rango = Extras.generaRango(madre.length - 1, probabilidad);
        int tmp;

        for (int i = 0; i < rango.length; i++) {
            if (rand <= rango[i]) { //i+1 es el punto de corte
                for (int j = (i + 1); j < madre.length; j++) { //comienza a cambiar lugares
                    tmp = madre[j];
                    madre[j] = padre[j];
                    padre[j] = tmp;
                }

                //guarda la nueva población
                lCromoTmp.add(madre);
                lCromoTmp.add(padre);
            }
        }
    }

    private void mutation(Integer[] cromosoma) {
        double numRandom;
        for (int i = 0; i < cromosoma.length; i++) {
            numRandom = Extras.getRandom();

            if (numRandom > probMutación) {
                if (cromosoma[i] == 0) {
                    cromosoma[i] = 1; //de 0 cambia a 1
                } else {
                    cromosoma[i] = 0; //de 1 cambia a 0
                }
            }
        }
    }

    public static void main(String[] args) {
        Algoritmo objA = new Algoritmo(0, 100, 0.3, 6, new int[]{2, 3, 0}); //equivale a la formula 2x^2+3x+0
    }

}
