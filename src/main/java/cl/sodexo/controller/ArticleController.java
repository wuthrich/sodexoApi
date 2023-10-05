package cl.sodexo.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.sodexo.entity.Article;
import cl.sodexo.entity.ArticleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ArticleController {

	private final ArticleRepository repository;
	
	ArticleController(ArticleRepository repository){
		this.repository = repository;
		
	}
	
  @Operation(summary = "Borra un artículo por id")
  @DeleteMapping("/article/{id}")
  void deleteArticle(@PathVariable Long id) {
    repository.deleteById(id);
  }
  
  @Operation(summary = "Inserta artículo")
  @PostMapping("/article")
  Article newArticle(@RequestBody Article nuevo) {
	  OffsetDateTime now = OffsetDateTime.now();	  
	  nuevo.setAdding(now);	  
    return repository.save(nuevo);
  }
  
  @Operation(summary = "Devuelve los primeros 10 artículos existentes")
  @GetMapping("/article")
  Page<Article> firstPage() {
	  Pageable firstPage = PageRequest.of(0, 10, Sort.by("published").descending());
	  return repository.findAll(firstPage);
  }
  
  @Operation(summary = "Devuelve 10 artículos segun los parametros de busqueda")
  @GetMapping("/article/")
  Page<Article> whatPage(
		  @Parameter(description = "Pagina a buscar") 
		  @RequestParam(required = true) int pagina
		  ,@Parameter(description = "Campo del artículo por el cual ordenar, por ejemplo 'published'")  
		  @RequestParam(required = false, defaultValue = "") String orden
		  ,@Parameter(description = "Texto por cual buscar en los titulos") 
		  @RequestParam(required = false, defaultValue = "") String search) {	  
	  
	  Sort ordenado;
	  
	  if("published".equalsIgnoreCase(orden)) {
		  ordenado = Sort.by("published").ascending();
	  }else {
		  ordenado = Sort.by("published").descending();
	  }
	  
	  Pageable page = PageRequest.of(pagina, 10, ordenado);	
	  
	  Page<Article> salida;
	  
	  if(search.equalsIgnoreCase("")) {
		  salida = repository.findAll(page);
	  }else {
		  salida = repository.findByTitleContainingIgnoreCase(search,page);
	  }
	  
	  return salida;	  
  }
	  
}
