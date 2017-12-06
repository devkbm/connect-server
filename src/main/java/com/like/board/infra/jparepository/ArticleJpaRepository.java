package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.dto.ArticleListDTO;
import com.like.board.infra.jparepository.springdata.JpaArticle;
import com.like.board.infra.jparepository.springdata.JpaArticleCheck;
import com.like.board.infra.jparepository.springdata.JpaBoard;
import com.like.file.domain.model.FileInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.like.board.domain.model.*;

@Repository
public class ArticleJpaRepository implements ArticleRepository {
				
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaBoard jpaBoard;
	
	@Autowired
	private JpaArticle jpaArticle;
	
	@Autowired
	private JpaArticleCheck jpaArticleCheck;
			
	private final QArticle qArticle = QArticle.article;
	private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;	

	public ArticleCheck findFkarticleAndSysuser(Long fkarticle, String userId) {
									
		return queryFactory.selectFrom(qArticleCheck)
					.where(qArticleCheck.sysUser.eq(userId)
					.and(qArticleCheck.article.pkArticle.eq(fkarticle)))
					.fetchOne();				
	}
		
	public Article getArticle(Long id) {		
		return jpaArticle.findOne(id);
		
	}
		
	public List<Article> getArticleList(Long fkBoard) { 
					
		return queryFactory.selectFrom(qArticle)
							.where(qArticle.board.pkBoard.eq(fkBoard))
							.fetch();				
	}
	
	public List<Article> getArticleList(Long fkBoard, String likeTitle, String likeContents) { 
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (StringUtils.hasText(likeTitle)) {
			builder.and(qArticle.title.like("%"+likeTitle+"%"));
		}
		
		if (StringUtils.hasText(likeContents)) {
			builder.and(qArticle.contents.like("%"+likeContents+"%"));
		}
		
		return queryFactory.selectFrom(qArticle)
							.where(qArticle.board.pkBoard.eq(fkBoard)
								  .and(builder))							
							.fetch();				
	}
	
	public List<ArticleListDTO> getArticleListDTO(Long fkBoard) {
		
		/*JPAQuery<BoardHierarchyDTO> query = queryFactory
				.select(Projections.constructor(ArticleListDTO.class
											, qBoard.pkBoard, qBoard.boardNm, leaf
											, qBoard.boardNm, qBoard.boardNm, parent.pkBoard))
				.from(qArticle)
				.leftJoin(qBoard.parent, parent);
		*/
		return null;
	}

	public Long saveArticle(Article article, Long fkBoard) {		
		
		Board board = jpaBoard.findOne(fkBoard);
		
		if ( !article.hasParentArticle() ){
			article.setParentRoot();
		}
		
		if (article.getSeq() == null ) {							
			article.setSeq(getArticleNextSeq(fkBoard));
		} else if (article.getSeq() == 0 ) {
			article.setSeq(1);
		}
						
		article.setBoard(board);
						
		Article savedArticle = jpaArticle.saveAndFlush(article);
							
		return savedArticle.getPkArticle();
	}
	
	public void deleteArticle(Article article) {		
		
		jpaArticle.delete(article);
	}
	
	public void deleteArticle(Long id) {				
		jpaArticle.delete(id);
	}
	
	public void deleteArticle(List<Article> articleList) {
		jpaArticle.delete(articleList);
	}
	
	private void deleteArticleCheck(Long fkArticle) {
		queryFactory
			.delete(qArticleCheck)
			.where(qArticleCheck.article.pkArticle.eq(fkArticle))
			.execute();					
	}
	
	public ArticleCheck getArticleCheck(Long fkarticle, String userId) {				
					
		return queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.sysUser.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
	
	public Integer getArticleNextSeq(Long pkboard) {
		
		Expression<Integer> seq = new CaseBuilder()				
										.when(qArticle.seq.max().isNull()).then(0)
										.otherwise(qArticle.seq.max()).as("seq");
		
		Integer rtn = queryFactory
						.select(seq)
			  			.from(qArticle)
			  			.where(qArticle.board.pkBoard.eq(pkboard))				  
			  			.fetchOne();					
		
		return rtn + 1;		
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {				
				
		Article article = jpaArticle.findOne(pkAriticle);		
				
		article.updateHitCnt();
		
		jpaArticle.save(article);
				
		ArticleCheck check = queryFactory
									.selectFrom(qArticleCheck)
									.where(qArticleCheck.article.pkArticle.eq(pkAriticle)
									  .and(qArticleCheck.sysUser.eq(userId)))
									.fetchOne();
		
		if ( check == null) {
			check = new ArticleCheck();				
			check.setArticle(article);			
		} else {
			check.updateHitCnt();
		}
		
		jpaArticleCheck.save(check);
			 		
		return article;		
	}
	
	public List<FileInfo> getFileInfoList(Long pkArticle) {
		return jpaArticle.findOne(pkArticle).getFiles();
	}
	
}