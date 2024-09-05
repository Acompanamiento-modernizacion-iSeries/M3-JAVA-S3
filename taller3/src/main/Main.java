package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static BigDecimal saldo;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.next();
                System.out.print("Seleccione una opción: ");
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito(scanner);
                    break;
                case 3:
                    realizarRetiro(scanner);
                    break;
                case 4:
                    calcularIntereses(scanner);
                    break;
                case 5:
                    calcularInteresComp(scanner);
                    break;
                case 6:
                    validarElegCredito(scanner);
                    break;
                case 7:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Seleccione una opción del menú.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Calcular intereses");
        System.out.println("5. Salir");
    }

    private static void consultarSaldo() {
        System.out.printf("El saldo actual es: $%.2f%n", saldo);
    }

    private static void realizarDeposito(Scanner scanner) {
        System.out.print("Ingrese la cantidad a depositar: $");
        BigDecimal cantidad = leerMonto(scanner);

        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("La cantidad a depositar debe ser positiva.");
        } else {
            saldo = saldo.add(cantidad);
            System.out.printf("Depósito realizado. Nuevo saldo: $%.2f%n", saldo);
        }
    }

    private static void realizarRetiro(Scanner scanner) {
        System.out.print("Ingrese la cantidad a retirar: $");
        BigDecimal cantidad = leerMonto(scanner);

        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("La cantidad a retirar debe ser positiva.");
        } else if (cantidad.compareTo(saldo) > 0) {
            System.out.println("Fondos insuficientes.");
        } else {
            saldo = saldo.subtract(cantidad);
            System.out.printf("Retiro realizado. Nuevo saldo: $%.2f%n", saldo);
        }
    }

    private static void calcularIntereses(Scanner scanner) {
        System.out.print("Ingrese la tasa de interés anual (en %): ");
        BigDecimal tasaInteres = leerTasaInteres(scanner);

        if (tasaInteres.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("La tasa de interés debe ser positiva.");
        } else {
            BigDecimal intereses = saldo.multiply(tasaInteres.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
            System.out.printf("Los intereses generados sobre el saldo de $%.2f a una tasa de %.2f%% son: $%.2f%n",
                    saldo.setScale(2, RoundingMode.HALF_UP),
                    tasaInteres.setScale(2, RoundingMode.HALF_UP),
                    intereses.setScale(2, RoundingMode.HALF_UP));
        }
    }

    private static void calcularInteresComp(Scanner scanner) {
        System.out.print("Ingrese la tasa de interés (en %): ");
        String tasa = scanner.nextLine();
        System.out.print("Ingrese el tiempo (en años): ");
        String tiempo = scanner.nextLine();
        System.out.print("Ingrese el número de períodos por año: ");
        String numPeriodos = scanner.nextLine();

        BigDecimal interesCompuesto = calcularInteresCompuesto(tasa, tiempo, numPeriodos);
        System.out.println("Interés compuesto: " + interesCompuesto);
    }

    private static void validarElegCredito(Scanner scanner) {
        System.out.print("Ingrese el ingreso anual: ");
        String ingresoAnual = scanner.nextLine();
        System.out.print("Ingrese la deuda total: ");
        String deuda = scanner.nextLine();

        boolean elegible = validarElegibilidadCredito(ingresoAnual, deuda);
        System.out.println(elegible ? "Elegible para crédito." : "No elegible para crédito.");
    }

    public static BigDecimal calcularInteresCompuesto(String tasa, String tiempo, String numPeriodos) {
        BigDecimal tasaDecimal = new BigDecimal(tasa).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal tiempoDecimal = new BigDecimal(tiempo);
        BigDecimal periodosDecimal = new BigDecimal(numPeriodos);

        BigDecimal montoFinal = saldo.multiply(BigDecimal.ONE.add(tasaDecimal.divide(periodosDecimal, MathContext.DECIMAL128)).pow(periodosDecimal.multiply(tiempoDecimal).intValue(), MathContext.DECIMAL128));
        return montoFinal.subtract(saldo).setScale(2, RoundingMode.HALF_UP);
    }

    public static boolean validarElegibilidadCredito(String ingresoAnual, String deuda) {
        BigDecimal ingreso = new BigDecimal(ingresoAnual);
        BigDecimal deudaTotal = new BigDecimal(deuda);
        return ingreso.compareTo(deudaTotal.multiply(BigDecimal.valueOf(0.5))) > 0;
    }

    private static BigDecimal leerMonto(Scanner scanner) {
        BigDecimal monto = BigDecimal.ZERO;
        while (true) {
            try {
                monto = scanner.nextBigDecimal();
                if (monto.compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("El monto debe ser no negativo.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
        return monto;
    }

    private static BigDecimal leerTasaInteres(Scanner scanner) {
        BigDecimal tasaInteres = BigDecimal.ZERO;
        while (true) {
            try {
                tasaInteres = scanner.nextBigDecimal();
                if (tasaInteres.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("La tasa de interés debe ser positiva.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar el buffer
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next(); // Limpiar el buffer
            }
        }
        return tasaInteres;
    }
}

