import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static double saldoCuenta = 0.0;
    private static final double tasaInteresSimple = 0.03; // 5% de interes simple
    private static final BigDecimal tasaInteresCompuesto = new BigDecimal("0.04"); // 4% anual
    private static final double limiteCredito = 1000.0; // Crédito disponible

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el saldo inicial de la cuenta: ");
        saldoCuenta = scanner.nextDouble();

        int opcion;

        do {
            System.out.println("\n =======Bienvenido al Menú de su cuenta bancaria:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Calcular interés simple");
            System.out.println("3. Calcular interés compuesto (1 año)");
            System.out.println("4. Verificar elegibilidad para crédito");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch(opcion) {
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
                    System.out.println("Gracias por usar el programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }


    private static void consultarSaldo() {
       System.out.println("Su saldo actual es: $" + saldoCuenta );
    }

    private static void calcularInteresSimple() {
        double interes = saldoCuenta * tasaInteresSimple;
        System.out.println("El interés simple anual sobre su saldo es: $" + interes);
    }

    private static void calcularInteresCompuesto() {
        BigDecimal saldoBD = BigDecimal.valueOf(saldoCuenta);
        BigDecimal interes = saldoBD.multiply(tasaInteresCompuesto.add(BigDecimal.ONE)).subtract(saldoBD);
        System.out.println("El interés compuesto anual sobre su saldo es: $" + interes);
    }

    private static void verificarElegibilidadCredito() {
        if (saldoCuenta >= limiteCredito) {
            System.out.println("Felicidades, es elegible para un crédito.");
        } else {
            System.out.println("Lo sentimos, no es elegible para un crédito.");
        }
    }
}