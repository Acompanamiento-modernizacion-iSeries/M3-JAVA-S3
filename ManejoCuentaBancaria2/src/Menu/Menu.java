package Menu;

import OperacionesBancarias.OperacionesBancarias;

import javax.swing.*;

public class Menu {

    private Double Saldomain;
    private Double TasaInteres;
    private Integer Tiempo;
    private OperacionesBancarias opb = new OperacionesBancarias() ;

    public String IngresarNombre(){
        return JOptionPane.showInputDialog("Ingrese su nombre");
    }
    public Integer IngreseCuenta(){
      return Integer.parseInt(JOptionPane.showInputDialog("Ingrese su número de cuenta"));
    }
    public Double IngreseSaldoCuenta(){
      return Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo de la cuenta"));
    }

    public void MenuMain(){
        String opcion;

        do {
            opcion = MostrarOpciones();
            switch (opcion){
                case "C":
                    MensajeGenerico("El saldo del cliente es $"+getSaldomain());
                    break;
                case "I":
                    setTasaInteres(IngreseInteres());
                    MensajeGenerico("El interes siemple es de: $"
                            +opb.InteresSimple(getSaldomain(),getTasaInteres()));
                    break;
                case "O":
                    MensajeGenerico("El capital será tu saldo actual $"+getSaldomain());
                    setTasaInteres(IngreseInteres());
                    setTiempo(IngreseTiempo());
                    MensajeGenerico("El interés compuesto es de $"+opb.InteresCompuesto(
                            getSaldomain(),getTasaInteres(),getTiempo()));
                    break;
                default:
                    MensajeGenerico("Opción no válida");
                    break;
            }
        } while (SalirSiNo());

    }

    private String MostrarOpciones(){
        return JOptionPane.showInputDialog("C:Consultar saldo\nI:Interes simple\nO:Interés Compuesto");
    }

    private boolean SalirSiNo(){
        int salirint;
        boolean salir;
        salirint = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación", JOptionPane.YES_NO_OPTION);
        salir = (salirint == JOptionPane.YES_OPTION);
        return salir;
    }

    public Double IngreseInteres(){
        return Double.parseDouble(JOptionPane.showInputDialog("Ingrese la tasa de interés"));
    }

    public Integer IngreseTiempo(){
        return Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo en años"));
    }

    public Double getSaldomain() {
        return Saldomain;
    }

    public void setSaldomain(Double saldomain) {
        Saldomain = saldomain;
    }

    public Double getTasaInteres() {
        return TasaInteres;
    }

    public void setTasaInteres(Double tasaInteres) {
        TasaInteres = tasaInteres;
    }

    public Integer getTiempo() {
        return Tiempo;
    }

    public void setTiempo(Integer tiempo) {
        Tiempo = tiempo;
    }

    private void MensajeGenerico(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

}
