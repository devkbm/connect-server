package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.ArticleRepository;
import com.like.board.dto.ArticleDTO;
import com.like.board.infra.jparepository.springdata.JpaArticle;
import com.like.board.infra.jparepository.springdata.JpaArticleCheck;
import com.like.board.infra.jparepository.springdata.JpaAttachedFile;
import com.like.file.domain.model.FileInfo;
import com.like.file.domain.model.QFileInfo;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.like.board.domain.model.*;

@Repository
public class ArticleJpaRepository implements ArticleRepository {
				
	@Autowired
	private JPAQueryFactory  queryFactory;	
	
	@Autowired
	private JpaArticle jpaArticle;
	
	@Autowired
	private JpaArticleCheck jpaArticleCheck;
	
	@Autowired
	private JpaAttachedFile jpaAttachedFile;
			
	private final QArticle qArticle = QArticle.article;
	private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;
	private final QFileInfo qFileInfo = QFileInfo.fileInfo;
	private final QAttachedFile qAttachedFile = QAttachedFile.attachedFile;

	public ArticleCheck findFkarticleAndSysuser(Long fkarticle, String userId) {
									
		return queryFactory.selectFrom(qArticleCheck)
					.where(qArticleCheck.createdBy.eq(userId)
					.and(qArticleCheck.article.pkArticle.eq(fkarticle)))
					.fetchOne();				
	}
		
	public Article getArticle(Long id) {		
		return jpaArticle.findOne(id);
		
	}
			

	public List<Article> getArticleList(Long fkBoard) { 			
				
		return queryFactory.select(qArticle)
							.from(qArticle)	
							.leftJoin(qArticle.files, qAttachedFile)
							.fetchJoin()
							.where(qArticle.board.pkBoard.eq(fkBoard))							
							.fetch();				
	}
	
	public List<Article> getArticleList(ArticleDTO.QueryCondition condition) { 	
		
		return queryFactory.select(qArticle).distinct()
		  				   .from(qArticle)		  				   
		  				   .leftJoin(qArticle.files, qAttachedFile)
		  				   .fetchJoin()		  				   
		  				   .leftJoin(qAttachedFile.fileInfo, qFileInfo)
		  				   .fetchJoin()
		  				   .where(condition.getBooleanBuilder())
		  				   .fetch();
		
		//return (List<Article>) jpaArticle.findAll(queryDTO.getBooleanBuilder());		
	}	

	private void setArticleSequence(Article article) {
		if (article.getSeq() == null ) {							
			article.setSeq(getArticleNextSeq(article.getBoard().getPkBoard()));
		} else if (article.getSeq() == 0 ) {
			article.setSeq(1);
		}
	}
	
	public Long saveArticle(Article article) {		
								
		setArticleSequence(article);			
														
		Article savedArticle = jpaArticle.saveAndFlush(article);
				
		for (AttachedFile file: article.getFiles()) {
			file.setId();
		}
			
		jpaAttachedFile.save(article.getFiles());
		
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
		
		Article article = jpaArticle.findOne(pkArticle);
		
		return article.getAttachedFileInfoList();
	}
	
	public void saveAttachedFile(AttachedFile attachedFile) {
		jpaAttachedFile.save(attachedFile);
	}
	
}