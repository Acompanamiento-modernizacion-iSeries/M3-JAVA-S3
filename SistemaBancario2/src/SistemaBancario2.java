import java.math.BigDecimal;
import java.util.Scanner;
import java.math.RoundingMode;

public class SistemaBancario2
{
    private static int seleccion;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int seleccion;
        seleccion = 0;
        int  salir = 0;
        Long cliente = Long.valueOf(0);
        BigDecimal tasaInteres = new BigDecimal(1.5);
        BigDecimal saldoCliente = new BigDecimal(0);
        do
        {
        menu();
        seleccion = sc.nextInt();
        switch (seleccion)
         {
          case 1:// Crear cliente
               cliente = CrearCliente();
               break;

             case 2:// Crear saldo inicial
                saldoCliente =  saldoInicial(cliente);
                 break;

             case 3:// Consulta saldo
                 System.out.println("=".repeat(30));
                 System.out.println("El Saldo del cliente "+ cliente + " es  :"+saldoCliente);
                 System.out.println("=".repeat(30));
                 break;
             case 6://interes simple
                 BigDecimal interesSimple = calculaInteresSimple(saldoCliente, tasaInteres);
                 break;
             case 7:// interes compuesto
                 BigDecimal interesCompuesto = calculaInteresCompuesto(saldoCliente, tasaInteres);
                 System.out.println(" Calculo Intereses compuesto para un periodo de un año es :" + interesCompuesto);
                 break;
             case 8:// Elegibilidad cliente
                 boolean aceptado = elegibilidad(cliente, saldoCliente);
                 if (aceptado)
                 {
                 System.out.println("Cliente pasa requisito de eligibilidad");
                 }
                 else
                 {
                 System.out.println("Cliente no cumple requisito de eligibilidad");
                 }
                 break;
             case 9:
                 salir = 1 ;
                 break;
             default:
                 System.out.println("Opción especificada no es correcta: " );
                 break;
         }

        } while (salir != 1);
        sc.close();
   }


    public static void  menu()
    {
        System.out.println("=".repeat(30));
        System.out.println("Sistema Bancario 2.0\n");
        System.out.println("Opciones: \n" );
        System.out.println("1. Crear Cliente" );
        System.out.println("2. Crear saldo inicial " );
        System.out.println("3. Consultar saldo ");
        System.out.println("4. Realizar deposito" );
        System.out.println("5. Retirar" );
        System.out.println("6. Calculo de interese simple" );
        System.out.println("7. Calculo de interese compuesto" );
        System.out.println("8. Elegibilidad para crédito" );
        System.out.println("9.  Salir del menu: " );
        System.out.println("=".repeat(30));
    }

    public static long CrearCliente()
    {
        Scanner sc = new Scanner(System.in);
        Long nuevoCliente;
        System.out.println("=".repeat(30));
        System.out.println(" Opcion 1 crear cliente :\n");
        nuevoCliente  = sc.nextLong();
        if (nuevoCliente == 0)
        {
            System.out.println("Ingrese un numero diferente de cero..." );
        }
        return nuevoCliente;
    }

    public static BigDecimal saldoInicial(long id_cliente)
    {
        Scanner sc = new Scanner(System.in);
        Long nuevoCliente;
        BigDecimal saldo = new BigDecimal(0);
        System.out.println("Ingrese saldo inicial :" );
        if (sc.hasNextBigDecimal())
        {
            saldo = saldo.add(sc.nextBigDecimal());
        }
        else
        {
            System.out.println("Ingrese valor valido..." );
            sc.next();
        }
        return saldo;
    }
    public static BigDecimal calculaInteresSimple(BigDecimal Saldo, BigDecimal Interes )
    {
        System.out.println("=".repeat(30));
        Saldo = Saldo.add(Saldo.multiply(Interes));
        System.out.println(" Interese simple en un periodo es :" +Saldo);
        return  Saldo.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculaInteresCompuesto(BigDecimal SaldoIni, BigDecimal Interes )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("=".repeat(30));
        System.out.println(" Ingrese número de periodos en un año para calculo :\n");
        Integer periodos  = sc.nextInt();
        BigDecimal base = BigDecimal.ONE.add(Interes.divide(BigDecimal.valueOf(periodos), 10, RoundingMode.HALF_UP)); // (1 + r/n)
        BigDecimal exponente = BigDecimal.valueOf(periodos * 1.0);  // nt
        BigDecimal SaldoNew = SaldoIni.multiply(base.pow(exponente.intValue()));   // P * (1 + r/n)^(nt)
        // System.out.println(" Calculo Intereses compuesto para un periodo de un año es :" +SaldoNew);
        //        return SaldoNew.setScale(2);
        return SaldoNew.setScale(2, RoundingMode.HALF_UP);
        //return SaldoNew;
    }

    protected static boolean elegibilidad(long id_cliente, BigDecimal saldo)
    {
        Scanner sc = new Scanner(System.in);
        Long nuevoCliente;
        boolean aprobado;
        aprobado = false;
        Integer  edad;
        BigDecimal topeMinino = new BigDecimal(10000);
        System.out.println("=".repeat(30));
        System.out.println(" Ingrese edad cliente :\n");
        edad  = sc.nextInt();
        if ((edad > 18) || (saldo.compareTo(saldo) > topeMinino.compareTo(topeMinino) ))
        {
            return true;
        }
        return aprobado;
    }

}
