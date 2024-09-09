import java.math.BigDecimal;
import java.math.RoundingMode;

public class CuentaBancaria {
    private BigDecimal saldo;
    private BigDecimal tasaInteres;

    public CuentaBancaria(BigDecimal saldoInicial, BigDecimal tasaInteres) {
        this.saldo = saldoInicial;
        this.tasaInteres = tasaInteres;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal calcularInteresSimple(int meses) {
        return saldo.multiply(tasaInteres).multiply(new BigDecimal(meses)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularInteresCompuesto(int meses) {
        BigDecimal factor = BigDecimal.ONE.add(tasaInteres).pow(meses);
        return saldo.multiply(factor).subtract(saldo).setScale(2, RoundingMode.HALF_UP);
    }

    public boolean verificarElegibilidadCredito() {
        // Supongamos que un usuario es elegible para crédito si su saldo es mayor a 5000
        return saldo.compareTo(new BigDecimal(5000)) > 0;
    }
}