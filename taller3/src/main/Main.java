package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el saldo inicial de la cuenta: ");
        String saldoInicial = scanner.nextLine();

        Cuenta cuenta = new Cuenta(saldoInicial);
        Menu menu = new Menu(cuenta);

        menu.mostrarMenu();
    }
}

