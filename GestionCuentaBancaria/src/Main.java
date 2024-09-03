import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){

        Cliente cliente = new Cliente();

        System.out.println("Ingrese los datos del cliente \n");
        System.out.println("Nombre: ");
        cliente.setNombre(scanner.next());
        System.out.println("Numero de cuenta: ");
        cliente.setCuenta(scanner.nextInt());
        System.out.println("Saldo inicial: ");
        cliente.setSaldo(scanner.nextDouble());

        while (true){

            System.out.println("\n Seleccione opcion: \n");
            System.out.println("1. Consultar saldo. ");
            System.out.println("2. Realizar depósito. ");
            System.out.println("3. Realizar retiro. ");
            System.out.println("4. Calcular interes simple. ");
            System.out.println("5. Calcular interes compuesto. ");
            System.out.println("6. verificar elegibilidad para crédito. ");
            System.out.println("7. Salir. \n");
            System.out.println("Opcion:");
            Integer Op = scanner.nextInt();
            Double interes = 0.05;

            switch (Op) {
                case 1:
                    consultaSaldo(cliente);
                    break;
                case 2:
                    deposito(cliente);
                    break;
                case 3:
                    retiro(cliente);
                    break;
                case 4:
                    interesSimple(cliente, interes);
                    break;
                case 5:
                    interesCompuesto(cliente, interes);
                    break;
                case 6:
                    eligibilidad(cliente);
                    break;
                case 7:
                    System.out.println("Fin proceso");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida \n");
            }
        }
    }

    public static void eligibilidad(Cliente cliente) {
       if (cliente.getSaldo() >= 5000){
           System.out.println(cliente.getNombre() +" el prestamo es viable");
       }
       else{
           System.out.println(cliente.getNombre() + " no es posible realizar prestamo");
       }
    }

    public static void interesCompuesto(Cliente cliente, Double interes) {
        System.out.println("\n Tiempo de prestamo(años): ");
        Integer tiempo = scanner.nextInt();
        Integer per = 12;
        Double base = (1+(interes / per));
        Double exponente = (double) (per * tiempo);
        BigDecimal intsaldo = BigDecimal.valueOf(cliente.getSaldo() * Math.pow(base,exponente) - cliente.getSaldo());
        System.out.println("intereses generados en: "+ tiempo + " año/s  es de: " + intsaldo);
    }

    public static void interesSimple(Cliente cliente, Double interes) {
        System.out.println("\n Tiempo de prestamo(años): ");
        Integer tiempo = scanner.nextInt();
        BigDecimal intsaldo = BigDecimal.valueOf(cliente.getSaldo() * interes * tiempo);
        System.out.println("intereses generados en: "+ tiempo + " año/s es de: " + intsaldo);
    }

    public static void retiro(Cliente cliente) {
        System.out.println("\n Valor retiro: ");
        Double valor = scanner.nextDouble();
        if(valor > cliente.getSaldo()){
            System.out.println("Fondos insuficientes");
        }else{
            cliente.setSaldo(cliente.getSaldo() - valor);
            System.out.println("Se realizo retiro");
        }
    }

    public static void deposito(Cliente cliente) {
        System.out.println("\n Valor deposito: ");
        Double valor = scanner.nextDouble();
         cliente.setSaldo(cliente.getSaldo() + valor);
        System.out.println("Se realizo depósito");
    }

    public static void consultaSaldo(Cliente cliente){
        System.out.println("Saldo actual: " + cliente.getSaldo());
    }
}