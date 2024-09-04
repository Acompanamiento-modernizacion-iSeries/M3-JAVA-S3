import javax.swing.*;
import java.math.BigDecimal;

public class GestionCuentaBancaria {
    public static void main(String[] args) {

        BigDecimal saldo = null;

        boolean saldoValido = false;

        while (!saldoValido) {
            String saldoInicialStr = JOptionPane.showInputDialog("Ingresa tu saldo inicial:");

            //Se verifica que el saldo sea un valor válido.
            try {
                saldo  = new BigDecimal(saldoInicialStr);
                saldoValido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor ingresa un número.");
            }
        }

        CuentaBancaria cuentaBancaria = new CuentaBancaria(saldo);

        boolean continuar = true;

        while (continuar) {
            String menu = "¡Bienvenid@!\n"
                    + "\nMenú Principal:\n"
                    + "1. Consultar Saldo.\n"
                    + "2. Calcular intereses Simples.\n"
                    + "3. Calcular intereses Compuestos.\n"
                    + "4. Verificar elegibilidad.\n"
                    + "5. Salir.\n"
                    + "\nElige una opción entre (1-5):";
            String seleccion = JOptionPane.showInputDialog(menu);

            if (seleccion == null) {
                continue;
            }

            switch (seleccion) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Tu saldo actual es: $" + cuentaBancaria.consultarSaldo());
                    break;

                case "2":
                    BigDecimal tasaSimple = null;
                    Integer aniosSimple = null;

                    //Se verifican datos validos.
                    try {
                        String tasaSimpleStr = JOptionPane.showInputDialog("Ingresa tasa de interés anual:");
                        tasaSimple = new BigDecimal(tasaSimpleStr);
                        String aniosSimpleStr = JOptionPane.showInputDialog("Ingresa plazo en años:");
                        aniosSimple = Integer.parseInt(aniosSimpleStr);
                        JOptionPane.showMessageDialog(null, "Su Interes simple calculado es: $" + cuentaBancaria.interesSimple(tasaSimple, aniosSimple));
                    } catch (NumberFormatException e) {
                        // Identificar el dato inválido basado en su valor.
                        if (tasaSimple == null) {
                            JOptionPane.showMessageDialog(null, "Tasa inválida. Por favor ingresa un número válido.");
                        } else if (aniosSimple == null) {
                            JOptionPane.showMessageDialog(null, "Años inválidos. Por favor ingresa un número válido.");
                        }
                    }
                    break;

                case "3":
                    BigDecimal tasaCompuesta = null;
                    Integer aniosComp = null;

                    //Se verifican datos validos.
                    try {
                        String tasaCompStr = JOptionPane.showInputDialog("ngresa tasa de interés anual:");
                        tasaCompuesta = new BigDecimal(tasaCompStr);
                        String aniosCompStr = JOptionPane.showInputDialog("Ingresa plazo en años:");
                        aniosComp = Integer.parseInt(aniosCompStr);
                        JOptionPane.showMessageDialog(null, "Su Interes compuesto calculado es: $" + cuentaBancaria.interesCompuesto(tasaCompuesta, aniosComp));
                    } catch (NumberFormatException e) {
                        // Identificar el dato inválido basado en su valor.
                        if (tasaCompuesta == null) {
                            JOptionPane.showMessageDialog(null, "Tasa inválida. Por favor ingresa un número válido.");
                        } else if (aniosComp == null) {
                            JOptionPane.showMessageDialog(null, "Años inválidos. Por favor ingresa un número válido.");
                        }
                    }
                    break;

                case "4":
                    Boolean elegibilidad = cuentaBancaria.elegibilidad();
                    if (elegibilidad){
                        JOptionPane.showMessageDialog(null, "¡Felicitaciones, usted es elegible para un crédito!");
                    }else {
                        JOptionPane.showMessageDialog(null, "Lo sentimos, usted NO es elegible para un crédito en este momento.");
                    }
                    break;

                case "5":
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Gracias por usar el servicio. ¡Hasta pronto!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, selecciona una opción entre 1 y 5.");
                    break;
            }
        }


    }
}