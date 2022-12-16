package com.carParking.threads;

import com.carParking.parsingData.Cliente;

import java.util.Comparator;

public class TRThread2 extends Thread {

    private final TRThread1 trThread1;

    public TRThread2(TRThread1 trThread1) {
        this.trThread1 = trThread1;
    }

    @Override
    public void start() {
        super.start();
        //Price calculation
        for (Cliente c : trThread1.getClienti()) {
            c.operazioneMezzo(c.getTipoMezzo(),
                    c.getTipoParcheggio(),
                    Integer.parseInt(c.getFlagOperazione()));
        }

        //Sorting by costo and print the first
        trThread1.getClienti().sort(Comparator.comparing(Cliente::getCosto));
        if (trThread1.getClienti().size() > 0) {
            trThread1.getPw().println("Il cliente che ha pagato di più è: " + trThread1.getClienti().get(0).getNome() + " " + trThread1.getClienti().get(0).getCognome());
        } else {
            trThread1.getPw().println("Il cliente che ha pagato di più non esiste perché la lista è vuota ");
        }

        trThread1.getPw().println("------------------------------------------------");

        //Sorting by Date
        trThread1.getClienteParser().getClientList().sort(Comparator.comparing(Cliente::getDataOperazione));
        trThread1.getPw().println("Lista clienti per data di parcheggio: ");
        trThread1.getPw().println("------------------------------------------------");
        if (trThread1.getClienteParser().getClientList().size() > 0) {
            // Print sorted by date list
            for (Cliente c : trThread1.getClienteParser().getClientList()) {
                trThread1.getPw().println(c.getNome() + " " + c.getCognome() + " " + c.getDataOperazione());
            }
        } else {
            trThread1.getPw().println("Lista vuota");
        }

        trThread1.getPw().println("------------------------------------------------");
    }
}
