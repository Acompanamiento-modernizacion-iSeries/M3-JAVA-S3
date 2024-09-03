import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Transaccion {
    public static void consulta(Cliente cliente) {
        System.out.println("Su saldo actual es: " + cliente.saldoActual + "\n");
    }

    public static void depositoRetiro(String accion, Scanner scanner, Cliente cliente) {
        double vlrTransacc;
        boolean esNumero = true;
        boolean transaccOk = false;
        String msgNoOk = "Lo sentimos, debe ingresar un valor igual o mayor a 0";

        System.out.println("Ingrese el Valor del " + accion + " :");
        if (scanner.hasNextDouble()) vlrTransacc = scanner.nextDouble();
        else {
            vlrTransacc = accion.equals("Deposito") ? -1.0 : cliente.saldoActual.doubleValue() + 1;
            esNumero = false;
        }

        switch (accion) {
            case "Deposito" :
                if (vlrTransacc >= 0) {
                    cliente.saldoActual = cliente.saldoActual.add(BigDecimal.valueOf(vlrTransacc));
                    transaccOk = true;
                }
                break;
            case "Retiro" :
                if (vlrTransacc >= 0 && vlrTransacc <= cliente.saldoActual.doubleValue()) {
                    cliente.saldoActual = cliente.saldoActual.subtract(BigDecimal.valueOf(vlrTransacc));
                    transaccOk = true;
                }
                break;
        }

        if (transaccOk) System.out.println(accion + " por " + vlrTransacc + " realizado!\n");
        else {
            msgNoOk = accion.equals("Deposito") ? (msgNoOk + "!") : (msgNoOk + " y menor o igual al saldo disponible!");
            System.out.println(msgNoOk);
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }
    }

    public static void calculoInteres(String tipoInteres, Scanner scanner, Cliente cliente) {
        double tasaInteres = 12.0;
        boolean esNumero = true;
        int tEnMeses;
        BigDecimal intereses = BigDecimal.ZERO;

        System.out.println("Ingrese el tiempo en meses de permanencia del saldo actual:");
        if (scanner.hasNextInt()) tEnMeses = scanner.nextInt();
        else {
            tEnMeses = -1;
            esNumero = false;
        }

        if (tEnMeses > 0) {
            switch (tipoInteres) {
                case "Simple" :
                    intereses = cliente.saldoActual.multiply(BigDecimal.valueOf(tasaInteres/1200 * tEnMeses), MathContext.DECIMAL32);
                    break;
                case "Compuesto" :
                    intereses = cliente.saldoActual.multiply(BigDecimal.valueOf(Math.pow((1+tasaInteres/1200),tEnMeses) - 1), MathContext.DECIMAL32);
                    break;
            }
            System.out.println("Con su saldo actual, " +
                    "y a una Tasa de Interes del " + tasaInteres + "%, " +
                    "en un año usted obtendrá un interes " + tipoInteres + " de\n" +
                    intereses);
        } else {
            System.out.println("Lo sentimos, debe ingresar un tiempo en meses mayor a 0");
            if (!esNumero) scanner.next(); // Limpiar el buffer del scanner
        }
    }

    public static void eligibilidadPrestamo(double saldoCliente) {
        double saldoMinPrestamo = 50000.00;

        if (saldoCliente >= saldoMinPrestamo)
            System.out.println("Excelente, usted es elegible para solicitar un prestamo.\n");
        else
            System.out.println("Lo sentimos, debido a su saldo actual, " +
                    "usted no es elegible para solicitar un prestamo.\n");
    }
}
