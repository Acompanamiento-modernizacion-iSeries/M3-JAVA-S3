import java.util.Scanner;

public class Menu {
    public static Cliente setUp(Scanner scanner) {
        double saldoActual;
        boolean valorValido = false;
        boolean esNumero = true;

        System.out.println("Ingrese su Nombre:");
        String nombre = scanner.next();

        System.out.println("Ingrese su Numero de Cuenta:");
        String numCuenta = scanner.next();

        do {
            System.out.println("Ingrese el Saldo Inicial:");
            if (scanner.hasNextDouble()) saldoActual = scanner.nextDouble();
            else {
                saldoActual = -1.0;
                esNumero = false;
            }

            if (saldoActual >= 0) {
                valorValido = true;
            } else {
                System.out.println("Lo sentimos, debe ingresar un valor igual o mayor a 0!");
                if (!esNumero) scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
                esNumero = true;
            }
        } while (!valorValido);

        return new Cliente(nombre, numCuenta, saldoActual);
    }

    public static int principal(Scanner scanner) {
        while (true) {
            System.out.println("Ingrese el numero de la Transaccion a realizar:\n" +
                    " 1. Consultar saldo.\n" +
                    " 2. Realizar deposito.\n" +
                    " 3. Realizar retiro.\n" +
                    " 4. Calcular el interes simple sobre el saldo.\n" +
                    " 5. Calcular el interes compuesto sobre el saldo.\n" +
                    " 6. Consulte si es elegible para un prestamo.\n" +
                    " 0. TERMINAR");
            if (scanner.hasNextInt()) return scanner.nextInt();
            else {
                System.out.println("Tipo de transaccion no reconocida!");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
            }
        }
    }
}
