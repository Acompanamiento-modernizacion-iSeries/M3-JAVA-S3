import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigDecimal saldoInicial = obtenerSaldoInicial(sc);
        BigDecimal interes = new BigDecimal("0.03");

        while (true) {
            mostrarMenu();
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    consultarSaldo(saldoInicial);
                    break;
                case 2:
                    saldoInicial = calcularInteresSimple(sc, saldoInicial, interes);
                    break;
                case 3:
                    saldoInicial = calcularInteresCompuesto(sc, saldoInicial, interes);
                    break;
                case 4:
                    verificarElegibilidadCredito(saldoInicial);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Seleccionaste una opción incorrecta");
            }
        }
    }

    public static BigDecimal obtenerSaldoInicial(Scanner sc) {
        System.out.println("Ingrese su saldo inicial:");
        while (!sc.hasNextBigDecimal()) {
            System.out.println("No es un saldo válido");
            sc.next();
        }
        return sc.nextBigDecimal();
    }

    public static void mostrarMenu() {
        System.out.println("______________________________________________________________________________");
        System.out.println("Si desea consultar el saldo seleccione 1\n" +
                "Si desea calcular interés simple seleccione 2\n" +
                "Si desea calcular interés compuesto seleccione 3\n" +
                "Si desea verificar elegibilidad para crédito seleccione 4\n" +
                "Si desea salir seleccione 5");
    }

    public static void consultarSaldo(BigDecimal saldo) {
        System.out.println("El saldo actual es de: $" + saldo);
    }

    public static BigDecimal calcularInteresSimple(Scanner sc, BigDecimal saldo, BigDecimal interes) {
        System.out.println("Ingrese el número de meses para calcular los intereses simples:");
        int meses = sc.nextInt();
        if (meses < 0) {
            System.out.println("Entrada de datos incorrecta");
        } else {
            BigDecimal interesMensual = interes.divide(new BigDecimal("12"));
            BigDecimal interesGenerado = saldo.multiply(interesMensual).multiply(new BigDecimal(meses));
            saldo = saldo.add(interesGenerado);
            System.out.println("El cálculo de interés simple es de: $" + interesGenerado + ". Su saldo total es de: $" + saldo);
        }
        return saldo;
    }

    public static BigDecimal calcularInteresCompuesto(Scanner sc, BigDecimal saldo, BigDecimal interes) {
        System.out.println("Ingrese el número de meses para calcular los intereses compuestos:");
        int meses = sc.nextInt();
        if (meses < 0) {
            System.out.println("Entrada de datos incorrecta");
        } else {
            BigDecimal interesMensual = interes.divide(new BigDecimal("12"));
            BigDecimal saldoFinal = saldo.multiply(
                    (BigDecimal.ONE.add(interesMensual)).pow(meses)
            );
            BigDecimal interesGenerado = saldoFinal.subtract(saldo);
            saldo = saldoFinal;
            System.out.println("El cálculo de interés compuesto es de: $" + interesGenerado +
                    ". Su saldo total es de: $" + saldo);
        }
        return saldo;
    }

    public static void verificarElegibilidadCredito(BigDecimal saldo) {
        BigDecimal limiteCredito = new BigDecimal("1000");
        Boolean esElegible = saldo.compareTo(limiteCredito) >= 0;
        if (esElegible) {
            System.out.println("Apto para un crédito");
        } else {
            System.out.println("No apto para un crédito.");
        }
    }
}

