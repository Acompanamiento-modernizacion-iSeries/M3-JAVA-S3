                                                                                                                                                                 import java.math.BigDecimal;
import java.util.Scanner;

public class SimuladorCuentaBancaria {
    public static BigDecimal saldo;
    public static String     separador1 = "-".repeat(100);
    public static String     separador2 = "-".repeat(60);
    public static String     texto;
    public static String     nombre;
    public static Integer    cuenta;
    public static Scanner    scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ingresarDatosCliente();
        menu();

        scanner.close();
    }

    public static void  ingresarDatosCliente(){
        mensaje(separador1);
        mensaje("Ingrese el Nombre:");
        nombre = ingresarTexto();
        mensaje("Ingrese el numero de cuenta:");
        cuenta = ingresarEntero();
        mensaje("Ingrese el Saldo Inicial:");
        saldo = ingresarDecimal();
        mensaje(separador1);
        texto = "Hola " + nombre + ", tu cuenta No. " + cuenta +
                "\nTu saldo Inicial es: $"+ saldo.doubleValue();
        mensaje(texto);
    }

    public static void  menu(){
        int     opcionMenu;
        boolean continuar  = true;

        while (continuar){
            mensaje(separador1);
            texto = separador2
                    + "\n                    Elige una Opción\n"
                    +  separador2
                    + "\nMenú Principal:\n"
                    + "   1. Consultar Saldo\n"
                    + "   2. Realizar Depósito\n"
                    + "   3. Realizar Retiro\n"
                    + "   4. Calcular Interes Simple\n"
                    + "   5. Calcular Interes Compuesto (1 Año)\n"
                    + "   6. Verificar Elegibilidad\n"
                    + "   7. Salir\n"
                    +  separador2;
            mensaje(texto);
            opcionMenu = 0;
            mensaje("Opción (1-7): ", true);
            opcionMenu = ingresarEntero();
            mensaje(separador1);

            switch(opcionMenu) {
                case 1:  consultarSaldo();                   break;
                case 2:  realizarDeposito();                 break;
                case 3:  realizarRetiro();                   break;
                case 4:  calcularInteresSimple();            break;
                case 5:  calcularInteresCompuesto();         break;
                case 6:  verificarElegibilidadCredito();     break;
                case 7:  continuar = salirDelMenu();         break;
                default: continuar = opcionDeMenuNoValida(opcionMenu);
            }
        }
    }

    public static void  mensaje(String texto, boolean sinSaltoDeLinea ){
        if (sinSaltoDeLinea) {
            System.out.print(texto);
        }else {
            System.out.println(texto);
        }
    }

    public static void  mensaje(String texto){
        mensaje(texto ,false);
    }

    public static String ingresarTexto(){
        String dato = scanner.nextLine();
        return dato;
    }

    public static Integer ingresarEntero(){
        Integer dato = scanner.nextInt();
        return dato;
    }

    public static BigDecimal ingresarDecimal (){
        BigDecimal dato = scanner.nextBigDecimal();
        return dato;
    }

    private static boolean opcionDeMenuNoValida(int opcion) {
        mensaje(separador2);
        texto =   " *** *** ALERTA - La Opción de Menú " + opcion + " NO es valida. *** ***";
        mensaje(texto);
        mensaje(separador2);
        return  true;
    }

    private static boolean salirDelMenu() {
        mensaje(separador2);
        mensaje( nombre +  ", Gracias por usar nuestro servicio.\n¡Hasta pronto!");
        return false;
    }

    private static void verificarElegibilidadCredito() {
        mensaje("Ingrese el ingreso anual: $", true);
        BigDecimal ingresoAnual = ingresarDecimal();

        mensaje("Ingrese la deuda total: $", true);
        BigDecimal deudaTotal = ingresarDecimal();
        boolean elegible;
        try {
            BigDecimal limite = ingresoAnual.multiply(BigDecimal.valueOf(0.5));
            elegible = ingresoAnual.compareTo(deudaTotal) > 0 && deudaTotal.compareTo(limite) <= 0;
        } catch (ArithmeticException e) {
            System.out.println("Error en la validación de elegibilidad para crédito: " + e.getMessage());
            elegible=  false;
        }

        System.out.println(elegible ? "Elegible para crédito." : "No elegible para crédito.");

    }

    private static void calcularInteresCompuesto() {
        mensaje("Ingrese el capital:", true);
        BigDecimal capital = ingresarDecimal();
        mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = ingresarDecimal();
        mensaje("Ingrese el tiempo en años:", true);
        int tiempo = ingresarEntero();

        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular (1 + tasaInteres)
        BigDecimal base = BigDecimal.ONE.add(tasaInteresDecimal);

        // Calcular (1 + tasaInteres)^tiempo
        BigDecimal interesCompuesto = base.pow(tiempo);

        // Calcular capital * (1 + tasaInteres)^tiempo
        interesCompuesto = capital.multiply(interesCompuesto);

        mensaje("El interes compuesto es: " + interesCompuesto.doubleValue());

    }

    private static void calcularInteresSimple() {
        mensaje("Ingrese el capital:", true);
        BigDecimal capital = ingresarDecimal();
        mensaje("Ingrese la tasa de interes anual:", true);
        BigDecimal tasaInteres = ingresarDecimal();
        mensaje("Ingrese el tiempo en años:", true);
        int tiempo = ingresarEntero();
        // BigDecimal interes = capital.multiply(tasaInteres).multiply(BigDecimal.valueOf(tiempo));

        BigDecimal interesSimple;
        // Convertir la tasa de interés a un valor decimal dividiéndola por 100
        BigDecimal tasaInteresDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calcular el interés simple: capital * tasaInteres * tiempo
        interesSimple = capital.multiply(tasaInteresDecimal).multiply(BigDecimal.valueOf(tiempo));

        mensaje("El interes simple es: " + interesSimple.doubleValue());
    }

    private static void realizarRetiro() {
        mensaje("Ingrese el Monto a Retirar:  ",true);
        BigDecimal monto = ingresarDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
            return;
        }
        if (monto.compareTo(saldo) > 0){
            mensaje(" *** ALERTA *** \n    - Transacción No exitosa, Saldo Insuficiente.");
            return;
        }
        saldo = saldo.subtract(monto);
        mensaje(" *** RETIRO REALIZADO EXITOSAMENTE ***");
        return;

    }

    private static void realizarDeposito() {
        mensaje("Ingrese el Monto a Depositar:  ", true);
        BigDecimal monto = ingresarDecimal();
        if (monto.compareTo(BigDecimal.ZERO) <= 0){
            mensaje(" *** ALERTA *** \n    - Transacción No exitosa, El monto debe ser mayor a Cero (0).");
        }else{
            saldo = saldo.add(monto);
            mensaje(" *** DEPOSITO REALIZADO EXITOSAMENTE ***");
        }
    }

    private static void consultarSaldo() {
        mensaje("\nTu saldo actual es: $" + saldo.doubleValue() + "\n");
    }

}


