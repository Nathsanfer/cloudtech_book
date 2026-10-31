package com.ct_books.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 

public class Livro {
    private Long id;

    private String titulo;

    private String autor;

    private String editora;

    private String ano;

    private String categoria;

    private Status status = Status.DISPONIVEL;
}