package br.gustavo.antonino.KanbanAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// POJO -> Plain Old Java Object
@Data // Gera getters, setters, toString, equals e hashCode
@NoArgsConstructor // Construtor padrão
@AllArgsConstructor // Construtor com todos os argumentos
@Entity
@Table(name = "frame")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
}
