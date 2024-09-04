import java.math.BigDecimal;

public class CuentaBancaria {
   private BigDecimal saldo;

    public CuentaBancaria(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /*metodo*/
    public BigDecimal consultarSaldo()
    {
     return saldo;
    }

    public BigDecimal calculoInteresesSimple(BigDecimal tasainteres, int tiempo)
    {
        BigDecimal tasacalculada = tasainteres.divide(BigDecimal.valueOf(100));
        BigDecimal capitalFinal = saldo.multiply(BigDecimal.ONE.add(tasacalculada.multiply(BigDecimal.valueOf(tiempo))));
        return capitalFinal;
    }

    public BigDecimal calculoInteresesCompuesto(BigDecimal tasainteres, int tiempo)
    {
        BigDecimal tasacalculada = tasainteres.divide(BigDecimal.valueOf(100));
        BigDecimal base = BigDecimal.ONE.add(tasacalculada);
        BigDecimal capitalFinal = saldo.multiply(base.pow(tiempo));
        return capitalFinal;
    }
    public Boolean validoCredito()
    {
        //* Validar rango saldo*//
            BigDecimal minimo = new BigDecimal(1000000);
            BigDecimal maximo = new BigDecimal(5000000);
            if (saldo.compareTo(minimo) >= 0 && saldo.compareTo(maximo) <= 0)  {
               System.out.println("Es apto para Credito: \n" );
               return true;

            } else {
              System.out.println("No es apto para Credito: \n" );
              return false;
        }
     }
}
