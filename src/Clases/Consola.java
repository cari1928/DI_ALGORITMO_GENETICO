package Clases;

import java.util.Scanner;

/**
 *
 * @author Tenistas
 */
public class Consola {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Algoritmo objA;
        double probMuta;
        int limInf, limSup, numCromosomas;

        System.out.println("Se solicitará el rango para el tamaño de cada cromosomas");
        System.out.print("Ingrese el límite inferior: ");
        limInf = reader.nextInt();

        System.out.print("Ingrese el límite superior: ");
        limSup = reader.nextInt();

        System.out.print("Número de Individuos a utilizar: ");
        numCromosomas = reader.nextInt();

        System.out.print("Probabilidad para mutación: ");
        probMuta = reader.nextDouble();

        //objA = new Algoritmo(limInf, limSup, probMuta, numCromosomas);
    }

}
