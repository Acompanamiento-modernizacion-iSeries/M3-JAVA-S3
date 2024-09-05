package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double saldo = 0;
        Operaciones operaciones = new Operaciones();

        while (true) {
            System.out.println("***Menu Bancario***");
            System.out.println("1. Realizar deposito:");
            System.out.println("2. Consultar saldo:");
            System.out.println("3. Calcular interes simple:");
            System.out.println("4. Calcular interes compuesto:");
            System.out.println("5. Consultar elegibilidad:");
            System.out.println("6. Salir del menu:\n");
            int opc = sc.nextInt();

            switch (opc) {
                case 1:
                    System.out.println("Ingrese el valor del deposito: ");
                    saldo += sc.nextDouble();
                    System.out.println("Deposito exitoso");
                    break;

                case 2:
                    System.out.println("Saldo de la cuenta:"+saldo);
                    break;

                case 3:
                    if(saldo > 0){
                        Double intereSimple = operaciones.InteresSimple(saldo,0.05,12);
                        System.out.println("Interes simple: "+intereSimple);
                    }else{
                        System.out.println("Saldo de la cuenta en cero!");
                    }
                    break;
                case 4:
                    BigDecimal interComp;
                    interComp = operaciones.InteresCompuesto(BigDecimal.valueOf(saldo), 0.05, 6);
                    System.out.println("Interes Compuesto: "+interComp);
                    break;
                case 5:

                    System.out.println("Ingrese puntaje de calificación de la persona entre 1/100:");
                    int calificacion = sc.nextInt();
                    if (calificacion<=50){
                        System.out.println("No es elegible para credito:");
                    }else{
                        System.out.println("Es elegible para credito:");
                    }
                    break;
                case 6:
                    //Salir
                    System.out.println("Gracias por usar nuestros servicios");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Por favor seleccione una opción valida");
                    break;
            }

        }

    }
}