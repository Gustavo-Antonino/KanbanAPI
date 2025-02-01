package br.gustavo.antonino.KanbanAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// POJO -> Plain Old Java Object
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Construtor padrão
@AllArgsConstructor // Construtor com todos os argumentos
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ColumnStatus status;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}