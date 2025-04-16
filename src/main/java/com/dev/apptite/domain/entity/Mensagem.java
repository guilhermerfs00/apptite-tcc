package com.dev.apptite.domain.entity;

import com.dev.apptite.domain.enums.TipoMensagemEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    @Enumerated(EnumType.STRING)
    private TipoMensagemEnum tipoMensagem;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    private String conteudo;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
