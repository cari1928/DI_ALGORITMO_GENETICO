package Clases;

import java.util.Random;

public class Algoritmo {

    private int[] generaNoRandom(int noCromo, int rangoI, int rangoF) {
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
    
    private void crossover(){
        
    }
    
    private void mutation() {
        
    }
}
