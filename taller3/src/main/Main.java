package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static BigDecimal saldo= BigDecimal.ZERO;

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
        } while (opcion != 7);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Calcular intereses");
        System.out.println("5. Calcular interés compuesto");
        System.out.println("6. Verificar elegibilidad para crédito");
        System.out.println("7. Salir");
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
            System.out.printf("Los intereses generados sobre el saldo de $%s a una tasa de %s%% son: $%s%n",
                    saldo.setScale(2, RoundingMode.HALF_UP).toString(),
                    tasaInteres.setScale(2, RoundingMode.HALF_UP).toString(),
                    intereses.setScale(2, RoundingMode.HALF_UP).toString());
        }
    }

    private static void calcularInteresComp(Scanner scanner) {
        System.out.print("Ingrese la tasa de interés anual (en %): ");
        BigDecimal tasaInteres = leerTasaInteres(scanner);

        System.out.print("Ingrese el tiempo (en años): ");
        BigDecimal tiempo = leerMonto(scanner);

        System.out.print("Ingrese el número de períodos por año: ");
        BigDecimal numPeriodos = leerMonto(scanner);

        try {
            BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP);
            BigDecimal unoMasRentreN = BigDecimal.ONE.add(tasaInteresDecimal.divide(numPeriodos, 8, RoundingMode.HALF_UP));
            BigDecimal exponent = numPeriodos.multiply(tiempo);
            BigDecimal monto = saldo.multiply(unoMasRentreN.pow(exponent.intValueExact(), new java.math.MathContext(8, RoundingMode.HALF_UP)));
            System.out.printf("El interés compuesto es: $%.2f%n", monto.subtract(saldo).setScale(2, RoundingMode.HALF_UP));
        } catch (ArithmeticException e) {
            System.out.println("Error en el cálculo del interés compuesto: " + e.getMessage());
        }
    }

    private static void validarElegCredito(Scanner scanner) {
        System.out.print("Ingrese el ingreso anual: $");
        BigDecimal ingresoAnual = leerMonto(scanner);

        System.out.print("Ingrese la deuda total: $");
        BigDecimal deudaTotal = leerMonto(scanner);

        boolean elegible = validarElegibilidadCredito(ingresoAnual, deudaTotal);
        System.out.println(elegible ? "Elegible para crédito." : "No elegible para crédito.");
    }

    private static boolean validarElegibilidadCredito(BigDecimal ingresoAnual, BigDecimal deudaTotal) {
        try {
            BigDecimal limite = ingresoAnual.multiply(BigDecimal.valueOf(0.5));
            return ingresoAnual.compareTo(deudaTotal) > 0 && deudaTotal.compareTo(limite) <= 0;
        } catch (ArithmeticException e) {
            System.out.println("Error en la validación de elegibilidad para crédito: " + e.getMessage());
            return false;
        }
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

