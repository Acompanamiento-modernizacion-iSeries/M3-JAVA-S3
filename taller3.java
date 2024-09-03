import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.SQLOutput;
import java.util.Scanner;

public class taller3 {

    // Atributos de la cuenta bancaria
    private String nombre;
    private Integer numeroCuenta;
    private BigDecimal saldo;

    // Constructor
    public taller3(String nombre, Integer numeroCuenta, BigDecimal saldoInicial) {
        this.nombre = nombre;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Función para consultar el saldo
    public BigDecimal consultarSaldo() {
        return saldo;
    }


    public BigDecimal calcularInteresSimple(BigDecimal principal, BigDecimal tasaInteres, int tiempo) {
        // En este primer paso convertimos la tasa de interés a decimal
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // luego Calculamos el interés simple: Principal * TasaDecimal * Tiempo
        BigDecimal interesSimple = principal.multiply(tasaDecimal).multiply(BigDecimal.valueOf(tiempo));

        // para este ejercicio al resultado lo redondeamos a 2 decimales
        return interesSimple.setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal calcularInteresCompuesto(BigDecimal principal, BigDecimal tasaInteres, int tiempo) {

        // En este primer paso convertimos la tasa de interés a decimal
        BigDecimal tasaDecimal = tasaInteres.divide(BigDecimal.valueOf(100));

        // Calculamos el factor de crecimiento: (1 + TasaDecimal)
        BigDecimal factorCrecimiento = BigDecimal.ONE.add(tasaDecimal);

        // Calculamos el monto final: Principal * (1 + TasaDecimal) ^ Tiempo
        BigDecimal montoFinal = principal.multiply(factorCrecimiento.pow(tiempo));

        // Calculamos el interés compuesto: MontoFinal - Principal
        BigDecimal interesCompuesto = montoFinal.subtract(principal);

        // tambien redondeamos a 2 decimales
        return interesCompuesto.setScale(2, RoundingMode.HALF_UP);
    }

    public Boolean verificarElegibilidadCredito(BigDecimal umbralCredito, int edadS) {

        if (umbralCredito.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        boolean saldoSuficiente = saldo.compareTo(umbralCredito) >= 0;
         boolean edadAdecuada = edadS > 17;
        return saldoSuficiente && edadAdecuada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el Nombre del dueño de la Cuenta Bancaria: ");
        String nombre = scanner.nextLine();

        int numeroCuenta;
        boolean validador = true;
        do {
            if (validador){
                System.out.print("Ingrese su número de cuenta: ");
            }else {
                System.out.print("Valor no válido. Ingrese su número de cuenta correcto: ");
            }
            validador= false;
            while (!scanner.hasNextInt()) {
                System.out.print("Valor no válido. Ingrese su número de cuenta (debe ser un número positivo): ");
                scanner.next();
            }
            numeroCuenta = scanner.nextInt();
        } while (numeroCuenta < 0);

        BigDecimal saldoInicial;
        validador = true;
        do {
            if (validador){
                System.out.print("Ingrese su saldo inicial: ");
            }else {
                System.out.print("Valor no válido. Ingrese un saldo correcto: ");
            }
            validador= false;
            while (!scanner.hasNextBigDecimal()) {
                System.out.print("Valor no válido. Ingrese su saldo inicial (debe ser un valor positivo): ");
                scanner.next();
            }
            saldoInicial = scanner.nextBigDecimal();
        } while (saldoInicial.compareTo(BigDecimal.ZERO) < 0);

        taller3 cuenta = new taller3(nombre, numeroCuenta, saldoInicial);

        int opcion;
        do {
            // Mostrar menú
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Calcular interés simple");
            System.out.println("3. Calcular interés compuesto");
            System.out.println("4. Verificar elegibilidad para crédito");
            System.out.println("5. Salir");

            // Leer opción del usuario
            System.out.print("Opción: ");
            while (!scanner.hasNextInt()) {
                System.out.print("La Opción digitada no es válida. Ingrese un número entre 1 y 5: ");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("===================================================");
                    System.out.println("==                                               ==");
                    System.out.println("====>      Su saldo actual es: " + cuenta.consultarSaldo() );
                    System.out.println("==                                               ==");
                    System.out.println("===================================================");
                    break;

                case 2:

                    BigDecimal tasaInteresSimple;
                    Integer tiempoSimple;
                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese la tasa de interés (%): ");
                        }else {
                            System.out.print("Valor no válido. Ingrese la tasa de interés (%) (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextBigDecimal()) {
                            System.out.print("Valor no válido. Ingrese la tasa de interés (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        tasaInteresSimple = scanner.nextBigDecimal();
                    } while (tasaInteresSimple.compareTo(BigDecimal.ZERO) < 0);

                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese el tiempo (en años): ");
                        }else {
                            System.out.print("Valor no válido. Ingrese el tiempo (en años) (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextBigInteger()) {
                            System.out.print("Valor no válido. Ingrese el tiempo (en años) (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        tiempoSimple = scanner.nextInt();
                    } while (tiempoSimple < 0);

                    BigDecimal interesSimple = cuenta.calcularInteresSimple( cuenta.saldo, tasaInteresSimple, tiempoSimple);
                    System.out.println("===================================================");
                    System.out.println("==                                               ==");
                    System.out.println("====>      Interés simple calculado: " + interesSimple);
                    System.out.println("==                                               ==");
                    System.out.println("===================================================");
                    break;

                case 3:
                    BigDecimal tasaInteresCompuesto;
                    Integer tiempoCompuesto;
                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese la tasa de interés (%): ");
                        }else {
                            System.out.print("Valor no válido. Ingrese la tasa de interés (%) (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextBigDecimal()) {
                            System.out.print("Valor no válido. Ingrese la tasa de interés (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        tasaInteresCompuesto = scanner.nextBigDecimal();
                    } while (tasaInteresCompuesto.compareTo(BigDecimal.ZERO) < 0);

                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese el tiempo (en años): ");
                        }else {
                            System.out.print("Valor no válido. Ingrese el tiempo (en años) (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextBigInteger()) {
                            System.out.print("Valor no válido. Ingrese el tiempo (en años) (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        tiempoCompuesto = scanner.nextInt();
                    } while (tiempoCompuesto < 0);

                    BigDecimal interesCompuesto = cuenta.calcularInteresCompuesto( cuenta.saldo,tasaInteresCompuesto, tiempoCompuesto);
                    System.out.println("===================================================");
                    System.out.println("==                                               ==");
                    System.out.println("====>      Interés compuesto calculado: " + interesCompuesto);
                    System.out.println("==                                               ==");
                    System.out.println("===================================================");
                    break;

                case 4:

                    BigDecimal umbralCredito;
                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese el umbral mínimo de saldo para crédito: ");
                        }else {
                            System.out.print("Valor no válido. Ingrese el umbral mínimo de saldo para crédito (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextBigDecimal()) {
                            System.out.print("Valor no válido. Ingrese el umbral mínimo de saldo para crédito (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        umbralCredito = scanner.nextBigDecimal();
                    } while (umbralCredito.compareTo(BigDecimal.ZERO) < 0);

                    int edad;
                    validador = true;
                    do {
                        if (validador){
                            System.out.print("Ingrese su edad: ");
                        }else {
                            System.out.print("Valor no válido. Ingrese la edad del solicitante (debe ser un valor positivo): ");
                        }
                        validador= false;
                        while (!scanner.hasNextInt()) {
                            System.out.print("Valor no válido. Ingrese la edad del solicitante (debe ser un valor numerico): ");
                            scanner.next();
                        }
                        edad = scanner.nextInt();
                    } while (edad < 0);

                    Boolean esElegible = cuenta.verificarElegibilidadCredito(umbralCredito, edad);
                    if (esElegible) {
                        System.out.println("===================================================");
                        System.out.println("==                                               ==");
                        System.out.println("==      Usted es elegible para crédito           ==");
                        System.out.println("==                                               ==");
                        System.out.println("===================================================");


                    } else {
                        System.out.println("===================================================");
                        System.out.println("==                                               ==");
                        System.out.println("==      Usted no es elegible para crédito        ==");
                        System.out.println("==                                               ==");
                        System.out.println("==                                               ==");
                        System.out.println("== El solicitante debe estar mayor de 18 años    ==");
                        System.out.println("==                                               ==");
                        System.out.println("===================================================");
                    }
                    break;

                case 5:
                    // Salir
                    System.out.println("Gracias por utilizar el sistema bancario. ¡Hasta luego!");
                    System.out.println("Autor: Harold Choles");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 5.");
                    break;
            }

        } while (opcion != 5);

        scanner.close();
    }
}
