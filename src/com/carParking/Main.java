package com.carParking;

import com.carParking.parsingData.ClienteParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ClienteParser cp = new ClienteParser();
        String csvfilePath = "C:\\Users\\Abstract\\Desktop\\carParking2\\Clienti.csv";

        System.out.println("<------------|Benvenuto in Abstract Car Parking|------------>\n" +
                "Inserisci una scelta:\n" +
                "0. Esci\n" +
                "1. Carica da file CSV\n" +
                "2. Carica da tastiera\n"
        );


        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        switch (s) {
            case "0":
                break;
            case "1":
                cp.parseFromCsv(csvfilePath);

                break;
            case "2":
                cp.parseFromKeyboard();

                break;
            default:
        }
    }
}
