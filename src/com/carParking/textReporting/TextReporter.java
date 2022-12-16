package com.carParking.textReporting;


import com.carParking.parsingData.Cliente;
import com.carParking.parsingData.ClienteParser;
import com.carParking.threads.TRThread1;
import com.carParking.threads.TRThread2;
import com.carParking.threads.TRThread3;

import java.util.List;


public class TextReporter {

    public void ReportClienti(ClienteParser cp, List<Cliente> clienti) {

        TRThread1 trThread1 = new TRThread1(cp, clienti);
        trThread1.start();
        TRThread2 trThread2 = new TRThread2(trThread1);
        trThread2.start();
        TRThread3 trThread3 = new TRThread3(trThread1);
        trThread3.start();

    }
}

