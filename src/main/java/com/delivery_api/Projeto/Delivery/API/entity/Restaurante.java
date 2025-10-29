package com.delivery_api.Projeto.Delivery.API.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String categoria;
    private boolean ativo;
}
