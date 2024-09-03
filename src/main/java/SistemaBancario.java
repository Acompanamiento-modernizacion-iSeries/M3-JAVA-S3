import java.math.BigDecimal;
import java.util.Scanner;

public class SistemaBancario {

    private static String nombre = "";
    private static Integer numeroCuenta = 0;
    private static BigDecimal saldo = new BigDecimal(0.0);
    private static Double interesMensual = 0.83;

    public void solicitarInfo(Scanner scanner){
        System.out.println("Ingrese Nombre:");
        nombre = scanner.nextLine();

        System.out.println("Ingrese Numero de cuenta:");
        numeroCuenta = scanner.nextInt();

        System.out.println("Ingrese Saldo de cuenta:");
        saldo = scanner.nextBigDecimal();
    }

    public void ingresarTransaccion(Scanner scanner, String tipo){
        System.out.println("Ingrese Valor de transacción:");
        BigDecimal valor  = scanner.nextBigDecimal();

        if("depósito".equals(tipo)){
            saldo = saldo.add(valor);
        } else if("retiro".equals(tipo)){
            saldo = saldo.subtract(valor);
        }
        System.out.println("Su saldo es: " + saldo);
    }

    public BigDecimal findSaldo(){
        return saldo;
    }

    public BigDecimal calcularInteres(){
        return saldo.multiply(BigDecimal.valueOf(interesMensual/100));
    }

    public BigDecimal calcularInteresCompuesto(Scanner scanner){
        System.out.println("Ingrese el tiempo de la inversión en años:");
        Integer ano  = scanner.nextInt();
        BigDecimal interesAnual = BigDecimal.valueOf(interesMensual*12/100).add(BigDecimal.valueOf(1)).pow(ano);
        return saldo.multiply(interesAnual);
    }

    public Boolean elegible(){
        return saldo.compareTo(new BigDecimal(1000)) >= 0;
    }
}
