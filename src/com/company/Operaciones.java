package com.company;

import java.math.BigDecimal;

public class Operaciones {

    public Double InteresSimple(Double saldo, Double tasaEfectiva, Integer tiempo){
        double interesSimple =  (saldo * tasaEfectiva) * tiempo;
        return interesSimple;
    }

    public BigDecimal InteresCompuesto( BigDecimal capital, Double tasaEfectiva, Integer tiempo){
        BigDecimal interesCompuesto = new BigDecimal(0);
        Double potenciaTasa;

        potenciaTasa = Math.pow((1+tasaEfectiva),tiempo);
        interesCompuesto = capital.multiply(BigDecimal.valueOf(potenciaTasa-1));
        return interesCompuesto;
    }


}
