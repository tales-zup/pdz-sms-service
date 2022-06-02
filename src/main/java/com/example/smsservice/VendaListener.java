package com.example.smsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;


@Component
public class VendaListener {

    private ObjectMapper mapper = new ObjectMapper();

    @SqsListener(value =  "https://sqs.sa-east-1.amazonaws.com/075970593276/vendas-sms-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void recieveMessage(String record) throws Exception {

        SqsRecord sqsRecord = mapper.readValue(record, SqsRecord.class);
        SqsMessage sqsMessage = mapper.readValue(sqsRecord.getMessage(), SqsMessage.class);
        sendSms(sqsMessage);
    }

    private void sendSms(SqsMessage sqsMessage) {
        String sms = "Ola {cliente}, a compra de R$" + sqsMessage.getValor() + " na loja " + sqsMessage.getIdLoja() + " foi confirmada!";
        sms = sms.concat(" Os produtos foram: " + sqsMessage.getProdutos());
        System.out.println(sms);
    }

}