import java.math.BigDecimal;
import java.util.Scanner;

public class AdministracionCuentaFunciones {
    public static void main(String[] args) {
        int i = 0;
        int opc;
        Scanner sc = new Scanner(System.in);
        System.out.println("Saldo inicial: \n");
        BigDecimal saldo = new BigDecimal(sc.nextLine());
        CuentaBancaria cuenta = new CuentaBancaria(saldo);


        while (i < 9) {
            System.out.println("Opciones: \n");
            System.out.println("1        Consultar Saldo: ");
            System.out.println("2        Calcular interes Simples: ");
            System.out.println("3        Calcular interes Compuestos: ");
            System.out.println("4        Verificar Elegibilidad de Credito: ");
            System.out.println("9        Salir del Sistema: ");
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    //consultar saldo
                    System.out.println("Saldo: \n");
                    System.out.println(cuenta.consultarSaldo());
                    break;
                case 2:
                    System.out.println("Ingrese tasa de Interes: \n");
                    BigDecimal tasaInteres = new BigDecimal(sc.nextDouble());

                    System.out.println("Ingrese Tiempo: \n");
                    int tiempo = sc.nextInt();
                    cuenta.calculoInteresesSimple(tasaInteres, tiempo);
                    System.out.println("Capital Final: \n");
                    System.out.println(cuenta.calculoInteresesSimple(tasaInteres, tiempo));
                    break;
                case 3:
                    System.out.println("Calcular interes Compuestos: \n");
                    System.out.println("Ingrese tasa de Interes: \n");
                    BigDecimal tasaInteresC = new BigDecimal(sc.nextDouble());
                    System.out.println("Ingrese Tiempo: \n");
                    int tiempoC = sc.nextInt();
                    cuenta.calculoInteresesCompuesto(tasaInteresC, tiempoC);
                    System.out.println("Capital Final: \n");
                    System.out.println(cuenta.calculoInteresesCompuesto(tasaInteresC, tiempoC));
                    break;
                case 4:
                    cuenta.validoCredito();
                    break;
                case 9:
                    i = 10;
                    break;
                default:
                    System.out.println("Opción no valida: \n");
                    break;
            }


        }
    }
}