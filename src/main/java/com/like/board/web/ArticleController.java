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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.board.domain.model.Article;
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.domain.repository.dto.ArticleReqeustDTO;
import com.like.board.service.BoardService;
import com.like.common.web.util.WebControllerUtil;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@Controller
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@Resource(name = "fileService")
	private FileService fileService;
	
	private boolean validId(Long id) {				
		return ( id != null && id > 0 ) ? true : false;
	}
	
	@RequestMapping(value={"/grw/boards/articles2"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getArticleList(@RequestParam(value="fkBoard", required=true) Long fkBoard,			
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="contents", required=false) String contents) {
			
		ResponseEntity<?> result = null;			
		
		List<ArticleListDTO> list;
		Map<String,Object> map = new HashMap<>();		
		map.put("pkBoard", fkBoard);
				
		list = boardService.getArticleList(map);
						
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 			
				
		return result;
	}
	
	
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getArticleList(@RequestParam(value="fkBoard", required=true) Long fkBoard,
			@RequestParam(value="id", required=false) Long id,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="contents", required=false) String contents) {
			
		ResponseEntity<?> result = null;			
		
		List<Article> list;
		
		if ( validId(id) ) {
			list = new ArrayList<>(); 			
			list.add(boardService.getAritlce(id));
		} else {
			list = boardService.getAritlceList(fkBoard,title,contents);
			log.info(title);
		}
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 			
				
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveArticle(@RequestBody List<Article> articleList,
			@RequestParam(value="fkBoard", required=true) Long fkBoard) {
			
		ResponseEntity<?> result = null;			
								
		for (Article article : articleList ) {			
			boardService.saveArticle(article, fkBoard);
		}
		
		/*ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8090/file", 
																			request, 
																			responseType, 
																			uriVariables);*/
						
		result = WebControllerUtil.getResponse(null, 
				articleList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles2"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleFile(@ModelAttribute ArticleReqeustDTO articleDTO
			//@RequestBody ArticleReqeustDTO article,
			//@RequestParam(value="file", required=false)MultipartFile file,
			//@RequestParam(value="fkBoard", required=true) Long fkBoard
			) {
			
		ResponseEntity<?> result = null;			
		
		log.info(articleDTO.toString());
		
		Article article = new Article(articleDTO.getTitle(), articleDTO.getContents());
		FileInfo file;
		try {
			file = fileService.uploadFile(articleDTO.getFile(), "test", "board");
			article.addAttachedFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}					
		boardService.saveArticle(article, articleDTO.getFkBoard());
			
		/*for (Article article : articleList ) {			
			boardService.saveArticle(article, fkBoard);
		}*/
		
		/*ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8090/file", 
																			request, 
																			responseType, 
																			uriVariables);*/
						
		result = WebControllerUtil.getResponse(null, 
				0,//articleList.size(), 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	
	@RequestMapping(value={"/grw/boards/articles"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delArticle(@RequestParam(value="id", required=true) Long id) {
			
		ResponseEntity<?> result = null;			
												
		boardService.deleteBoard(id);
						
		result = WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
	
	@RequestMapping(value={"/grw/boards/articles/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="userid", required=true) String userId) {
			
		ResponseEntity<?> result = null;			
		List<Article> list = new ArrayList<>();
				
		Article aritlce = boardService.updateArticleHitCnt(id, userId);
		
		list.add(aritlce);
						
		result = WebControllerUtil.getResponse(list, 
				1, 
				true, 
				String.format("%d건 업데이트 하였습니다.", 1), 
				HttpStatus.OK); 					
		
		return result;
	}
}
