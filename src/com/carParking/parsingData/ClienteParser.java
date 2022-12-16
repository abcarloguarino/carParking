package com.carParking.parsingData;

import com.carParking.textReporting.TextReporter;
import com.opencsv.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClienteParser {

    //Clienti List Object result from parsing data records
    private final List<Cliente> clientList = new ArrayList<>();
    private int postiCoperti = 30, postiAperti = 50;
    private boolean voglioInserire = true;

    public String getTextfilePath() {
        return textfilePath;
    }

    private static final String textfilePath = "C:\\Users\\Abstract\\Desktop\\test.txt";

    public void parseFromKeyboard() {
        if ((postiCoperti > 0 || postiAperti > 0) && voglioInserire) {
            Cliente c = new Cliente();
            Scanner sc = new Scanner(System.in);
            System.out.println("Inserisci il nome del cliente");
            c.setNome(sc.nextLine());
            System.out.println("Inserisci il cognome del cliente");
            c.setCognome(sc.nextLine());
            System.out.println("Inserisci il tipo del mezzo del cliente tra: \n4x4, \nberlina, \ncabrio, \ncity, \n e moto");
            String typeVehicle = sc.nextLine().toLowerCase();
            switch (typeVehicle) {
                case "4x4":
                case "berlina":
                case "cabrio":
                case "city":
                case "moto":
                    c.setTipoMezzo(typeVehicle);
                    break;
                default:
            }
            System.out.println("Inserisci il tipo del parcheggio del cliente");
            String typePark = sc.nextLine();
            if (typePark.contains("aperto") && postiAperti > 0) {
                c.setTipoParcheggio(typePark);
                postiAperti--;
            }
            if (typePark.contains("coperto") && postiCoperti > 0) {
                c.setTipoParcheggio(typePark);
                postiCoperti--;
            }
            System.out.println("Inserisci la data del cliente in formato: dd/mm/yyyy hh:mm");
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dateTime;
            try {
                dateTime = sf.parse(sc.nextLine());

            } catch (ParseException e) {
                dateTime = null;
                e.printStackTrace();
            }
            c.setDataOperazione(dateTime);
            System.out.println("Inserisci il tipo di operazione: 0 per parcheggiare 1 per ritirare");
            c.setFlagOperazione(sc.nextLine());
            System.out.println("Vuoi effettuare un altro inserimento? s/n");
            String son = sc.nextLine();
            if (son.contains("s")) {
                clientList.add(c);
                parseFromKeyboard();
            } else if (son.contains("n")) {
                clientList.add(c);
                voglioInserire = false;
            }
            TextReporter tr = new TextReporter();
            tr.ReportClienti(ClienteParser.this, getClientList());
        }

    }

    //Parsing method that take the path of csv file
    public void parseFromCsv(String filePathCSV) {

        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // custom separator
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePathCSV)).withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1).withSkipLines(2)// skip the first and 2nd lines, header info
                .build()) {

            List<String[]> r = reader.readAll();

            for (String[] rows : r) {
                Cliente cliente = new Cliente();
                cliente.setNome(rows[0]);
                cliente.setCognome(rows[1]);
                cliente.setTipoMezzo(rows[2]);
                if (rows[3].contains("aperto") && postiAperti > 0) {
                    cliente.setTipoParcheggio(rows[3]);
                    postiAperti--;
                } else if (rows[3].contains("coperto") && postiCoperti > 0) {
                    cliente.setTipoParcheggio(rows[3]);
                    postiCoperti--;
                }

                SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dateTime;
                try {
                    dateTime = sf.parse(rows[4]);

                } catch (ParseException e) {
                    dateTime = null;
                    e.printStackTrace();
                }
                cliente.setDataOperazione(dateTime);
                cliente.setFlagOperazione(rows[5]);

                clientList.add(cliente);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TextReporter tr = new TextReporter();
        tr.ReportClienti(ClienteParser.this, getClientList());
        voglioInserire = false;
    }

    public List<Cliente> getClientList() {
        return clientList;
    }
}