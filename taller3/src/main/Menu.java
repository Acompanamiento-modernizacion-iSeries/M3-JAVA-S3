package main;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    private Cuenta cuenta;

    public Menu(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1. Consultar saldo");
            System.out.println("2. Calcular interés simple");
            System.out.println("3. Calcular interés compuesto");
            System.out.println("4. Verificar elegibilidad para crédito");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    calcularInteresSimple(scanner);
                    break;
                case 3:
                    calcularInteresCompuesto(scanner);
                    break;
                case 4:
                    validarElegibilidadCredito(scanner);
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void consultarSaldo() {
        System.out.println("Saldo actual: " + cuenta.getSaldo());
    }

    private void calcularInteresSimple(Scanner scanner) {
        System.out.print("Ingrese la tasa de interés (en %): ");
        String tasa = scanner.nextLine();
        System.out.print("Ingrese el tiempo (en años): ");
        String tiempo = scanner.nextLine();

        BigDecimal interesSimple = cuenta.calcularInteresSimple(tasa, tiempo);
        System.out.println("Interés simple: " + interesSimple);
    }

    private void calcularInteresCompuesto(Scanner scanner) {
        System.out.print("Ingrese la tasa de interés (en %): ");
        String tasa = scanner.nextLine();
        System.out.print("Ingrese el tiempo (en años): ");
        String tiempo = scanner.nextLine();
        System.out.print("Ingrese el número de períodos por año: ");
        String numPeriodos = scanner.nextLine();

        BigDecimal interesCompuesto = cuenta.calcularInteresCompuesto(tasa, tiempo, numPeriodos);
        System.out.println("Interés compuesto: " + interesCompuesto);
    }

    private void validarElegibilidadCredito(Scanner scanner) {
        System.out.print("Ingrese el ingreso anual: ");
        String ingresoAnual = scanner.nextLine();
        System.out.print("Ingrese la deuda total: ");
        String deuda = scanner.nextLine();

        boolean elegible = cuenta.validarElegibilidadCredito(ingresoAnual, deuda);
        System.out.println(elegible ? "Elegible para crédito." : "No elegible para crédito.");
    }
}

