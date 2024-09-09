import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuenta = new CuentaBancaria(new BigDecimal(10000), new BigDecimal(0.01));

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Calcular interés simple");
            System.out.println("3. Calcular interés compuesto");
            System.out.println("4. Verificar elegibilidad para crédito");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo es: " + cuenta.getSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese el número de meses:");
                    int mesesSimple = scanner.nextInt();
                    System.out.println("El interés simple es: " + cuenta.calcularInteresSimple(mesesSimple));
                    break;
                case 3:
                    System.out.println("Ingrese el número de meses:");
                    int mesesCompuesto = scanner.nextInt();
                    System.out.println("El interés compuesto es: " + cuenta.calcularInteresCompuesto(mesesCompuesto));
                    break;
                case 4:
                    if (cuenta.verificarElegibilidadCredito()) {
                        System.out.println("Usted es elegible para crédito.");
                    } else {
                        System.out.println("Usted no es elegible para crédito.");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
