package com.carParking.parsingData;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Calendar;
import java.util.Date;

public class Cliente {
    //com.carParking.parsingData.Cliente attributes
    @CsvBindByPosition(position = 0)
    private String nome;
    @CsvBindByPosition(position = 1)
    private String cognome;
    @CsvBindByPosition(position = 2)
    private String tipoMezzo;

    public String getTipoParcheggio() {
        return tipoParcheggio;
    }

    @CsvBindByPosition(position = 3)
    private String tipoParcheggio;
    @CsvBindByPosition(position = 4)
    private Date dataOperazione;
    @CsvBindByPosition(position = 5)
    private String flagOperazione;

    public float getCosto() {
        return costo;
    }

    private float costo;

    //Starting of getters and setters
    public String getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(String t) {
        this.tipoMezzo = t;
    }


    public void setTipoParcheggio(String t) {
        this.tipoParcheggio = t;
    }

    public Date getDataOperazione() {
        return dataOperazione;
    }

    public void setDataOperazione(Date d) {
        this.dataOperazione = d;
    }

    public String getFlagOperazione() {
        return flagOperazione;
    }

    public void setFlagOperazione(String fo) {
        this.flagOperazione = fo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public String getCognome() {
        return cognome;
    }

    @Override
    public String toString() {
        return
                " nome: " + nome +
                        ", cognome: " + cognome +
                        ", tipoMezzo: " + tipoMezzo +
                        ", tipoParcheggio: " + tipoParcheggio +
                        ", dataOperazione: " + dataOperazione +
                        ", flagOperazione: " + flagOperazione +
                        ", costo: " + costo;
    }

    public void setCognome(String c) {
        this.cognome = c;
    }

    //Ending of getters and setters
    //Parking operation that use transport type, parking type and operation like int number
    public void operazioneMezzo(String tm, String tp, int fo) {
        //int postiAlCoperto = 30;
        //int postiAllAperto = 50;
        //Taking Date object of operation
        //Data variable of operations time
        long dataEntrataInMs = 0, dataUscitaInMs = 0;
        if (fo == 0) {
            this.tipoMezzo = tm;
            this.tipoParcheggio = tp;
            //Date entry object in ms
            dataEntrataInMs = dataOperazione.getTime();
        } else if (fo == 1) {
            Date tempdataOperazione = Calendar.getInstance().getTime();
            //Date exit object in ms
            dataUscitaInMs = tempdataOperazione.getTime();
            //Calling of price calculated method
            costo = calcolaCosto(dataEntrataInMs, dataUscitaInMs);
        }
    }

    //Price calculated method
    private float calcolaCosto(long dataEntrata, long dataUscita) {
        //Time calculated in ms
        long tempoInMill = dataUscita - dataEntrata;
        //Convert time into min
        int minutes = (int) ((tempoInMill / (1000 * 60)) % 60);
        //Initialize hours
        int hours = 0;
        //Everytime min are 60's multiple update total minutes minus 60 hours plus 1
        while (minutes % 60 == 0) {
            minutes -= 60;
            hours++;
        }
        // If parking time is "coperto" calculate related price
        if (tipoParcheggio.toLowerCase().contains("coperto")) {
            if (minutes <= 30) {
                costo += (hours) + 0.5;

            } else {
                hours++;
                costo += hours;

            }
            return costo;
            // If parking time is "aperto" calculate related price
        } else if (tipoParcheggio.toLowerCase().contains("aperto")) {
            if (minutes <= 30) {
                costo += (hours * 0.5) + 0.2;

            } else {
                hours++;
                costo += hours * 0.5;

            }
            return costo;
        }
        return 0;
    }

}
