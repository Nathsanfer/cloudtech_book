package com.ct_books.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.ct_books.api.controller.LivroController;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebMvcTest(LivroController.class)
class ApiApplicationTests {

	@Autowired 
	private MockMvc mockMvc;

	// Teste 1 - Verifica documentação 

	@Test 
	void testeLivroDocumentacao() throws Exception {

		mockMvc.perform(get("/livros/documentacao"))
			.andExpect(status().isOk())
			.andExpect(content().string("SEGUE A DOCUMENTAÇÂO DAS ROTAS: \n /livros -> listá-los \n /livros/cadastrar -> registrá-los \n /livros/alterar -> atualizá-los \n /livros/id -> buscar por id ou deletar"));
	}

	// Teste 2 - Listar livros

	@Test
	void testeListarLivros() throws Exception {

		mockMvc.perform(get("/livros"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].titulo").value("O Beijo da Neve"))
			.andExpect(jsonPath("$[1].titulo").value("Sapatilhas de Gelo"));
	}

	// Teste 3 - Buscar livro por Id

	@Test
	void testeBuscarLivroId() throws Exception {

		mockMvc.perform(get("/livros/2"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(2))
			.andExpect(jsonPath("$.titulo").value("Sapatilhas de Gelo"))
			.andExpect(jsonPath("$.autor").value("Babi A. Sette"))
			.andExpect(jsonPath("$.editora").value("Verus"))
			.andExpect(jsonPath("$.ano").value("2023"))
			.andExpect(jsonPath("$.categoria").value("Romance"))
			.andExpect(jsonPath("$.status").value("DISPONIVEL"));

	}

	// Teste 4 - Cadastrar livro

	@Test 
	void testeCadastrarLivro() throws Exception {

		String livro = """
			{
				"id": 3,
				"titulo": "Coração de Gelo",
				"autor": "Babi A. Sette",
				"editora": "Verus",
				"ano": "2025",
				"categoria": "Romantasia",
				"status": "DISPONIVEL"
			}
		""";

		mockMvc.perform(post("/livros/cadastrar")
			.contentType("application/json")
			.content(livro))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.titulo").value("Coração de Gelo"))
			.andExpect(jsonPath("$.autor").value("Babi A. Sette"))
			.andExpect(jsonPath("$.editora").value("Verus"))
			.andExpect(jsonPath("$.ano").value("2025"))
			.andExpect(jsonPath("$.categoria").value("Romantasia"))
			.andExpect(jsonPath("$.status").value("DISPONIVEL"));
	}

	// Teste 5 - Atualizar livro

	@Test
	void testeAtualizarLivro() throws Exception {

		String livroAtualizado = """
			{
				"titulo": "Sapatilhas de Gelo - Edição Atualizada",
				"autor": "Babi A. Sette",
				"editora": "Verus",
				"ano": "2025",
				"categoria": "Romantasia",
				"status": "INDISPONIVEL"
			}
		""";

		mockMvc.perform(put("/livros/alterar/2")
			.contentType("application/json")
			.content(livroAtualizado))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(2))
			.andExpect(jsonPath("$.titulo").value("Sapatilhas de Gelo - Edição Atualizada"))
			.andExpect(jsonPath("$.autor").value("Babi A. Sette"))
			.andExpect(jsonPath("$.editora").value("Verus"))
			.andExpect(jsonPath("$.ano").value("2025"))
			.andExpect(jsonPath("$.categoria").value("Romantasia"))
			.andExpect(jsonPath("$.status").value("INDISPONIVEL"));
	}


	// Teste 6 - Verificar Inexistência 

	@Test 
	void testeLivroInexistente() throws Exception{

		mockMvc.perform(get("/livros/99"))
			.andExpect(status().isNotFound());
	}
}
