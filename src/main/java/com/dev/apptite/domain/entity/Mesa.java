package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMesa;

    @NotNull(message = "O número é obrigatório")
    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "uuid",nullable = false,unique = true,updatable = false)
    private String uuid;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_restaurante",nullable = false)
    private Restaurante restaurante;

    @ToString.Exclude
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Cliente> clientes;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
