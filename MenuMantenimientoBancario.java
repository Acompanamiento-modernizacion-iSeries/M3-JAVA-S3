import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMantenimientoBancario {
    public static void main(String[] args) {
        int i = 0;
        int opc ;
        Scanner sc = new Scanner(System.in);
        System.out.println("Saldo inicial: \n" );
        double saldo = sc.nextDouble();
        System.out.println("Edad: \n" );
        int edad =sc.nextInt();
        SaldoBancario Saldocliente = new SaldoBancario(saldo, edad);
        double monto ;
        double tasa = 0.0;
        int meses = 0;

        while (i < 9){
            System.out.println("Opciones: \n" );
            System.out.println("1        Consultar saldo: " );
            System.out.println("2        Calcular Interes Simple: " );
            System.out.println("3        Calcular Interes Compuesto: " );
            System.out.println("4        Elegibilidad para Credito: " );
            System.out.println("9        Salir del menu: " );
            opc = sc.nextInt();
            switch (opc){
                case 1:
                    //consultar saldo
                    System.out.println("Saldo: \n" );
                    System.out.println(Saldocliente.saldo);
                    break;
                case 2:
                    try{
                        System.out.println("Ingrese Tasa: \n" );
                        tasa = sc.nextDouble();
                        System.out.println("Ingrese Mese: \n" );
                        meses =sc.nextInt();
                        System.out.println("Interes Simple: " + Saldocliente.InteresSimple(tasa, meses));
                    }catch (InputMismatchException E){
                        if (tasa == 0.0) {
                            System.out.println("Ingrese Tasa valida!");
                        }
                        else if (meses == 0) {
                            System.out.println("Ingrese meses validos!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Ingrese Tasa: \n" );
                    tasa = sc.nextDouble();
                    System.out.println("Ingrese Mese: \n" );
                    meses =sc.nextInt();
                    System.out.println("Interes Compuesto: " + Saldocliente.InteresCompuesto(tasa, meses));
                    break;
                case 4:
                    if (Saldocliente.Elegibilidad()){
                        System.out.println("Usted es elegible para credito");
                    }else{
                        System.out.println("Usted no es elegible para credito");
                    }
                    break;
                case 9:
                    i = 10;
                    break;
                default:
                    System.out.println("Opción no valida: \n" );
                    break;
            }

        }
        sc.close();

    }

}
