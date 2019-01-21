package com.like.board.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.dto.ArticleDTO;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@Controller
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
	
	@Resource
	private BoardCommandService boardCommandService;
	
	@Resource
	private BoardQueryService boardQueryService;
	
	@Resource
	private FileService fileService;	
		
	@GetMapping("/grw/boards/articles/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(value="id") Long id) {						
		
		Article article = boardQueryService.getAritlce(id);		
				
		return WebControllerUtil.getResponse(article, 
				article == null ? 0 : 1, 
				article == null ? false : true, 
				String.format("%d 건 조회되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@DeleteMapping("/grw/boards/articles/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable(value="id") Long id) {				
		
		boardCommandService.deleteArticle(id);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@GetMapping("/grw/boards/articles")
	public ResponseEntity<?> getArticleList(ArticleDTO.QueryCondition condition) {
																	
		List<Article> list = boardQueryService.getAritlceList(condition);  							
				
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
				
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> deleteArticle(@RequestBody List<Article> articleList) {						
		
		boardCommandService.deleteArticle(articleList);									
		
		return WebControllerUtil.getResponse(null, 
				articleList.size(), 
				true, 
				String.format("%d 건 삭제되었습니다.", articleList.size()), 
				HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/grw/boards/articles"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleWithFile(ArticleDTO.ArticleSave dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}			
		
		Article article = convertArticleEntity(dto);															
						
		boardCommandService.saveArticle(article, dto.getFile());											
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	
	@RequestMapping(value={"/grw/boards/articles/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="userid", required=true) String userId) {								
				
		Article aritlce = boardCommandService.updateArticleHitCnt(id, userId);			
										
		return WebControllerUtil.getResponse(aritlce, 
				1, 
				true, 
				String.format("%d건 업데이트 하였습니다.", 1), 
				HttpStatus.OK);
	}
	
	/**
	 * DTO -> 게시글 엔티티로 변환한다
	 * @param dto
	 * @return 게시글 엔티티
	 */
	private Article convertArticleEntity(ArticleDTO.ArticleSave dto) {
				
		Article article = null;
		
		Board board = boardQueryService.getBoard(dto.getFkBoard());
		
		if ( dto.getPkArticle() == null ) {											
			
			article = Article.builder()
							 .board(board)
							 .title(dto.getTitle())
							 .contents(dto.getContents())
							 .fromDate(dto.getFromDate())
							 .toDate(dto.getToDate())							 
							 .build();
					
		} else {			
			article = boardQueryService.getAritlce(dto.getPkArticle());
			
			article.updateEntity(dto);
		}
		
		return article;		
	}
}
