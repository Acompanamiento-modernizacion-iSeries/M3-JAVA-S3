import java.math.BigDecimal;
import java.util.Scanner;

public class AdministracionCuentas {
    public static void main(String[] args) {
        int i = 0;
        int opc;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa tu nombre:");
        String nombre = sc.nextLine();

        System.out.println("Saldo inicial: \n");
        BigDecimal saldo = new BigDecimal(sc.nextLine());
        Cuenta cta = new Cuenta(saldo);

        while (i < 5) {
            System.out.println("Opciones: \n");
            System.out.println("1.   Consultar Saldo: ");
            System.out.println("2.   Calcular interes Simples: ");
            System.out.println("3.   Calcular interes Compuestos: ");
            System.out.println("4.   Verificar Disponibilidad para un Credito: ");
            System.out.println("5.   Salir del Sistema: ");
            System.out.println(" ");
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Saldo: \n");
                    System.out.println(cta.consultarSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese tasa de Interes: \n");
                    BigDecimal tasaInteres = new BigDecimal(sc.nextDouble());

                    System.out.println("Ingrese plazo del credito: \n");
                    int tiempo = sc.nextInt();
                    cta.calculoInteresSimple(tasaInteres, tiempo);
                    System.out.println("Capital Final: \n");
                    System.out.println(cta.calculoInteresSimple(tasaInteres, tiempo));
                    break;
                case 3:
                    System.out.println("Calcular interes Compuestos: \n");
                    System.out.println("Ingrese tasa de Interes: \n");
                    BigDecimal tasaInteresC = new BigDecimal(sc.nextDouble());
                    System.out.println("Ingrese plazo del credito: \n");
                    int tiempoC = sc.nextInt();
                    cta.calculoInteresCompuesto(tasaInteresC, tiempoC);
                    System.out.println("Capital Final: \n");
                    System.out.println(cta.calculoInteresCompuesto(tasaInteresC, tiempoC));
                    break;
                case 4:
                    cta.validoCredito();
                    break;
                case 5:
                    i = 6;
                    System.out.println("Gracias por usar nuestros servicios Sr "+nombre);
                    break;
                default:
                    System.out.println("Transaccion no valida: \n");
                    break;
            }


        }
    }
}