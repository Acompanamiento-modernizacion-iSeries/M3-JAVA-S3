import java.math.BigDecimal;
import java.util.Scanner;

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el valor de la cuenta
        System.out.print("Ingrese el saldo inicial de la cuenta: ");
        BigDecimal saldo = scanner.nextBigDecimal();

        // Solicitar la tasa de interés
        System.out.print("Ingrese la tasa de interés (en porcentaje): ");
        Double tasaInteres = scanner.nextDouble();

        // Solicitar el plazo
        System.out.print("Ingrese el plazo (en años): ");
        Integer plazo = scanner.nextInt();

        // Solicitar la edad
        System.out.print("Ingrese su edad: ");
        Integer edad = scanner.nextInt();

        // Crear la instancia de CuentaBancaria con los valores ingresados
        CuentaBancaria cuenta = new CuentaBancaria(saldo, tasaInteres, plazo);

        while (true) {
            System.out.println("\n------- Menu -------");
            System.out.println("Seleccione una operación:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Calcular interés simple");
            System.out.println("3. Calcular interés compuesto");
            System.out.println("4. Verificar elegibilidad para crédito");
            System.out.println("5. Salir\n");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: " + cuenta.getSaldo());
                    break;
                case 2:
                    BigDecimal interesSimple = OperacionesBancarias.calcularInteresSimple(cuenta.getSaldo(),
                            cuenta.getTasaInteres(), cuenta.getPlazo());
                    System.out.println("Interés simple: " + interesSimple);
                    break;
                case 3:
                    BigDecimal interesCompuesto = OperacionesBancarias.calcularInteresCompuesto(cuenta.getSaldo(),
                            cuenta.getTasaInteres(), cuenta.getPlazo());
                    System.out.println("Interés compuesto: " + interesCompuesto);
                    break;
                case 4:
                    Boolean elegible = OperacionesBancarias.verificarElegibilidadCredito(cuenta.getSaldo(), edad);
                    System.out.println("Elegibilidad para crédito: " + (elegible ? "Sí" : "No"));
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
