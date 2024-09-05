package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Cuenta {
    private BigDecimal saldo;

    public Cuenta(String saldoInicial) {
        this.saldo = new BigDecimal(saldoInicial);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal calcularInteresSimple(String tasa, String tiempo) {
        BigDecimal tasaDecimal = new BigDecimal(tasa).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal tiempoDecimal = new BigDecimal(tiempo);
        return saldo.multiply(tasaDecimal).multiply(tiempoDecimal).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularInteresCompuesto(String tasa, String tiempo, String numPeriodos) {
        BigDecimal tasaDecimal = new BigDecimal(tasa).divide(BigDecimal.valueOf(100), MathContext.DECIMAL128);
        BigDecimal tiempoDecimal = new BigDecimal(tiempo);
        BigDecimal periodosDecimal = new BigDecimal(numPeriodos);

        BigDecimal montoFinal = saldo.multiply(BigDecimal.ONE.add(tasaDecimal.divide(periodosDecimal, MathContext.DECIMAL128)).pow(periodosDecimal.multiply(tiempoDecimal).intValue(), MathContext.DECIMAL128));
        return montoFinal.subtract(saldo).setScale(2, RoundingMode.HALF_UP);
    }

    public boolean validarElegibilidadCredito(String ingresoAnual, String deuda) {
        BigDecimal ingreso = new BigDecimal(ingresoAnual);
        BigDecimal deudaTotal = new BigDecimal(deuda);
        return ingreso.compareTo(deudaTotal.multiply(BigDecimal.valueOf(0.5))) > 0;
    }
}

