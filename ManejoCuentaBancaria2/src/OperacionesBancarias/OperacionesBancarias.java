package OperacionesBancarias;

import java.math.BigDecimal;

public class OperacionesBancarias {

    public BigDecimal InteresSimple(double saldo,double tasainteres){
        return BigDecimal.valueOf(saldo *= tasainteres);
    }

    public BigDecimal InteresCompuesto(double saldo,double tasainteres,int tiempo){
        return BigDecimal.valueOf(saldo * Math.pow(1 + (tasainteres / 12), 12 * tiempo));
    }

}
