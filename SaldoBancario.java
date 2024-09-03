import java.math.BigDecimal;

public class SaldoBancario {
    double saldo;
    int edad;
    public  SaldoBancario(double saldo, int edad) {
        this.saldo = saldo;
        this.edad = edad;
    }
    public BigDecimal InteresSimple(double tasa, int meses){
        BigDecimal interessimple;
        interessimple = BigDecimal.valueOf( saldo * (1 + (tasa/100) * meses ));
        return interessimple;
    }
    public BigDecimal InteresCompuesto(double tasa, int meses){
        BigDecimal interescompuesto;
        interescompuesto = BigDecimal.valueOf((1+(tasa/100)));
        interescompuesto = interescompuesto.pow(meses);
        interescompuesto = interescompuesto.multiply(BigDecimal.valueOf(saldo));
        return interescompuesto;
    }
    public  Boolean Elegibilidad(){

        return this.edad >= 18;
    }

}
