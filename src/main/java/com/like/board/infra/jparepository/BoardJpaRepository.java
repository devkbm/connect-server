package com.like.board.infra.jparepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.BoardRepository;
import com.like.board.dto.BoardDTO;
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
		
	public Board getBoard(Long id) {
		return jpaBoard.findOne(id);
	}
	
	public List<Board> getBoardList(BoardDTO.QueryCondition condition) {		
		return queryFactory
					.selectFrom(qBoard)
					.where(condition.getBooleanBuilder())
					.fetch(); 		
	}	
	
	public List<BoardDTO.BoardHierarchy> getBoardHierarchy() {
				
		List<BoardDTO.BoardHierarchy> rootList = getBoardHierarchyRootList();
		
		List<BoardDTO.BoardHierarchy> rtn =  setLinkBoardHierarchy(rootList);
		
		return rtn;
	}
	
	private List<BoardDTO.BoardHierarchy> setLinkBoardHierarchy(List<BoardDTO.BoardHierarchy> list) {
		List<BoardDTO.BoardHierarchy> children = null;
		
		for ( BoardDTO.BoardHierarchy dto : list) {
			if (dto.isLeaf()) {	// leaf 노드이면 다음 리스트 검색
				continue;
			} else {
				children = getBoardHierarchyChildrenList(dto.getKey());
				dto.setChildren(children);
				
				setLinkBoardHierarchy(children);
			}
		}
		
		return list;
	}
	
	private List<BoardDTO.BoardHierarchy> getBoardHierarchyRootList() {									
		
		Expression<Boolean> isLeaf = new CaseBuilder()
										.when(qBoard.ppkBoard.isNotNull()).then(true)
										.otherwise(false).as("leaf");
		
		JPAQuery<BoardDTO.BoardHierarchy> query = queryFactory
				.select(Projections.constructor(BoardDTO.BoardHierarchy.class
						, qBoard.pkBoard, qBoard.ppkBoard, qBoard.boardType
						, qBoard.boardName, qBoard.boardDescription, qBoard.fromDate
						, qBoard.toDate, qBoard.articleCount, qBoard.sequence, isLeaf))
				.from(qBoard)
				.where(qBoard.ppkBoard.isNull());
													
						
		return query.fetch();	
	}
	
	private List<BoardDTO.BoardHierarchy> getBoardHierarchyChildrenList(Long parentPkBoard) {
		Expression<Boolean> isLeaf = new CaseBuilder()
				.when(qBoard.ppkBoard.isNotNull()).then(true)
				.otherwise(false).as("leaf");

		JPAQuery<BoardDTO.BoardHierarchy> query = queryFactory
				.select(Projections.constructor(BoardDTO.BoardHierarchy.class
						, qBoard.pkBoard, qBoard.ppkBoard, qBoard.boardType
						, qBoard.boardName, qBoard.boardDescription, qBoard.fromDate
						, qBoard.toDate, qBoard.articleCount, qBoard.sequence, isLeaf))
				.from(qBoard)
				.where(qBoard.ppkBoard.eq(parentPkBoard));								
		
		return query.fetch();
		
	}
	
	public void saveBoard(Board board) {
		if ( !board.hasParentBoard() ) {
			board.setParentRoot();
		}
		jpaBoard.save(board);
	}
			
	public void deleteBoard(Long pkBoard) {
		jpaBoard.delete(pkBoard);			
	}
	
	public void deleteBoard(Board board) {
		jpaBoard.delete(board);
	}
	
	
}