import java.math.BigDecimal;

public class CuentaBancaria {
    private String nombreCliente;
    private Integer numeroDeCuenta;
    private BigDecimal saldo;

    public CuentaBancaria(String nombreCliente, Integer numeroDeCuenta, BigDecimal saldoInicial) {
        this.nombreCliente = nombreCliente;
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldoInicial;
    }

    public BigDecimal consultarSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal monto) {
        saldo = saldo.add(monto);
    }

    public boolean retirar(BigDecimal monto) {
        if (monto.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(monto);
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal calcularInteresSimple(BigDecimal tasaInteres, int años) {
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal factor = BigDecimal.ONE.add(tasaDecimal.multiply(new BigDecimal(años))); // (1 + (r/100)t)
        return saldo.multiply(factor).subtract(saldo); // CF - C
    }

    public BigDecimal calcularInteresCompuesto(BigDecimal tasaInteres, int años) {
        BigDecimal uno = BigDecimal.ONE;
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal cantidadCompuesta = saldo.multiply((uno.add(tasaDecimal)).pow(años));
        return cantidadCompuesta.subtract(saldo);
    }

    public Boolean verificarElegibilidadCredito() {
        BigDecimal umbral = new BigDecimal("1000");
        return saldo.compareTo(umbral) >= 0;
    }
}