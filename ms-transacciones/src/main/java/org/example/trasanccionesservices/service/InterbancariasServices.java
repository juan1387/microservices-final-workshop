package org.example.trasanccionesservices.service;

import org.example.trasanccionesservices.model.Transacciones;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class InterbancariasServices {
    private final RabbitTemplate rabbitTemplate;

    public InterbancariasServices(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;

    }

    public void programarTransaInterBancaria(Transacciones transacciones){
        rabbitTemplate.convertAndSend("trans-exchange","trans-routing-key",transacciones);
    }
}
