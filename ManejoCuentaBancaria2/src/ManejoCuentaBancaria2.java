import Menu.Menu;

public class ManejoCuentaBancaria2 {


    public static void main(String[] args) {

        String nombre;
        Integer cuenta;
        Double saldo;

        Menu mn = new Menu();
        nombre = mn.IngresarNombre();
        cuenta = mn.IngreseCuenta();
        saldo = mn.IngreseSaldoCuenta();
        mn.setSaldomain(saldo);
        mn.MenuMain();

    }



}
