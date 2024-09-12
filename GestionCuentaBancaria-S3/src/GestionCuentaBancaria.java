import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class CuentaBancaria {
    private String nombre;
    private Integer numeroCuenta;
    private BigDecimal saldo;

    public CuentaBancaria(String nombre, Integer numeroCuenta, BigDecimal saldoInicial) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }


    public void depositar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(monto);
            System.out.printf("Depósito realizado. Nuevo saldo: $%.2f\n", saldo);
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public void retirar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            if (monto.compareTo(saldo) <= 0) {
                saldo = saldo.subtract(monto);
                System.out.printf("Retiro realizado. Nuevo saldo: $%.2f\n", saldo);
            } else {
                System.out.println("Fondos insuficientes.");
            }
        } else {
            System.out.println("El monto debe ser positivo.");
        }
    }

    public void calcularInteresSimple(BigDecimal tasaInteres) {
        if (tasaInteres.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal interes = saldo.multiply(tasaInteres).divide(new BigDecimal("100"), RoundingMode.HALF_UP);
            System.out.printf("Intereses simples generados: $%.2f\n", interes);
        } else {
            System.out.println("La tasa de interés debe ser positiva.");
        }
    }

    public void calcularInteresCompuesto(BigDecimal tasaInteres, Integer anos) {
        if (tasaInteres.compareTo(BigDecimal.ZERO) > 0 && anos > 0) {
            BigDecimal unoMasTasa = BigDecimal.ONE.add(tasaInteres.divide(new BigDecimal("100"), RoundingMode.HALF_UP));
            BigDecimal montoFinal = saldo.multiply(unoMasTasa.pow(anos));
            System.out.printf("Monto final con interés compuesto: $%.2f\n", montoFinal);
        } else {
            System.out.println("La tasa de interés y el número de años deben ser positivos.");
        }
    }

    public void verificarElegibilidadCredito(BigDecimal montoCredito, BigDecimal ingresoMensual) {
        if (montoCredito.compareTo(BigDecimal.ZERO) > 0 && ingresoMensual.compareTo(BigDecimal.ZERO) > 0) {
            if (montoCredito.compareTo(ingresoMensual.multiply(new BigDecimal("0.5"))) <= 0) {
                System.out.println("Elegible para crédito.");
            } else {
                System.out.println("No elegible para crédito.");
            }
        } else {
            System.out.println("El monto del crédito y el ingreso mensual deben ser positivos.");
        }
    }

    public void mostrarSaldo() {
        System.out.printf("Saldo actual: $%.2f\n", saldo);
    }
}

public class GestionCuentaBancaria {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Captura de datos iniciales
        System.out.println("Ingrese su Nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su Número de Cuenta:");
        Integer numeroCuenta = obtenerEntero();

        System.out.println("Ingrese su Saldo Inicial:");
        BigDecimal saldoInicial = obtenerBigDecimal();

        // Crear una cuenta con los datos proporcionados
        CuentaBancaria cuenta = new CuentaBancaria(nombre, numeroCuenta, saldoInicial);

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            Integer opcion = obtenerEntero();
            switch (opcion) {
                case 1:
                    cuenta.mostrarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese el monto a depositar: ");
                    BigDecimal montoDepositar = obtenerBigDecimal();
                    cuenta.depositar(montoDepositar);
                    break;
                case 3:
                    System.out.print("Ingrese el monto a retirar: ");
                    BigDecimal montoRetirar = obtenerBigDecimal();
                    cuenta.retirar(montoRetirar);
                    break;
                case 4:
                    System.out.print("Ingrese la tasa de interés anual (en porcentaje): ");
                    BigDecimal tasaInteres = obtenerBigDecimal();
                    System.out.print("Ingrese el número de años: ");
                    Integer anos = obtenerEntero();
                    System.out.println("Seleccione tipo de interés:");
                    System.out.println("1. Interés simple");
                    System.out.println("2. Interés compuesto");
                    Integer tipoInteres = obtenerEntero();
                    if (tipoInteres.equals(1)) {
                        cuenta.calcularInteresSimple(tasaInteres);
                    } else if (tipoInteres.equals(2)) {
                        cuenta.calcularInteresCompuesto(tasaInteres, anos);
                    } else {
                        System.out.println("Tipo de interés no válido.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese el monto del crédito solicitado: ");
                    BigDecimal montoCredito = obtenerBigDecimal();
                    System.out.print("Ingrese el ingreso mensual: ");
                    BigDecimal ingresoMensual = obtenerBigDecimal();
                    cuenta.verificarElegibilidadCredito(montoCredito, ingresoMensual);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\nGestión de Cuenta Bancaria");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Realizar depósito");
        System.out.println("3. Realizar retiro");
        System.out.println("4. Calcular intereses");
        System.out.println("5. Verificar elegibilidad para crédito");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static Integer obtenerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static BigDecimal obtenerBigDecimal() {
        while (!scanner.hasNextBigDecimal()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número decimal.");
            scanner.next();
        }
        return scanner.nextBigDecimal();
    }
}
