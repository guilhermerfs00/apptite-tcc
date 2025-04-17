package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatusService {

    private final TwilioService twilioService;

    private static final Map<StatusPedidoEnum, String> MENSAGENS_STATUS = new EnumMap<>(StatusPedidoEnum.class);

    static {
        MENSAGENS_STATUS.put(StatusPedidoEnum.PEDIDO_REALIZADO, "Seu pedido foi realizado com sucesso! 🚀");
        MENSAGENS_STATUS.put(StatusPedidoEnum.PRODUZINDO_PEDIDO, "Estamos preparando seu pedido! 🍕");
        MENSAGENS_STATUS.put(StatusPedidoEnum.AGUARDANDO_RETIRADA, "Seu pedido está pronto para retirada! 🏃");
        MENSAGENS_STATUS.put(StatusPedidoEnum.AGUARDANDO_PAGAMENTO, "Seu pedido está aguardando pagamento. 💳");
        MENSAGENS_STATUS.put(StatusPedidoEnum.PROCESSANDO_PAGAMENTO, "Estamos processando seu pagamento. ⏳");
        MENSAGENS_STATUS.put(StatusPedidoEnum.PAGAMENTO_RECUSADO, "Seu pagamento foi recusado. Tente novamente! ❌");
        MENSAGENS_STATUS.put(StatusPedidoEnum.PAGAMENTO_CONCLUIDO, "Pagamento concluído! Obrigado. ✅");
    }

    public void processarStatus(PedidoDTO pedido, StatusPedidoEnum status) {
        pedido.setStatus(status);
        String mensagem = MENSAGENS_STATUS.get(status);
        if (mensagem != null) {
            enviarNotificacao(mensagem, pedido);
        }
    }

    private void enviarNotificacao(String mensagem, PedidoDTO pedido) {
        String celularCliente = pedido.getCliente().getCelular();
        twilioService.enviarMensagemWhatsApp(celularCliente, mensagem);
    }
}