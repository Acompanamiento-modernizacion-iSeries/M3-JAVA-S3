import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class SistemaBancarioManejoCuenta {

    public static void main(String[] args) {

        menuOpciones();

    }

    public static void menuOpciones() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // menu de opciones
            System.out.println("\nMenu de opciones de tu cuenta bancaria");
            System.out.println("1.Consultar saldo");
            System.out.println("2.Calcular interes simple");
            System.out.println("3.Calcular interes compuesto");
            System.out.println("4.Elegibilidad para crédito");
            System.out.println("5.Salir");

            int opcion = sc.nextInt();
            ejecutarOpcion(opcion);

        }
    }

    public static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                consultaSaldo();
                break;
            case 2:
                calcularInteresSimple();
                break;
            case 3:
                calcularInteresCompuesto();
                break;
            case 4:
                Boolean esElegible = true;
                verificarElegibilidad(esElegible);
                break;
            case 5:
                System.out.println("Adios");
                System.exit(0);
                break;
            default:
                System.out.println("Opcion digitada no válida, intente otra vez..");
        };
    }

    public static void consultaSaldo() {
        BigDecimal saldo = leerCapital("Ingrese su saldo:");
        System.out.println("El saldo total de su cuenta es: " + saldo + " pesos");

    }

    public static void calcularInteresSimple() {
        BigDecimal capital = leerCapital("Ingrese el capital:");
        BigDecimal tasaInteres = leerTasaInteres("Ingrese la tasa de interes anual:");
        int tiempo = leerInt("Ingrese el tiempo en años:");
        BigDecimal interes = capital.multiply(tasaInteres).multiply(BigDecimal.valueOf(tiempo));

        System.out.println("El interes simple es: " + interes);
    }

    public static BigDecimal leerCapital(String mensaje) {
        Scanner sca = new Scanner(System.in);
        System.out.print(mensaje + " ");
        return sca.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal leerTasaInteres(String mensaje) {
        Scanner sca = new Scanner(System.in);
        System.out.print(mensaje + " ");
        return sca.nextBigDecimal().setScale(2, RoundingMode.HALF_UP);
    }

    public static int leerInt(String mensaje) {
        Scanner sca = new Scanner(System.in);
        System.out.print(mensaje + " ");
        return sca.nextInt();

    }

    public static void calcularInteresCompuesto() {
        BigDecimal capital = leerCapital("Ingrese el capital:");
        BigDecimal tasaInteres = leerTasaInteres("Ingrese la tasa de interes anual:");
        int tiempo = leerInt("Ingrese el tiempo en años:");
        int numPeriodos = leerInt("Ingrese el número de períodos por año:");

        BigDecimal tasaPeriodica = tasaInteres.divide(BigDecimal.valueOf(numPeriodos), MathContext.DECIMAL128).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal exponent = BigDecimal.valueOf(numPeriodos).multiply(BigDecimal.valueOf(tiempo));
        BigDecimal montoFinal = capital.multiply(BigDecimal.ONE.add(tasaPeriodica).pow(exponent.intValueExact(), MathContext.DECIMAL128));
        BigDecimal interes = montoFinal.subtract(capital);

        System.out.println("El interes compuesto es: " + interes.toString());
    }

    public static void verificarElegibilidad(boolean esElegible) {
        //Boolean esElegible = true;
        if (esElegible) {
            System.out.println("Se le puede otorgar un credito");
        } else {
            System.out.println("No se le puede otorgar un credito");
        }
    }
}






