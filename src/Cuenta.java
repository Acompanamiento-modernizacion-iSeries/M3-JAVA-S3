import java.math.BigDecimal;

public class Cuenta {
    private BigDecimal saldo;

    public Cuenta(BigDecimal saldo) {
        this.saldo = saldo;
    }

        public BigDecimal consultarSaldo()
    {
        return saldo;
    }

    public BigDecimal calculoInteresSimple(BigDecimal tasainteres, int tiempo)
    {
        BigDecimal tasaactual = tasainteres.divide(BigDecimal.valueOf(100));
        BigDecimal capitalFinal = saldo.multiply(BigDecimal.ONE.add(tasaactual.multiply(BigDecimal.valueOf(tiempo))));
        return capitalFinal;
    }

    public BigDecimal calculoInteresCompuesto(BigDecimal tasainteres, int tiempo)
    {
        BigDecimal tasaactual = tasainteres.divide(BigDecimal.valueOf(100));
        BigDecimal base = BigDecimal.ONE.add(tasaactual);
        BigDecimal capitalFinal = saldo.multiply(base.pow(tiempo));
        return capitalFinal;
    }
    public Boolean validoCredito()
    {
        BigDecimal minimo = new BigDecimal(1300000);
        BigDecimal maximo = new BigDecimal(6500000);
        if (saldo.compareTo(minimo) >= 0 && saldo.compareTo(maximo) <= 0)  {
            System.out.println("Cumple con las condiciones para un Credito" );
            return true;

        } else {
            System.out.println("No cumple con las condiciones para un Credito" );
            return false;
        }
    }
}