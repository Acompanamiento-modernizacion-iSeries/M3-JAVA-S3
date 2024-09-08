import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimuladorCuentaBancaria {
    private static Scanner scanner = new Scanner(System.in);
    private static String nombre;
    private static Integer numeroCuenta;
    private static BigDecimal saldo;
    private static BigDecimal tasaInteres;
    private static Integer periodos;


    public static void main(String[] args) {
        Boolean cerrar = true;
        leerInformacion();
        while (cerrar) {
            mostrarMenu();
            Integer opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    intereses();
                    calcularInteresSimple();
                    break;
                case 3:
                    intereses();
                    periodos();
                    CalcularInteresCompuesto();
                    break;
                case 4:
                    verificarElegibilidad();
                    break;
                case 5:
                    cerrar = false;
                    System.out.println("Sesion finalizada exitosamente!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nSimulador Sistema Bancario de " + nombre);
        System.out.println("1. Consultar saldo");
        System.out.println("2. Calcular interes");
        System.out.println("3. Calcular interes compuesto");
        System.out.println("4. verificar elegibilidad de crédito");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void leerInformacion(){
        System.out.println("Ingresa tu nombre:");
        nombre = scanner.next();

        System.out.println("Ingresa tu número de cuenta:");
        numeroCuenta = leerEnteros();

        System.out.println("Ingresa el saldo inicial de tu cuenta bancaria:");
        saldo = leerDecimal();;
    }

    private static Integer leerOpcion() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opción no valida.");
                scanner.next();
            }
        }
    }
    private static void consultarSaldo() {
        System.out.println("\nSaldo actual: " + saldo);
    }
    private static void intereses() {
        System.out.print("\nIngrese la tasa de interés (en porcentaje): ");
        tasaInteres = leerDecimal();
    }

    private static void periodos() {
        System.out.print("\nIngrese el periodo: ");
        periodos = leerEnteros();
    }

    private static BigDecimal leerDecimal() {
        while (true) {
            try {
                BigDecimal valor = scanner.nextBigDecimal();
                if (valor.compareTo(BigDecimal.ZERO) == 1) {
                    return valor;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }

    private static Integer leerEnteros() {
        while (true) {
            try {
                Integer periodo = scanner.nextInt();
                if (periodo > 0) {
                    return periodo;
                } else {
                    System.out.println("El valor ingresado no puede ser negativo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.next();
            }
        }
    }

    public static void calcularInteresSimple() {
        BigDecimal interes = saldo.multiply(tasaInteres.divide(BigDecimal.valueOf(100)));
        System.out.println("Su interes simple sobre la cuenta es: " + interes);
    }

    public static void CalcularInteresCompuesto(){
        BigDecimal ValorInteresCompuesto = saldo.multiply(tasaInteres.divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(periodos));
        System.out.println("Su interes compuesto sobre la cuenta es: " + ValorInteresCompuesto);
    }

    public static void verificarElegibilidad(){
        if (saldo.compareTo(BigDecimal.valueOf(5000000)) == 1){
            System.out.println("Es elegible para crédito");
        }else{
            System.out.println("No es elegible para crédito");
        }
    }
}