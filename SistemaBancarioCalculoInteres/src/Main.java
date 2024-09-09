import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static BigDecimal saldo = new BigDecimal("1000.00");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 5) {
            mostrarMenu();
            opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    calcularInteresSimple();
                    break;
                case 3:
                    calcularInteresCompuesto();
                    break;
                case 4:
                    verificarElegibilidadCredito();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú del Sistema Bancario ---");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Calcular Interés Simple");
        System.out.println("3. Calcular Interés Compuesto");
        System.out.println("4. Verificar Elegibilidad para Crédito");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcion() {
        Integer opcion = Integer.valueOf(scanner.nextLine());
        return opcion;
    }

    private static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    private static void calcularInteresSimple() {
        System.out.print("Ingrese la tasa de interés (en porcentaje): ");
        Double tasaInteres = Double.valueOf(scanner.nextLine());
        System.out.print("Ingrese el número de años: ");
        Integer anios = Integer.valueOf(scanner.nextLine());

        if (tasaInteres <= 0 || anios <= 0) {
            System.out.println("La tasa de interés y los años deben ser mayores que cero.");
        } else {
            BigDecimal interesSimple = saldo.multiply(BigDecimal.valueOf(tasaInteres / 100)).multiply(BigDecimal.valueOf(anios));
            System.out.println("El interés simple es: $" + interesSimple);
        }
    }

    private static void calcularInteresCompuesto() {
        System.out.print("Ingrese la tasa de interés (en porcentaje): ");
        Double tasaInteres = Double.valueOf(scanner.nextLine());
        System.out.print("Ingrese el número de años: ");
        Integer anios = Integer.valueOf(scanner.nextLine());

        if (tasaInteres <= 0 || anios <= 0) {
            System.out.println("La tasa de interés y los años deben ser mayores que cero.");
        } else {
            BigDecimal interesCompuesto = saldo.multiply(BigDecimal.valueOf(Math.pow(1 + (tasaInteres / 100), anios)));
            System.out.println("El interés compuesto es: $" + interesCompuesto);
        }
    }

    private static void verificarElegibilidadCredito() {
        System.out.print("Ingrese su ingreso anual: ");
        BigDecimal ingresoAnual = new BigDecimal(scanner.nextLine());
        System.out.print("Ingrese el monto del crédito deseado: ");
        BigDecimal creditoDeseado = new BigDecimal(scanner.nextLine());

        if (ingresoAnual.compareTo(BigDecimal.ZERO) <= 0 || creditoDeseado.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Los valores ingresados deben ser mayores que cero.");
        } else {
            Boolean elegible = verificarElegibilidad(ingresoAnual, creditoDeseado);
            if (elegible) {
                System.out.println("Usted es elegible para el crédito.");
            } else {
                System.out.println("No cumple con los requisitos para el crédito.");
            }
        }
    }

    private static Boolean verificarElegibilidad(BigDecimal ingresoAnual, BigDecimal creditoDeseado) {
        BigDecimal umbralIngreso = new BigDecimal("30000");
        BigDecimal umbralCredito = ingresoAnual.multiply(new BigDecimal("0.5"));
        return ingresoAnual.compareTo(umbralIngreso) >= 0 && creditoDeseado.compareTo(umbralCredito) <= 0;
    }
}

