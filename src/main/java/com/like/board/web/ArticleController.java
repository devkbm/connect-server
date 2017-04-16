package com.like.board.web;

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
import org.springframework.web.multipart.MultipartFile;

import com.like.board.domain.model.Article;
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
}
