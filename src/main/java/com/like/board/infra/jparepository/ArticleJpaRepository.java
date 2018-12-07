package com.like.board.infra.jparepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.like.board.domain.repository.ArticleRepository;
import com.like.board.dto.ArticleListDTO;
import com.like.board.dto.ArticleQueryDTO;
import com.like.board.dto.ArticleResponseDTO;
import com.like.board.infra.jparepository.springdata.JpaArticle;
import com.like.board.infra.jparepository.springdata.JpaArticleCheck;
import com.like.board.infra.jparepository.springdata.JpaBoard;
import com.like.file.domain.model.FileInfo;
import com.like.file.domain.model.QFileInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
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
	private final QFileInfo qFileInfo = QFileInfo.fileInfo;
	

	public ArticleCheck findFkarticleAndSysuser(Long fkarticle, String userId) {
									
		return queryFactory.selectFrom(qArticleCheck)
					.where(qArticleCheck.createdBy.eq(userId)
					.and(qArticleCheck.article.pkArticle.eq(fkarticle)))
					.fetchOne();				
	}
		
	public Article getArticle(Long id) {		
		return jpaArticle.findOne(id);
		
	}
			
	@Override
	public ArticleResponseDTO getArticleDTO(Long id) {

		JPAQuery<ArticleResponseDTO> query = queryFactory
				.select(Projections.constructor(ArticleResponseDTO.class, 
												qArticle._super.createdDt, qArticle._super.createdBy, qArticle._super.modifiedDt, qArticle._super.modifiedBy,
												qArticle.pkArticle, qArticle.ppkArticle, qArticle.title, qArticle.contents, 
												qArticle.pwd, qArticle.hitCount, qArticle.fromDate, qArticle.toDate,
												qArticle.seq, qArticle.depth, qArticle.board.pkBoard
												))
				.from(qArticle);					
		return query.fetchOne();
	}

	public List<Article> getArticleList(Long fkBoard) { 
					
		return queryFactory.select(qArticle)
							.from(qArticle)														
							.where(qArticle.board.pkBoard.eq(fkBoard))							
							.fetch();				
	}
	
	public List<Article> getArticleList(ArticleQueryDTO queryDTO) { 	
		
		return queryFactory.selectFrom(qArticle)
							//.leftJoin(qFileInfo)
							//.on(qArticle.pkArticle.eq(qFileInfo.pkFile))
							//.fetchJoin()
							.where(queryDTO.getBooleanBuilder())							
							.fetch();				
	}	

	public Long saveArticle(Article article) {		
				
		
		if ( !article.hasParentArticle() ){
			article.setParentRoot();
		}
		
		if (article.getSeq() == null ) {							
			article.setSeq(getArticleNextSeq(article.getBoard().getPkBoard()));
		} else if (article.getSeq() == 0 ) {
			article.setSeq(1);
		}
														
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
				.where(qArticleCheck.createdBy.eq(userId)
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
									  .and(qArticleCheck.createdBy.eq(userId)))
									.fetchOne();
		
		if ( check == null) {
			check = new ArticleCheck(article);				
		}				
		
		check.updateHitCnt();
				
		jpaArticleCheck.save(check);
			 		
		return article;		
	}
	
	public List<FileInfo> getFileInfoList(Long pkArticle) {
		return jpaArticle.findOne(pkArticle).getFiles();
	}
	
}