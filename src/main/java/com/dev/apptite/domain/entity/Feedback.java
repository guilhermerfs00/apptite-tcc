package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ToString.Exclude
    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Mensagem> mensagens;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
