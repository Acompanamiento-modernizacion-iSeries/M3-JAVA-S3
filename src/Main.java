import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /*
        Objetivo.
        Los asistentes deben crear un programa que simule la gestión de una cuenta bancaria, usando clases wrapper y funciones
        para todos los cálculos, incluyendo el manejo de saldos, intereses y elegibilidad para créditos.

        Instrucciones.
        Crear un menú que permita al usuario realizar las siguientes operaciones: consultar saldo, calcular interés simple,
        calcular interés compuesto y verificar elegibilidad para crédito.
        Utilizar clases wrapper (Integer, Double, Boolean) para almacenar y manipular los datos.
        Modularizar el código en funciones.
        Usar BigDecimal para cualquier cálculo relacionado con dinero, como el cálculo de intereses.
        Implementar validaciones para asegurar que los datos ingresados sean correctos y que las operaciones se manejen
        con precisión.
        Nota: Los talleres deben entregarse por medio de la estrategia establecida para la formación, por medio de un Pull
        Request desde el repositorio Fork hacia la rama main del repositorio del taller.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal saldo = verificarNumeroBigDecimalScanner(scanner, "Ingrese un saldo inicial para su cuenta: ");

        while (true) {
            System.out.println("---".repeat(20));
            mostrarMenu();
            int opcion = verificarNumeroEnteroScanner(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo actual es: " + saldo);
                    break;
                case 2:
                    saldo = realizarDeposito(scanner, saldo);
                    break;
                case 3:
                    saldo = realizarRetiro(scanner, saldo);
                    break;
                case 4:
                    calcularInteresSimple(scanner, saldo);
                    break;
                case 5:
                    calcularInteresCompuesto(scanner, saldo);
                    break;
                case 6:
                    verificarElegibilidadCredito(scanner, saldo);
                    break;
                case 9:
                    System.out.println("Gracias por usar nuestros servicios");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
                    System.out.println("---".repeat(20));
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú de opciones");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Calcular interés simple");
        System.out.println("5. Calcular interés compuesto");
        System.out.println("6. Verificar elegibilidad para crédito");
        System.out.println("9. Salir\n");
    }

    private static int verificarNumeroEnteroScanner(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es válido. Por favor, intenta de nuevo");
                scanner.next();
            }
        }
    }

    private static BigDecimal verificarNumeroBigDecimalScanner(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scanner.nextBigDecimal();
            } catch (InputMismatchException e) {
                System.out.println("El dato ingresado no es válido. Por favor, intenta de nuevo");
                scanner.next();
            }
        }
    }

    private static BigDecimal realizarDeposito(Scanner scanner, BigDecimal saldo) {
        BigDecimal deposito = verificarNumeroBigDecimalScanner(scanner, "Ingrese el monto a depositar: ");
        saldo = saldo.add(deposito);
        System.out.println("Su saldo actual es: " + saldo);
        return saldo;
    }

    private static BigDecimal realizarRetiro(Scanner scanner, BigDecimal saldo) {
        BigDecimal retiro = verificarNumeroBigDecimalScanner(scanner, "Ingrese el monto a retirar: ");
        saldo = saldo.subtract(retiro);
        System.out.println("Su saldo actual es: " + saldo);
        return saldo;
    }

    private static void calcularInteresSimple(Scanner scanner, BigDecimal saldo) {
        BigDecimal interesSimple = verificarNumeroBigDecimalScanner(scanner, "Ingrese la tasa de interés simple, en porcentaje sin el signo % y separado por coma: ");
        int tiempo = verificarNumeroEnteroScanner(scanner, "Ingrese el tiempo en años: ");
        BigDecimal interes = saldo.multiply(interesSimple).multiply(BigDecimal.valueOf(tiempo)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        System.out.println("El interés simple es: " + interes);
        saldo = menuReinvertir(saldo, interes);
    }

    private static void calcularInteresCompuesto(Scanner scanner, BigDecimal saldo) {
        BigDecimal interesCompuesto = verificarNumeroBigDecimalScanner(scanner, "Ingrese la tasa de interés compuesto, en porcentaje sin el signo % y separado por coma: ");
        int tiempo = verificarNumeroEnteroScanner(scanner, "Ingrese el tiempo en años: ");
        BigDecimal interes = saldo.multiply(BigDecimal.valueOf(Math.pow(1 + interesCompuesto.doubleValue() / 100, tiempo))).subtract(saldo);
        System.out.println("El interés compuesto es: " + interes);
        menuReinvertir(saldo, interes);
    }

    private static void verificarElegibilidadCredito(Scanner scanner, BigDecimal saldo) {
        BigDecimal montoCredito = verificarNumeroBigDecimalScanner(scanner, "Ingrese el monto del crédito: ");
        BigDecimal interesCredito = verificarNumeroBigDecimalScanner(scanner, "Ingrese la tasa de interés del crédito, en porcentaje sin el signo %: ");
        int tiempoCredito = verificarNumeroEnteroScanner(scanner, "Ingrese el tiempo del crédito en meses: ");

        // Regla de negocio: la cuota mensual no puede ser mayor al 30% del salario
        BigDecimal intereses = saldo.multiply(interesCredito).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        BigDecimal totalPagar = montoCredito.add(intereses);
        BigDecimal cuotaMensual = totalPagar.divide(BigDecimal.valueOf(tiempoCredito), RoundingMode.HALF_UP);
        if (cuotaMensual.compareTo(saldo.multiply(BigDecimal.valueOf(0.3))) < 0) {
            System.out.println("Eres elegible para un crédito");
        } else {
            System.out.println("No eres elegible para un crédito");
        }
    }

    private static void reinvertir(BigDecimal saldo, BigDecimal interes) {
        saldo = saldo.add(interes);
    }

    public static BigDecimal menuReinvertir(BigDecimal saldo, BigDecimal interes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Reinvertir intereses");
        System.out.println("2. No reinvertir intereses");

        while (true) {
            int opcion = verificarNumeroEnteroScanner(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    reinvertir(saldo, interes);
                    System.out.println("Intereses reinvertidos exitosamente");
                    System.out.println("Su saldo actual es: " + saldo);
                    return saldo;
                case 2:
                    System.out.println("Intereses no reinvertidos");
                    return saldo;
                default:
                    System.out.println("Opción no válida, seleccione una opción válida de reinversión");
                    break;
            }
        }
    }
}