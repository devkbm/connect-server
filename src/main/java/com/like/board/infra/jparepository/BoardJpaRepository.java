package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.BoardRepository;
import com.like.board.domain.repository.dto.BoardHierarchyDTO;
import com.like.board.infra.jparepository.springdata.JpaBoard;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.like.board.domain.model.*;

@Repository
public class BoardJpaRepository implements BoardRepository {
				
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaBoard jpaBoard;
				
	private final QBoard qBoard = QBoard.board;		
	
	@Override
	public Board getBoard(Long id) {
		return jpaBoard.findOne(id);
	}

	@Override
	public List<Board> getBoardList() {		
		return jpaBoard.findAll(); 		
	}
	
	public List<Board> getBoardList(String likeBoardName) {		
									
		//query = (JPAQuery) super.getQuerydsl().applyPagination(pageable, query);
		
		return queryFactory.selectFrom(qBoard)
					.where(qBoard.boardNm.like(likeBoardName))
					.fetch();				
	}
	
	public List<BoardHierarchyDTO> getBoardHierarchy(Long parentId) {
				
		QBoard parent = new QBoard("parent");								
		
		Expression<String> leaf = new CaseBuilder()
										.when(qBoard.ppkBoard.isNull()).then("true")
										.otherwise("false").as("leaf");													
		
		JPAQuery<BoardHierarchyDTO> query = queryFactory
													.select(Projections.constructor(BoardHierarchyDTO.class
																				, parent.pkBoard, parent.boardNm, leaf
																				, parent.boardNm, parent.boardNm, parent.ppkBoard))
													.from(qBoard)
													.rightJoin(qBoard.parent, parent);
		
		/*JPAQuery<BoardHierarchyDTO> query = queryFactory
				.select(Projections.constructor(BoardHierarchyDTO.class
											, qBoard.pkBoard, qBoard.boardNm, leaf
											, qBoard.boardNm, qBoard.boardNm, parent.pkBoard))
				.from(qBoard)
				.rightJoin(qBoard.parent, parent);*/
													
		if ( parentId == null ) {
			query.where(parent.ppkBoard.isNull());
		} else {
			query.where(parent.ppkBoard.eq(parentId));
		}		
		
		return query.fetch();					
	}

	@Override
	public void saveBoard(Board board) {
		if ( !board.hasParentBoard() ) {
			board.setParentRoot();
		}
		jpaBoard.save(board);
	}
		
	@Override
	public void deleteBoard(Long pkBoard) {
		jpaBoard.delete(pkBoard);
	}
	
	
}