package com.estsoft.springproject.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.service.BlogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name = "Blog 컨트롤러")
public class BlogController {
	private final BlogService service;

	public BlogController(BlogService service) {
		this.service = service;
	}

	// RequestMapping (특정 url   POST /articles)
	// 입력
	@PostMapping("/api/articles")
	@Operation(summary = "글 작성", description = "article 작성")
	@Parameter(name = "id", description = "ID", example = "1")
	public ResponseEntity<ArticleResponse> writeArticle(@RequestBody AddArticleRequest request) {
		//System.out.println(request.getTitle());
		//System.out.println(request.getContent());
		//
		// // logging level
		// // trace, debug, info, warn, error
		//log.warn("{}, {}", request.getTitle(), request.getContent());

		//service.saveArticle(request);

		Article article = service.saveArticle(request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new ArticleResponse(article));
	}

	// RequestMapping
	// 조회
	@GetMapping("/api/articles")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "100", description = "요청에 성공했습니다.",
			content = @Content(mediaType = "application/json"))
	})
	@Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
	@Parameter(name = "id", description = "블로그 글 ID", example = "45")
	public ResponseEntity<List<ArticleResponse>> findAll() {
		// Article -> List<ArticlesResponse>
		List<ArticleResponse> articleResponses = service.findAll().stream()
			.map(Article::convert)
			.toList();
		return ResponseEntity.ok(articleResponses);
	}

	@GetMapping("/api/articles/{id}")
	@Operation(summary = "블로그 id 목록찾기", description = "블로그 id 선택하여 내용 보기")
	@Parameter(name = "id", description = "블로그id", example = "2")
	public ResponseEntity<ArticleResponse> findById(@PathVariable Long id){
		Article article = service.findBy(id);
		// Article -> ArticleResponse
		return ResponseEntity.ok(new ArticleResponse(article));
	}

	// 게시물 삭제
	@DeleteMapping("/api/articles/{id}")
	@Operation(summary = "게시물 삭제", description = "id선택하여 게시물 삭제")
	@Parameter(name = "id", description = "블로그id", example = "3")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		service.deleteBy(id);
		return ResponseEntity.ok().build();
	}

	// article/{id} 수정 API body
	@PutMapping("/api/articles/{id}")
	public ResponseEntity<ArticleResponse> updateById(@PathVariable Long id,
														@RequestBody UpdateArticleRequest request){
		Article updateArticle = service.update(id, request);
		return ResponseEntity.ok(updateArticle.convert());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(e.getMessage());
	}
}