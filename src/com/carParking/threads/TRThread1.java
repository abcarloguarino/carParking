package com.carParking.threads;

import com.carParking.parsingData.Cliente;
import com.carParking.parsingData.ClienteParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.List;


public class TRThread1 extends Thread {

    private final ClienteParser clienteParser;
    private final List<Cliente> clienti;

    public ClienteParser getClienteParser() {
        return clienteParser;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    protected PrintWriter pw;

    public TRThread1(ClienteParser cp, List<Cliente> c) {
        this.clienteParser = cp;
        this.clienti = c;
    }


    @Override
    public void start() {
        super.start();
        {
            try {
                pw = new PrintWriter(new FileOutputStream(getClienteParser().getTextfilePath()));
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        pw.println("Numero di clienti: " + clienti.size());
        pw.println("------------------------------------------------");
    }

    public PrintWriter getPw() {
        return pw;
    }

}
