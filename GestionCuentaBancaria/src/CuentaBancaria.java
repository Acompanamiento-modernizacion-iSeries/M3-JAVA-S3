import java.math.BigDecimal;

public class CuentaBancaria {

    private BigDecimal saldo;

    public CuentaBancaria(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal consultarSaldo(){
        return saldo;
    }

    public BigDecimal interesSimple(BigDecimal tasaInteres, int anios ){
        BigDecimal tasa = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal capitalFinal = saldo.multiply(BigDecimal.ONE.add(tasa.multiply(BigDecimal.valueOf(anios))));
        return capitalFinal;
    }

    public BigDecimal interesCompuesto(BigDecimal tasaInteres, int anios ){
        BigDecimal tasa = tasaInteres.divide(BigDecimal.valueOf(100));
        BigDecimal base = BigDecimal.ONE.add(tasa);
        BigDecimal capitalFinal = saldo.multiply(base.pow(anios));
        return capitalFinal;
    }

    public Boolean elegibilidad(){
        BigDecimal monto = new BigDecimal(1000000);
        return saldo.compareTo(monto) > 0;
    }
}
