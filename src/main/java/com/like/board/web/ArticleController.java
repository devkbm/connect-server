package com.like.board.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.board.domain.model.Article;
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.board.web.dto.ArticleSaveDTO;
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
	
	private boolean validId(Long id) {				
		return ( id != null && id > 0 ) ? true : false;
	}
		
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
		
	@GetMapping("/grw/boards/{id}/articles")
	public ResponseEntity<?> getArticleList(
			@PathVariable(value="id") Long id,					
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="contents", required=false) String contents) {
											
		Map<String,Object> map = new HashMap<>();		
		map.put("pkBoard", 	id);
		map.put("title", 	title);
		map.put("contents", contents);
				
		List<ArticleListDTO> list = boardQueryService.getArticleList(map);  							
				
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
		
	@RequestMapping(value={"/grw/boards/articles_temp"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getArticleList(@RequestParam(value="fkBoard", required=true) Long fkBoard,
			@RequestParam(value="id", required=false) Long id,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="contents", required=false) String contents) {						
		
		List<Article> list;
		
		if ( validId(id) ) {
			list = new ArrayList<>(); 			
			list.add(boardQueryService.getAritlce(id));
		} else {
			list = boardQueryService.getAritlceList(fkBoard,title,contents);
			log.info(title);
		}				
				
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/grw/boards/articles"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveArticle(@RequestBody List<Article> articleList,
			@RequestParam(value="fkBoard", required=true) Long fkBoard) {				
								
		for (Article article : articleList ) {			
			boardCommandService.saveArticle(article, fkBoard);
		}
		
		/*ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8090/file", 
																			request, 
																			responseType, 
																			uriVariables);*/							
		
		return WebControllerUtil.getResponse(null, 
				articleList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
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
	
	@RequestMapping(value={"/grw/boards/articles2"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleWithFile(@ModelAttribute ArticleSaveDTO articleDTO) {
														
		Article article = null;
		FileInfo file = null;
		
		/*if ( articleDTO.hasId() ) {
			article = boardQueryService.getAritlce(articleDTO.getId());			
			article.setArticleDTO(articleDTO);
		} else {
			article = new Article(articleDTO.getTitle(), articleDTO.getContents());
		}*/
							
		try {
			if (!articleDTO.getFile().isEmpty()) {
				file = fileService.uploadFile(articleDTO.getFile(), "test", "board");
				article.addAttachedFile(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}					
		boardCommandService.saveArticle(article, articleDTO.getFkBoard());											
		
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
}
