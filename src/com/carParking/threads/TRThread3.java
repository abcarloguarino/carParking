package com.carParking.threads;

import com.carParking.parsingData.Cliente;

public class TRThread3 extends Thread {

    TRThread1 trt1;

    public TRThread3(TRThread1 trt1) {
        this.trt1 = trt1;
    }

    @Override
    public void start() {
        super.start();
        //Object[] clienti = clienteList.toArray();
        // System.out.println(clienti.toString());
        //Sorting by Date not collected
        trt1.getPw().println("Lista clienti per data non ancora ritirate: ");
        trt1.getPw().println("------------------------------------------------");
        if (trt1.getClienti().size() > 0) {
            for (Cliente c : trt1.getClienti()) {
                if (c.getFlagOperazione().contains("0"))
                    trt1.getPw().println(c.getNome() + " " + c.getCognome() + " " + c.getDataOperazione());
            }
        } else {
            trt1.getPw().println("Lista vuota");
        }

        trt1.getPw().println("------------------------------------------------");

        trt1.getPw().print("Mezzo più parcheggiato: ");
        //Sorting by vehicle type
        //clienteList.sort(Comparator.comparing(Cliente::getTipoMezzo));
        trt1.getClienti().sort((o1, o2)
                -> o1.getTipoMezzo().compareTo(
                o2.getTipoMezzo()));

        //Initialize counters of vehicles
        int f = 0, b = 0, cb = 0, ct = 0, m = 0;
        //Count of vehicles types
        for (Cliente cliente : trt1.getClienti()) {
            if (cliente.getFlagOperazione().contains("0")) {
                switch (cliente.getTipoMezzo()) {
                    case "4x4":
                        f++;
                        break;
                    case "berlina":
                        b++;
                        break;
                    case "cabrio":
                        cb++;
                        break;
                    case "city":
                        ct++;
                        break;
                    case "moto":
                        m++;
                        break;
                    default:
                        f = 0;
                        b = 0;
                        cb = 0;
                        ct = 0;
                        m = 0;
                }
            }

        }

        if ((f > b) && (f > cb) && (f > ct) && (f > m))
            trt1.getPw().println("4x4");
        if ((b > f) && (b > cb) && (b > ct) && (b > m))
            trt1.getPw().println("berlina");
        if ((cb > f) && (cb > b) && (cb > ct) && (cb > m))
            trt1.getPw().println("cabrio");
        if ((ct > f) && (ct > cb) && (ct > b) && (ct > m))
            trt1.getPw().println("city");
        if ((m > b) && (m > cb) && (m > ct) && (m > f))
            trt1.getPw().println("moto");
        trt1.getPw().println("------------------------------------------------");

        trt1.getPw().close();
    }
}
