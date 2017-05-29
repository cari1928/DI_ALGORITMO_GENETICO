package Clases;

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
    private List<Integer[]> lCromosomas, lCromoTmp; //lista de cromosomas

    public Algoritmo(int limInf, int limSup, double probMutación, int numCromosomas) {
        this.limInf = limInf;
        this.limSup = limSup;
        this.probMutación = probMutación;
        this.numCromosomas = numCromosomas;

        init();
    }

    public void init() {
        setNumBits(); //asigna el número de bits para cada cromosoma
    }

    private void setNumBits() {
        int numCombinaciones = limSup - limInf + 1;
        numBits = (int) Math.exp(Math.log(numCombinaciones / 2));
    }

    private int[] generaCromosomaRandom(int noCromo, int rangoI, int rangoF) {
        int cromosomas[] = new int[noCromo];
        Random rnd = new Random();
        for (int i = 0; i < cromosomas.length; i++) {
            cromosomas[i] = (int) (rnd.nextDouble() * rangoI + rangoF);
        }
        return cromosomas;
    }

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

}
