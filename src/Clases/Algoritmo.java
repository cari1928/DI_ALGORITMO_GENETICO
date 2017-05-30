package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Algoritmo {

    //datos ingresados por el usuario
    private int limInf, limSup;
    private double probMutación; //probabilidad de mutación 
    private int numCromosomas;
    private int numExecutions;

    //datos calculados por el programa
    private int numBits;
    //población actual y temporal de cromosomas
    private List<Integer[]> lCromoBin; //lista de cromosomas binarios
    private List<Integer> lCromoInt; //lista de cromosomas enteros, por si se necesita
    private List<Integer[]> lCromoTmp;  //poblacion temporal de cromosomas
    private List<Double> lFitness;
    private List<Double> lRatio;
    private List<Integer[]> lCrossover;
    private List<Integer[]> lMutation;
    private List<String> lResultados;
    private List<String> lEpoch;
    private List<String> lLabels;
    private int[] fitness; //fórmula a evaluar
    private int epoch;
    private int countX;
    private Tools objT;

    public Algoritmo(int limInf, int limSup, double probMutación, int numCromosomas, int numExecutions, int[] fitness) {
        this.limInf = limInf;
        this.limSup = limSup;
        this.probMutación = probMutación;
        this.numCromosomas = numCromosomas;
        this.numExecutions = numExecutions;
        this.fitness = fitness;

        objT = new Tools();

        lCromoBin = new ArrayList<>();
        lCromoInt = new ArrayList<>();
        lResultados = new ArrayList<>();
        iniLists();

        epoch = 1;
        countX = 1;

        init();
    }

    private void iniLists() {
        lEpoch = new ArrayList<>();
        lLabels = new ArrayList<>();
        lCromoTmp = new ArrayList<>();
        lFitness = new ArrayList<>();
        lRatio = new ArrayList<>();
        lCrossover = new ArrayList<>();
        lMutation = new ArrayList<>();
    }

    public void init() {
        setNumBits(); //asigna el número de bits para cada cromosoma
        cromFamily(); //Genera la población actual de cromosomas
        execution();
    }

    private void execution() {
        Integer[][] tmpCromosomas;

        for (int i = 0; i < numCromosomas; i++) {
            lEpoch.add(String.valueOf(epoch));
            lLabels.add("X" + (i + 1));
        }

        evaluarFitness(fitness[0], fitness[1], fitness[2]);
        mostrar("DESPUES DE FITNES");
        getRatio();
        mostrar("DESPUES DE RATIO");

        //para calcular crossover
        for (int i = 0; i < lCromoBin.size(); i++) {
            tmpCromosomas = rouletteSelection(); //selección de 2 cromosomas
            if (tmpCromosomas == null) {
                return;
            }
            crossover(tmpCromosomas[0], tmpCromosomas[1]);

            if (lCromoTmp.size() == lCromoBin.size()) {
                break;
            }
        }
        mostrar("DESPUES DE CROSS");
        
        objT.addCromoBin(lCromoBin);
        updateCromoInteger();
        
        //pra calcular mutation
        for (int i = 0; i < lCromoBin.size(); i++) {
            Integer[] tmp = lCromoTmp.get(i);
            mutation(tmp);
        }
        mostrar("DESPUES DE MUTA");

        objT.addEpoch(lEpoch);
        objT.addLabel(lLabels);
        objT.addCromoInt(lCromoInt);
        objT.addFitnes(lFitness);
        objT.addRatio(lRatio);
        objT.addCrossover(lCrossover);
        objT.addMutation(lMutation);

        mostrar("DESPUES DE AÑADIR A TOOLS");

        lCromoBin = Tools.pasaList(lCromoTmp);

        mostrar("DESPUES DE INTERCAMBIAR DATOS CON LCROMOTMP");
        iniLists();

        ++epoch;

        if (epoch <= numExecutions) {
            execution();
        } else if (epoch > numExecutions) {
            System.out.println(""); //para debuguear
        }
    }

    private void mostrar(String msg) {
        System.out.println(msg);
        for (int i = 0; i < lCromoBin.size(); i++) {
            System.out.println(Arrays.toString(lCromoBin.get(i)));
        }
    }

    private void updateCromoInteger() {
        lCromoInt = new ArrayList<>();
        int value;
        for (int i = 0; i < lCromoBin.size(); i++) {
            value = Tools.binToDec(getNumber(lCromoBin.get(i)));
            lCromoInt.add(value);
        }
    }

    private void finalResult() {
        int max = lCromoInt.get(0);
        for (int i = 0; i < lCromoInt.size(); i++) {
            if (max < lCromoInt.get(i)) {
                max = lCromoInt.get(i);
            }
        }
        System.out.println("RESULTADO: " + max);
        objT.finalArray();

    }

    private String getNumber(Integer[] tmp) {
        String res = "";
        for (Integer num : tmp) {
            res += num;
        }
        return res;
    }

    /**
     * Calcula el número de bits para cada cromosoma
     */
    private void setNumBits() {
        int numCombinaciones = limSup - limInf + 1;
        numBits = (int) Math.exp(Math.log(numCombinaciones) / 2);
    }

    /**
     * Genera la población actual de cromosomas
     */
    private void cromFamily() {
        List<Integer> tmp = new ArrayList<>();
        Random rnd = new Random();
        int num;
        Integer[] tmpI;
        for (int i = 0; i < numCromosomas; i++) {
            num = limInf + rnd.nextInt(limSup);

            if (!tmp.contains(num) && num < limSup) {
                tmp.add(num);
                tmpI = Tools.convertToBinaryIntegerArray(num, numBits); //dejado así por cuestiones de pruebas y entendimiento
                lCromoBin.add(tmpI); //guarda el cromo binario
                lCromoInt.add(num); //guarda cromo entero, por si se necesita
            } else {
                --i;
            }
        }
    }

    private void evaluarFitness(double A, double B, double C) {
        double res;
        for (int i = 0; i < lCromoInt.size(); i++) {
            res = A * Math.pow(lCromoInt.get(i), 2) + B * lCromoInt.get(i) + C;
            lFitness.add(res);
        }
    }

    private void getRatio() {
        double sum = 0, res;
        for (int i = 0; i < lFitness.size(); i++) {
            sum += lFitness.get(i);
        }
        for (int i = 0; i < lFitness.size(); i++) {
            res = lFitness.get(i) / sum;
            lRatio.add(res);
        }
    }

    private void crossover(Integer[] madre, Integer[] padre) {
        double rand = Tools.getRandom(); //final

        //double rand = 0.85;//pruebas
        double probabilidad = ((double) (1) / (madre.length - 1));
        double[] rango = Tools.generaRango(madre.length - 1, probabilidad);
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

                lCrossover.add(madre);
                lCrossover.add(padre);
                return;
            }
        }
    }

    private void mutation(Integer[] cromosoma) {
        double numRandom;
        for (int i = 0; i < cromosoma.length; i++) {
            numRandom = Tools.getRandom();

            if (numRandom > probMutación) {
                if (cromosoma[i] == 0) {
                    cromosoma[i] = 1; //de 0 cambia a 1
                } else {
                    cromosoma[i] = 0; //de 1 cambia a 0
                }
            }
        }
        lMutation.add(cromosoma);
    }

    private Integer[][] rouletteSelection() {
        double random = Tools.getRandom();
        double[] rangos = generateChart();
        Integer[][] tmpCromosomas = new Integer[2][numBits];
        int countCromo = 0;
        boolean flag = true;

        for (int i = 0; i < rangos.length; i++) {
            if (random <= rangos[i]) {
                tmpCromosomas[countCromo] = lCromoBin.get(i);
                ++countCromo;
            }

            if (countCromo == 2) {
                //ya tiene dos cromosomas
                return tmpCromosomas;
            } else if (countCromo == 1 && flag) {
                random = Tools.getRandom();
                i = -1; // para que vuelva a checar todo
                flag = false;
            }
        }
        return null;
    }

    private double[] generateChart() {
        double[] tmp = new double[lRatio.size()];
        double sum = 0;
        for (int i = 0; i < tmp.length; i++) {
            sum += lRatio.get(i);
            tmp[i] += sum;
        }
        return tmp;
    }

    public static void main(String[] args) {
        //int limInf, int limSup, double probMutación, int numCromosomas, int numExecutions, int[] fitness
        Algoritmo objA = new Algoritmo(0, 100, 0.6, 6, 4, new int[]{2, 3, 0}); //equivale a la formula 2x^2+3x+0
        objA.finalResult();
    }

}
