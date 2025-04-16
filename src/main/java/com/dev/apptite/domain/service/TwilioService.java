package com.dev.apptite.domain.service;

import com.dev.apptite.domain.config.TwilioConfig;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TwilioService {

    private final TwilioConfig twilioConfig;

    public void enviarMensagemWhatsApp(String telefone, String corpoDaMensagem) {
        try {
            var to = new PhoneNumber("+55" + telefone);
            var from = new PhoneNumber(twilioConfig.getNumber());
            var messageCreator = Message.creator(to, from, corpoDaMensagem);
            messageCreator.create();

        } catch (ApiException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}