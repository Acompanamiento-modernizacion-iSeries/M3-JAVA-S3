import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String nombre;
    private static String cuenta;
    private static BigDecimal saldo ;
    private static Integer periodos;
    private static boolean control ;
    private static int tipoOp = 0;
    private static int tipoOpcionTransaccion = 0;
    private static int tipoOpcionCalculoInteres = 0;

    public static void main(String[] args) {
        control = true ;
        infoInicial();
        do {
            menuPrincipal();
            if (scanner.hasNextInt()) {
                tipoOp = scanner.nextInt();
                switch (tipoOp) {
                    case 1: // SALDO ACTUAL
                        mostrarSaldo();
                        break;
                    case 2: //TRANSACCIONES
                        menuTransacciones();
                        Scanner scannerTransac = new Scanner(System.in);
                        tipoOpcionTransaccion = scannerTransac.nextInt();
                        switch (tipoOpcionTransaccion){
                            case 1: //DEPOSITOS
                                depositar();
                                break;
                            case 2: //RETIRO
                                retirar();
                                break;
                            case 3: //MENU ANTERIOR
                                menuPrincipal();
                                break;
                            default:
                                System.out.println("La opción seleccionada es invalida.");
                                break;
                        }
                    case 3:  // CALCULAR INTERES
                        menuCalculoInteres();
                        Scanner scannerCalculoInteres = new Scanner(System.in);
                        tipoOpcionCalculoInteres = scannerCalculoInteres.nextInt();
                        switch (tipoOpcionCalculoInteres){
                            case 1: //CALCULAR INTERES CORRIENTE
                                calcularInteresCorriente();
                                break;
                            case 2: //CALCULAR INTERES COMPUESTO
                                calcularInteresCompuesto();
                                break;
                            case 3: //MENU ANTERIOR
                                menuPrincipal();
                                break;
                            default:
                                System.out.println("La opción seleccionada es invalida.");
                                break;
                        }
                    case 4: //VIABILIDAD CREDITICIA
                        viabilidadCrediticia();
                        break;
                    default:
                        System.out.println("La opción seleccionada es invalida.");
                        break;
                }
                System.out.print("Desea realizar otra operacion? S/N ");
                String fin_programa = scanner.next();

                switch (fin_programa.toUpperCase()) {
                    case "S":
                        control = true;
                        break;
                    case "N":
                        System.out.println("GRACIAS POR USAR NUESTROS SERVICIOS !!!");
                        control = false;
                        break;
                    default:
                        System.out.println("Opcion Invalida. Debe seleccions S o N");
                        break;
                }
            }else{
                System.out.println("La opción seleccionada es invalida 1.");
                return;
            }
        }while (control == true);
    }
    private static void infoInicial(){
        System.out.println("Nombre:");
        nombre = scanner.next();
        System.out.println("Número de cuenta:");
        cuenta = scanner.next();
        System.out.println("Saldo inicial: ");
        saldo = scanner.nextBigDecimal();
    }
    private static void menuPrincipal(){
        System.out.print(" TIPO OPERACION \n");
        System.out.print("1. Consultar Saldo\n2. Transacciones (Depósitos / Retiros)\n3. Cálculo de interés\n4. Viabilidad crediticia\n\nSeleccione Opcion :");
    }
    private static void menuTransacciones(){
        System.out.print(" TRANSACCIONES \n");
        System.out.print("1. Depósitos\n2. Retiros\n3. Menú anterior\n\nSeleccione Opcion :");
    }
    private static void menuCalculoInteres(){
        System.out.print(" CALCULAR INTERES \n");
        System.out.print("1. Interés corriente\n2. Interés compuesto\n3. Menú anterior\n\nSeleccione Opcion :");
    }
    private static void mostrarSaldo (){
        System.out.println("\nCONSULTA DE SALDO\n");
        System.out.println("Saldo Actual : " + saldo);
    }
    private static void depositar(){
        System.out.println("\nDEPOSITO\n");
        System.out.print("Valor a Depositar : ");
        BigDecimal valor_deposito = scanner.nextBigDecimal();
        BigDecimal cero =  BigDecimal.ZERO;
        while (valor_deposito.compareTo(cero) <= 0) {
            System.out.println("El valor ingresado no es valido.");
            System.out.print("Valor a Depositar : ");
            valor_deposito = scanner.nextBigDecimal();
        }
        saldo = valor_deposito.add(saldo);
        System.out.println("Saldo Actual   : " + saldo);
    }
    private static void retirar (){
        System.out.println("\nRETIRO\n");
        System.out.print("Valor a Retirar : ");
        BigDecimal valorRetiro = scanner.nextBigDecimal();
        BigDecimal cero =  BigDecimal.ZERO;
        while (valorRetiro.compareTo(saldo) > 0 || valorRetiro.compareTo(cero) <= 0) {
            System.out.println("Saldo Insuficiente o Cantidad Invalida");
            System.out.print("Valor a Retirar : ");
            valorRetiro = scanner.nextBigDecimal();
        }
        saldo = (saldo.subtract(valorRetiro));
        System.out.println("Saldo Actual   : " + saldo);
    }
    private static void calcularInteresCorriente(){
        System.out.print("\nTasa de interés en porcentaje: ");
        BigDecimal tasaInteresCorriente = scanner.nextBigDecimal();
        BigDecimal interesCorriente = saldo.multiply(tasaInteresCorriente.divide(BigDecimal.valueOf(100)));
        System.out.println("Saldo        : " + saldo);
        System.out.println("Interes      : " + interesCorriente);
        System.out.println("Total        : " + (saldo.add(interesCorriente)));
    }
    private static void calcularInteresCompuesto(){
        System.out.print("\nTasa de interés en porcentaje: ");
        BigDecimal tasaInteresCompuesto = scanner.nextBigDecimal();
        System.out.print("\nNumero de periodos: ");
        BigDecimal periodos = BigDecimal.valueOf(scanner.nextInt());
        BigDecimal interesCompuesto = saldo.multiply(tasaInteresCompuesto.divide(BigDecimal.valueOf(100))).multiply(periodos);
        System.out.println("Saldo        : " + saldo);
        System.out.println("Interes      : " + interesCompuesto);
        System.out.println("Total        : " + (saldo.add(interesCompuesto)));
    }
    public static void viabilidadCrediticia(){
        if (saldo.compareTo(BigDecimal.valueOf(3000)) == 1){
            System.out.println("Es elegible para crédito");
        }else{
            System.out.println("No es elegible para crédito");
        }
    }
}
