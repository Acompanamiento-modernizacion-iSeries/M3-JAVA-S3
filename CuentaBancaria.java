import java.math.BigDecimal;

public class CuentaBancaria {
    private BigDecimal saldo;
    private Double tasaInteres;
    private Integer plazo;

    public CuentaBancaria(BigDecimal saldo, Double tasaInteres, Integer plazo) {
        this.saldo = saldo;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }
}
