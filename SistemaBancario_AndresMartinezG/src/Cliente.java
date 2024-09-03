import java.math.BigDecimal;

public class Cliente {
    public String nombre;
    public String numCuenta;
    public BigDecimal saldoActual;

    public Cliente(String nombre, String numCuenta, double saldoActual) {
        this.nombre = nombre;
        this.numCuenta = numCuenta;
        this.saldoActual = BigDecimal.valueOf(saldoActual);
    }
}
