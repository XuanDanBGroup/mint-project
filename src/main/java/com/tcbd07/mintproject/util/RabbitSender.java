package com.tcbd07.mintproject.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback=new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            System.err.println("correlationData:"+correlationData);
            System.err.println("ack:"+b);
            if (!b){
                //日志记录，异常处理，补偿处理
                System.err.println("异常处理...");
            }else{
                System.out.println("后续正常操作");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback=new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.err.println("回调的信息");
        }
    };

    public void send(Object message)throws Exception{
        Map<String,Object> properties=new HashMap<>();
        properties.put("number","123");
        properties.put("time",new SimpleDateFormat().format(new Date()));
        MessageHeaders mhs=new MessageHeaders(properties);
        org.springframework.messaging.Message msg=MessageBuilder.createMessage(message,mhs);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData=new CorrelationData("1234567890");
        rabbitTemplate.convertAndSend("jhj01","abc",msg,correlationData);


    }

}
