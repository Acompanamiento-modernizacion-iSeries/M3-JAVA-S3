import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal saldoFinal = new BigDecimal(0);
        BigDecimal tasaInteres = new BigDecimal(0.15);
        Boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            switch (seleccionarOpcion()) {
                case 1:
                    mostrarMensaje("Ingrese el valor a depositar: ");
                    if (scanner.hasNextBigDecimal()) {
                        saldoFinal = realizarDeposito(saldoFinal, scanner.nextBigDecimal());
                        break;
                    } else {
                        mostrarMensaje("Valor no valido");
                        scanner.next();
                        break;
                    }
                case 2:
                    mostrarMensaje("Ingrese el valor a retirar: ");
                    if (scanner.hasNextBigDecimal()) {
                        BigDecimal retiro = scanner.nextBigDecimal();
                        if (retiro.compareTo(saldoFinal) > 0) {
                            mostrarMensaje("No tienes saldo suficiente para realizar el retiro");
                            break;
                        } else {
                            saldoFinal = realizarRetiro(saldoFinal, retiro);
                            break;
                        }
                    } else {
                        mostrarMensaje("Valor no valido");
                        scanner.next();
                        break;
                    }
                case 3:
                    System.out.println("Tú saldo actual es de: " + saldoFinal);
                    break;
                case 4:
                    calcularIntereses(saldoFinal, tasaInteres);
                    break;
                case 5:
                    calcularInteresescompuesto(saldoFinal, tasaInteres);
                    break;
                case 6:
                    elegibleParaCredito(saldoFinal);
                    break;
                case 7:
                    mostrarMensaje("Gracias por usar nuestro servicio");
                    continuar = false;
                    break;
                case 0:
                    mostrarMensaje("Esta opción no existe en el menú");
                    break;
                default:
                    mostrarMensaje("Opción no valida");
            }
        }

    }

    public static void mostrarMenu(){
        System.out.println("===========================Bienvenido al sistema Bancario BANK===========================");
        System.out.println("=========================================================================================");
        System.out.println("===== Como cliente preferencial cuenta con una tasa de interes 0.15 sobre su saldo ======");
        System.out.println("=========================================================================================");
        System.out.println("=====================Si desea realizar Depósitos indique la opción 1 ====================");
        System.out.println("=====================Si desea realizar Retiros indique la opción 2 ======================");
        System.out.println("=====================Si desea consultar su saldo indique la opción 3 ====================");
        System.out.println("=====================Si desea calcular intereses simple sobre el saldo indique la opción 4 =");
        System.out.println("=====================Si desea calcular intereses compuesto sobre el saldo indique la opción 5 =");
        System.out.println("=====================Si desea saber si es elegible para un credito indique la opción 6 ==");
        System.out.println("=====================Si desea salir del sistema indique la opción 7 =====================");
        System.out.println("=========================================================================================");
    }

    public static int seleccionarOpcion(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la opción que desea usar: ");
        int opcion = 0;
        if (scanner.hasNextBigDecimal()) {
            opcion = scanner.nextBigDecimal().intValue();
        } else {
            scanner.next();
        }
        return opcion;
    }

    public static BigDecimal realizarDeposito(BigDecimal saldoFinal, BigDecimal deposito){
        saldoFinal = saldoFinal.add(deposito);
        System.out.println("Tú saldo luego del depósito es de: " + saldoFinal);
        return saldoFinal;
    }

    public static BigDecimal realizarRetiro(BigDecimal saldoFinal, BigDecimal retiro){
        saldoFinal = saldoFinal.subtract(retiro);
        System.out.println("Tú saldo luego del retiro es de: " + saldoFinal);
        return saldoFinal;
    }

    public static void calcularIntereses(BigDecimal saldoFinal, BigDecimal tasaInteres){
        saldoFinal = saldoFinal.add(saldoFinal.multiply(tasaInteres));
        System.out.println("Tú saldo luego de los intereses es de: " + saldoFinal);
    }

    public static void calcularInteresescompuesto(BigDecimal saldoFinal, BigDecimal tasaInteres){
        int numeroVecesPorYear = 4;  // Número de veces que se aplica el interés en un año (n)
        int tiempoEnYears = 1;      // Tiempo en años t
        BigDecimal base = BigDecimal.ONE.add(tasaInteres.divide(BigDecimal.valueOf(numeroVecesPorYear), 10, RoundingMode.HALF_UP)); // (1 + r/n)
        BigDecimal exponente = BigDecimal.valueOf(numeroVecesPorYear * tiempoEnYears);  // nt
        BigDecimal montoFinal = saldoFinal.multiply(base.pow(exponente.intValue()));   // P * (1 + r/n)^(nt)
        System.out.println("Tú saldo luego de los intereses compuestos para un año es de: " + montoFinal);
    }

    public static void elegibleParaCredito(BigDecimal saldoFinal){
        boolean castigado = false;
        boolean validez1 = saldoFinal.compareTo(BigDecimal.valueOf(0)) > 0 && !castigado;
        if(validez1) {
            mostrarMensaje("Cliente elegible para credito");
        } else {
            mostrarMensaje("Cliente no elegible para credito");
        }
    }

    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}