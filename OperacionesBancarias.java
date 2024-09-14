import java.math.BigDecimal;
import java.math.RoundingMode;

public class OperacionesBancarias {
    public static BigDecimal calcularInteresSimple(BigDecimal saldo, Double tasaInteres, Integer plazo) {
        return saldo.multiply(BigDecimal.valueOf(tasaInteres))
                .multiply(BigDecimal.valueOf(plazo))
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP); // Redondeo hacia arriba
    }

    public static BigDecimal calcularInteresCompuesto(BigDecimal saldo, Double tasaInteres, Integer plazo) {
        BigDecimal factor = BigDecimal.valueOf(Math.pow(1 + tasaInteres / 100, plazo));
        return saldo.multiply(factor).subtract(saldo).setScale(2, RoundingMode.HALF_UP); // Redondeo hacia arriba
    }

    public static Boolean verificarElegibilidadCredito(BigDecimal saldo, Integer edad) {
        BigDecimal umbralSaldo = BigDecimal.ZERO;
        Integer umbralEdad = 18;

        boolean saldoSuficiente = saldo.compareTo(umbralSaldo) > 0;
        boolean edadSuficiente = edad >= umbralEdad;

        return saldoSuficiente && edadSuficiente;
    }
}
