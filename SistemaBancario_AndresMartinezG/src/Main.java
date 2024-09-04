import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n *** BIENVENIDO ***");

        Scanner scanner = new Scanner(System.in);
        int tipoTransacc;

        Cliente cliente = Menu.setUp(scanner);

        while (true) {
            tipoTransacc = Menu.principal(scanner);

            switch (tipoTransacc) {
                case 1:
                    Transaccion.consulta(cliente);
                    break;
                case 2:
                    Transaccion.depositoRetiro("Deposito", scanner, cliente);
                    break;
                case 3:
                    Transaccion.depositoRetiro("Retiro", scanner, cliente);
                    break;
                case 4:
                    Transaccion.calculoInteres("Simple", scanner, cliente);
                    break;
                case 5:
                    Transaccion.calculoInteres("Compuesto", scanner, cliente);
                    break;
                case 6:
                    Transaccion.eligibilidadPrestamo(cliente.saldoActual.doubleValue());
                    break;
                case 0:
                    System.out.println("Gracias por utilizar nuestro servicio!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tipo de transaccion no reconocida!\n");
            }
        }
    }
}
