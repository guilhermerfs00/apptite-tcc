package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldNameConstants;
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
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public class Variacao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idVariacao;

        @NotBlank(message = "O nome é obrigatório")
        @Column(name = "nome", nullable = false)
        private String nome;

        @Min(value = 0)
        @Column(name = "preco", nullable = false)
        private Double preco;

        @ToString.Exclude
        @ManyToOne
        @JoinColumn(name = "item_id", nullable = false)
        private Item item;

        @ToString.Exclude
        @ManyToMany(mappedBy = "variacoes")
        private List<Pedido> pedidos;

        @CreatedDate
        @Column(name = "data_criacao")
        private LocalDateTime dataCriacao;

        @UpdateTimestamp
        @Column(name = "data_atualizacao")
        private LocalDateTime dataAtualizacao;
}