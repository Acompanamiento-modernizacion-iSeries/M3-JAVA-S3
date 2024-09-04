import java.util.Scanner;
import java.math.BigDecimal;

public class CuentaBancaria {
    private static BigDecimal saldo = BigDecimal.ZERO;
    private static final BigDecimal TASA_INTERES = new BigDecimal("0.05"); // 5% de interés

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = obtenerOpcionValida(sc);

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito(sc);
                    break;
                case 3:
                    realizarRetiro(sc);
                    break;
                case 4:
                    calcularIntereses();
                    break;
                case 5:
                    calcularIntCompuesto();
                    break;
                case 6:
                    System.out.println("Gracias por usar nuestro servicio");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 6);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("--- Menú Gestión Cuenta Bancaria ---");
        System.out.println("------------------------------------------");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Calcular intereses Simple");
        System.out.println("5. Calcular intereses Compuesto");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static int obtenerOpcionValida(Scanner scanner) {
        int opcion = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String entrada = scanner.nextLine();
            if (esNumero(entrada)) {
                opcion = Integer.parseInt(entrada);
                entradaValida = true;
            } else {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }

        return opcion;
    }

    private static boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void consultarSaldo() {
        System.out.printf("Su saldo actual es: $%.2f%n", saldo);
    }

    private static void realizarDeposito(Scanner scanner) {
        System.out.print("Ingrese la cantidad a depositar: $");
        BigDecimal cantidad = obtenerCantidadValida(scanner);
        saldo = saldo.add(cantidad);
        System.out.printf("Depósito realizado. Nuevo saldo: $%.2f%n", saldo);
    }

    private static void realizarRetiro(Scanner scanner) {
        System.out.print("Ingrese la cantidad a retirar: $");
        BigDecimal cantidad = obtenerCantidadValida(scanner);
        if (cantidad.compareTo(saldo) > 0) {
            System.out.println("Fondos insuficientes. No se puede realizar el retiro.");
        } else {
            saldo = saldo.subtract(cantidad);
            System.out.printf("Retiro realizado. Nuevo saldo: $%.2f%n", saldo);
        }
    }

    private static void calcularIntereses() {
        System.out.print("Ingrese el número de años: ");
        Scanner scanner = new Scanner(System.in);
        int years = scanner.nextInt();

        BigDecimal interes = saldo.multiply(TASA_INTERES).multiply(new BigDecimal(years));
        BigDecimal montoTotal = saldo.add(interes);

        System.out.printf("Interés simple después de %d años: $%.2f%n", years, interes);
        System.out.printf("Monto total después de %d años: $%.2f%n", years, montoTotal);
    }

    private static void calcularIntCompuesto() {
        /*M = C × (1 + i)^n
          Donde:
            M es la suma de capital más intereses al final del período.
            C es el capital inicial.
            i es la tasa de interés compuesto.
            n es el número de períodos durante los cuales se capitaliza el interés compuesto.
         */

        System.out.print("Ingrese el número de años: ");
        Scanner scanner = new Scanner(System.in);
        int years = scanner.nextInt();

        BigDecimal montoTotal = saldo.multiply(BigDecimal.ONE.add(TASA_INTERES).pow(years));
        BigDecimal interes = montoTotal.subtract(saldo);

        System.out.printf("Interés compuesto después de %d años: $%.2f%n", years, interes);
        System.out.printf("Monto total después de %d años: $%.2f%n", years, montoTotal);
    }

    private static BigDecimal obtenerCantidadValida(Scanner scanner) {
        BigDecimal cantidad = BigDecimal.ZERO;
        boolean entradaValida = false;

        while (!entradaValida) {
            String entrada = scanner.nextLine();
            if (esNumero(entrada)) {
                cantidad = new BigDecimal(entrada);
                entradaValida = true;
            } else {
                System.out.print("Por favor, ingrese una cantidad válida: $");
            }
        }
        return cantidad;
    }
}