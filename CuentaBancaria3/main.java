import java.math.BigDecimal;
import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        BigDecimal saldo = BigDecimal.valueOf(0);
        BigDecimal tasa = new BigDecimal(0.05);
        BigDecimal saldoMinimo = new BigDecimal(100000);
        while (true) {

            switch (showMenu()) {
                case 1:
                    //consultar saldo
                    System.out.println("Saldo de la cuenta: " + saldo);
                    break;
                case 2:
                    //realizar depósitos
                    saldo = realizarDeposito(saldo);
                    break;
                case 3:
                    //realizar retiros
                    saldo = realizarRetiro(saldo);
                    break;
                case 4:
                    //calcular intereses sobre el saldo
                    saldo = liquidarInteres(saldo, tasa);
                    break;
                case 5:
                    //calcular interes compuesto
                    saldo = liquidarInteresCompuesto(saldo, tasa);
                    break;
                case 6:
                    //Estudio de crédito
                    estudioCredito(saldo, saldoMinimo);
                    break;
                case 7:
                    //Salir
                    System.out.println("Gracias por usar nuestros servicios");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Por favor seleccione una opción valida");
                    break;

            }
        }
    }

    public static int showMenu() {
        System.out.println("=================================");
        System.out.println("***Cuenta Bancaria***");
        System.out.println("=================================");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Hacer deposito");
        System.out.println("3. Hacer retiro");
        System.out.println("4. Calcular intereses");
        System.out.println("5. Calcular intereses compuesto");
        System.out.println("6. Estudio de crédito");
        System.out.println("7. Salir");
        return sc.nextInt();
    }

    public static BigDecimal realizarDeposito(BigDecimal sal) {
        System.out.println("ingrese el valor del deposito: ");
        BigDecimal deposito = sc.nextBigDecimal();
        if (deposito.longValue() > 0) {
            sal = sal.add(deposito);
            System.out.println("Deposito exitoso");
        }
        else
        {
            System.out.println("Por favor ingrese un valor valido para deposito. ");
        }
        return sal;
    }

    public static BigDecimal realizarRetiro(BigDecimal sal) {
        System.out.println("ingrese el valor del retiro: ");
        BigDecimal retiro = sc.nextBigDecimal();

        Boolean valido = false;
        if (retiro.compareTo(sal) >= 0)
        {
            System.out.println("Saldo insuficiente para el retiro");
            valido = false;
        }
        else {valido = true;}

        if (retiro.doubleValue() <= 0)
        {
            System.out.println("Por favor ingrese un valor valido ");
            valido = false;
        }
        else {valido = true;}

        if (valido)
        {
           sal = sal.subtract(retiro);
            System.out.println("Retiro exitoso");
        }else{
            System.out.println("Saldo insuficiente para el retiro");
        }
        return sal;
    }

    public static BigDecimal liquidarInteres(BigDecimal sal, BigDecimal tasa) {
        BigDecimal interes = BigDecimal.valueOf(0);
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal unidad = new BigDecimal(1);

        if (sal.doubleValue() > 0) {
            interes = sal.multiply(unidad.add(tasa.divide(divisor)));
            System.out.println("Intereses generados " + interes);
        }else{
            System.out.println("no tiene saldo disponible para generar intereses. ");
        }
        return interes;
    }

    public static BigDecimal liquidarInteresCompuesto(BigDecimal sal, BigDecimal tasa) {
        BigDecimal divisor = new BigDecimal(100);
        BigDecimal unidad = new BigDecimal(1);
        if (sal.doubleValue() > 0) {
            BigDecimal interes = unidad.add(tasa.divide(divisor));
            sal =  sal.multiply(interes.pow(12));
            System.out.println("Intereses generados " + sal);
        }else{
            System.out.println("no tiene saldo disponible para generar intereses. ");
        }
        return sal;
    }

    public static void estudioCredito(BigDecimal sal, BigDecimal salMinimo) {
        if (sal.compareTo(salMinimo) >= 0) {
            System.out.println("Se le puede aprobar un crédito...");
        }
        else
        {
            System.out.println("no se le puede aprobar un crédito...");
        }
    }
}
