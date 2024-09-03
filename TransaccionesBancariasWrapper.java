import javax.swing.*;
import java.math.BigDecimal;

public class TransaccionesBancariasWrapper {
    public static void main(String[] args) {

        String usuario = "";
        String Cuenta = "";
        Double saldo = 0.0;

        while (usuario.isEmpty()) {
            usuario = JOptionPane.showInputDialog("Por favor ingresa tu usuario");
            if (ValidaUsuario(usuario)){
                break;
            }
        }

        while (Cuenta.isEmpty()) {
            Cuenta = JOptionPane.showInputDialog("Por favor ingresa tu número de cuenta");
            if(ValidaCuenta(Cuenta)){
                break;
            }
        }

        while (saldo <= 0) {
            saldo = Double.parseDouble(JOptionPane.showInputDialog("Por favor ingresa el saldo actual"));
            if(ValidaSaldo(saldo)){
              break;
            }
        }

        MostrarmenuTrasacciones(saldo);
    }

    public static Boolean ValidaUsuario(String usuario){
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario incorrecto");
            return false;
        }
        return true;
    }

    public static Boolean ValidaCuenta(String cuenta){
        if (cuenta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Número de cuenta bancaria debe ser ingresada");
            return false;
        }
        return true;
    }

    public static Boolean ValidaSaldo(Double saldo){
        if (saldo <= 0) {
            JOptionPane.showMessageDialog(null, "Ingrese un saldo cotrrecto");
            return false;
        }
        return true;
    }

    public static void MostrarmenuTrasacciones(Double saldo){
        Double valordeposito;
        Double vaorretiro;
        Double tasaInteres = 0.09;
        Double tasaInteresCompuesta = 0.00;
        Integer periodos = 0;

        while (true) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(" 1 - consultar saldo\n" +
                    " 2 - Calcular interés simple\n" +
                    " 3 - Calcular interés compuesto\n" +
                    " 4 - verificar elegibilidad de crédito\n" +
                    " 5 - Salir"));

            switch (opcion){
                case 1:
                    JOptionPane.showMessageDialog(null, "El saldo de su cuenta es " + saldo);
                    break;
                case 2:
                    BigDecimal ValorInteresSimple =  CalcularInteresSimple(saldo, tasaInteres );
                    JOptionPane.showMessageDialog(null, "El valor con tasa de interés simple es :" + ValorInteresSimple);
                    break;
                case 3:
                    while (tasaInteresCompuesta == 0.00){
                        try {
                            tasaInteresCompuesta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa interés deseada"));
                        }catch (Exception e){
                            tasaInteresCompuesta = 0.00;
                        }
                        if(ValidaTasaInteresCompuesta(tasaInteresCompuesta)){
                            break;
                        }
                    }
                    while (periodos == 0){
                        try {
                            periodos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese número de periodos en años deseado"));
                        }catch (Exception e){
                            periodos = 0;
                        }
                        if(ValidaPeriodos(periodos)){
                            break;
                        };
                    }
                    BigDecimal valorInteresCompuesto =  CalcularInteresCompuesto(saldo, tasaInteresCompuesta, periodos);
                    JOptionPane.showMessageDialog(null, "Valor con tasa de interés compuesta es de: " + valorInteresCompuesto);
                    break;
                case 4:
                    VerificarElegibilidadCredito(saldo);

                    break;
                case 5:
                    break;
                default: JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }
            if (opcion == 5){
                break;
            }

        }
    }

    public static BigDecimal CalcularInteresSimple(Double saldo, Double tasaInteres){
        BigDecimal ValorInteresSimple;
        ValorInteresSimple = BigDecimal.valueOf(saldo * tasaInteres);
        return ValorInteresSimple;

    }

    public static BigDecimal CalcularInteresCompuesto(Double saldo, Double tasaInteres , Integer periodos){
        BigDecimal  ValorInteresCompuesto;
        ValorInteresCompuesto = BigDecimal.valueOf((saldo * tasaInteres * periodos) / 100);
        return ValorInteresCompuesto;
    }

    public static Boolean VerificarElegibilidadCredito(Double saldo){
        if (saldo >= 3000000.00){
            JOptionPane.showMessageDialog(null, "Es elegible para crédito");
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "No es elegible para crédito");
        }
        return false;
    }

    public static Boolean ValidaPeriodos(Integer periodos){
        if(periodos == 0){
            JOptionPane.showMessageDialog(null, "Debe ingresar un periodo valido");
            return false;
        }
        return  true;

    }

    public static Boolean ValidaTasaInteresCompuesta(Double tasaInteresCompuesta){
        if (tasaInteresCompuesta <= 0){
            JOptionPane.showMessageDialog(null, "Tasa de interés ingresada es invalida");
            return false;
        }
        return true;
    }


}
