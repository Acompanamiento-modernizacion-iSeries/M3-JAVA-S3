import java.math.BigDecimal;
import java.util.Scanner;

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registro de Cliente ===");
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Número de cuenta: ");
        Integer numeroDeCuenta = Integer.valueOf(scanner.nextLine());

        System.out.print("Saldo inicial: ");
        BigDecimal saldoInicial = new BigDecimal(scanner.nextLine());

        CuentaBancaria cuenta = new CuentaBancaria(nombre, numeroDeCuenta, saldoInicial);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Menú de Opciones ===");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Realizar Depósito");
            System.out.println("3. Realizar Retiro");
            System.out.println("4. Calcular Interés Simple");
            System.out.println("5. Calcular Interés Compuesto");
            System.out.println("6. Verificar Elegibilidad para Crédito");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: $" + cuenta.consultarSaldo());
                    break;

                case 2:
                    System.out.print("Ingrese el monto a depositar: ");
                    BigDecimal montoDeposito = new BigDecimal(scanner.nextLine());
                    cuenta.depositar(montoDeposito);
                    System.out.println("Depósito realizado exitosamente.");
                    System.out.println("Saldo actual: $" + cuenta.consultarSaldo());
                    break;

                case 3:
                    System.out.print("Ingrese el monto a retirar: ");
                    BigDecimal montoRetiro = new BigDecimal(scanner.nextLine());
                    if (cuenta.retirar(montoRetiro)) {
                        System.out.println("Retiro realizado exitosamente.");
                    } else {
                        System.out.println("Saldo insuficiente para realizar el retiro.");
                    }
                    System.out.println("Saldo actual: $" + cuenta.consultarSaldo());
                    break;

                case 4:
                    System.out.print("Ingrese la tasa de interés anual (en %): ");
                    BigDecimal tasaInteresSimple = new BigDecimal(scanner.nextLine()).divide(BigDecimal.valueOf(100));
                    System.out.print("Ingrese el número de años: ");
                    int añosSimple = Integer.parseInt(scanner.nextLine());
                    BigDecimal interesSimple = cuenta.calcularInteresSimple(tasaInteresSimple, añosSimple);
                    System.out.println("Interés simple calculado: $" + interesSimple);
                    break;

                case 5:
                    System.out.print("Ingrese la tasa de interés anual (en %): ");
                    BigDecimal tasaInteresCompuesto = new BigDecimal(scanner.nextLine()).divide(BigDecimal.valueOf(100));
                    System.out.print("Ingrese el número de años: ");
                    int añosCompuesto = Integer.parseInt(scanner.nextLine());
                    BigDecimal interesCompuesto = cuenta.calcularInteresCompuesto(tasaInteresCompuesto, añosCompuesto);
                    System.out.println("Interés compuesto calculado: $" + interesCompuesto);
                    break;

                case 6:
                    Boolean elegibilidad = cuenta.verificarElegibilidadCredito();
                    if (elegibilidad) {
                        System.out.println("Es elegible para crédito.");
                    } else {
                        System.out.println("No es elegible para crédito.");
                    }
                    break;

                case 7:
                    continuar = false;
                    System.out.println("Ha salido del simulador, ¡gracias!");
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }
        }

        scanner.close();
    }
}