package com.like.board.web;

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

import com.like.board.domain.repository.dto.ArticleReqeustDTO;
import com.like.common.web.util.WebControllerUtil;

@Controller
public class ArticleController {
	
	private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
	
	@RequestMapping(value={"/grw/boards/articles2"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleFile(@ModelAttribute ArticleReqeustDTO article,
			//@RequestBody ArticleReqeustDTO article,
			@RequestParam(value="file", required=false)MultipartFile file,
			@RequestParam(value="fkBoard", required=true) Long fkBoard
			) {
			
		ResponseEntity<?> result = null;			
		
		log.info("111");		
		//log.info(article.toString());
		
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
