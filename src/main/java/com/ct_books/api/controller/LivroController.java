package com.ct_books.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ct_books.api.model.Livro;
import com.ct_books.api.model.Status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/livros")
public class LivroController {

    private List<Livro> livros = new ArrayList<>();

    public LivroController() {

        livros.add(
            new Livro(1L, "O Beijo da Neve", "Babi A. Sette", "Verus", "2023", "Romance", Status.DISPONIVEL)
        );

        livros.add(
            new Livro(2L, "Sapatilhas de Gelo", "Babi A. Sette", "Verus", "2023", "Romance", Status.DISPONIVEL)
        );
    }

    // GET - Inicialização 

    @GetMapping("/")
    public String home() {
        return "Bem-vindo à API de Livros!";
    }

    // GET - Listar todos os livros

    @GetMapping
    public List<Livro> listarLivros() {
        return livros;
    }
    
    // GET - Buscar livro por id

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable int id) {
        
        for (Livro livro : livros) {
            if (Objects.equals(livro.getId(), (long) id)) {
                return livro;
            }
        }

        return null;
    }

    // POST - Cadastrar livro 

    @PostMapping("/cadatrar")
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        
        if (livro.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O ID da máquina é obigatório"
            );
        }

        boolean idJaCadastrado = livros.stream()
            .anyMatch(item -> Objects.equals(item.getId(), livro.getId()));
    
    if (idJaCadastrado) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um livro com o ID " + livro.getId()
            );
        }

        livros.add(livro);
        return livro;
    }
    
}
