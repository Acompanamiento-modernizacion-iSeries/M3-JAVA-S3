import java.util.Scanner;

public class SimuladorCuentaBancaria {
    public static void main(String[] args) {
        String separador1;
        String separador2;
        String menu;
        String nombre;
        int cuenta;
        int opcionMenu = 0;
        double tasa = 0.0050;
        double saldo;
        double monto;
        int numTrx;
        boolean continuar = true;

        separador1 = "-".repeat(100);
        separador2 = "-".repeat(60);

        Scanner sacanner = new Scanner(System.in);

        System.out.println(separador1);
        System.out.println("Ingrese el Nombre:");
        nombre = sacanner.next();

        System.out.println("Ingrese el numero de cuenta:");
        cuenta = sacanner.nextInt();

        System.out.println("Ingrese el Saldo Inicial:");
        saldo = sacanner.nextDouble();
        System.out.println(separador1);

        System.out.println(separador1);

        System.out.println("Hola " + nombre + ", tu cuenta No. " + cuenta
                + "\nTu saldo Inicial es: $"+ saldo);
        // ---------------------------------------------------------
        while (continuar){
            System.out.println(separador1);
            menu =     separador2
                    + "\nMenú Principal:\n"
                    + "   1. Consultar Saldo\n"
                    + "   2. Realizar Depósito\n"
                    + "   3. Realizar Retiro\n"
                    + "   4. Calcular Intereses\n"
                    + "   5. Salir\n"
                    +  separador2;
            System.out.println(menu);
            System.out.println("Elige una opción entre (1-5):");
            opcionMenu = sacanner.nextInt();

            switch (opcionMenu) {
                case 1:  // Consultar Saldo
                    System.out.println("Tu saldo actual es: $" + saldo);
                    break;
                case 2:  // Realizar Depósito
                    System.out.println("Ingrese el Monto a Depositar: ");
                    monto = sacanner.nextDouble();
                    if (monto<=0){
                        System.out.println(" *** *** ALERTA -- Transacción No exitosa, " +
                                "El monto ingresado debe ser mayor a Cero (0). *** *** ");
                        continue;
                    }
                    saldo = saldo + monto;
                    break;
                case 3:  // Realizar Retiro
                    System.out.println("Ingrese el Monto a Retirar: ");
                    monto = sacanner.nextDouble();
                    if (monto<=0){
                        System.out.println(" *** *** ALERTA - Transacción No exitosa, " +
                                "El monto ingresado debe ser mayor a Cero (0). *** *** ");
                        continue;
                    }
                    if (monto > saldo){
                        System.out.println(" *** *** ALERTA - Transacción No exitosa, " +
                                "Saldo Insuficiente. *** *** ");
                        continue;
                    }
                    saldo = saldo - monto;
                    break;
                case 4:  // Calcular Intereses
                    System.out.println("Tus intereses generados son: $" + saldo * tasa);
                    break;
                case 5:  // Salir
                    System.out.println(separador2);
                    System.out.println( nombre +  ", Gracias por usar nuestro servicio.\n¡Hasta pronto!");
                    continuar = false;
                    break;
                default:
                    System.out.println(separador2);
                    System.out.println(  " *** *** ALERTA - La Opción de Menú " + opcionMenu
                            + " NO es valida. *** ***");
                    System.out.println(separador2);
                    continuar = true;
                    break;
            }
            System.out.println(separador1);
        }
        // ---------------------------------------------------------
        System.out.println("Tú Saldo final es:  $"+ saldo);
        // Cerrar el scanner al finalizar
        sacanner.close();
    }
}


