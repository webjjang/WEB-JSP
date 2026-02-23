package com.webjjang.board.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.util.db.DB;
import com.webjjang.util.page.PageObject;

//Main - BoardController - Board***Service - (BoardDAO) // BoardVO
// DAO - Data Access Object : DB처리 프로그램
public class BoardDAO extends DAO{
	
	// DB필요한 객체 - DAO 클래스를 상속받아서 해결
	
	// DB 연결 정보 -> com.webjjang.util.db.DB 이동
	
	// 1. 일반 게시판 리스트
	public List<BoardVO> list(PageObject pageObject) throws Exception{
		
		// System.out.println("BoardDAO.list()----------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		List<BoardVO> list = new ArrayList<>();
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		// 3-1. 원본 데이터 정렬해서 가져오기
		String sql = "select no, title, writer, to_char(writeDate, 'yyyy-mm-dd') writeDate, "
				+ " hit from board ";
		// 검색 처리를 한다. -> getTotalRow()의 검색 처리와 같다. -> 반복이 된다. 메서드를 만든다.
		sql += search(pageObject);
		
		sql += " order by no desc";
		// 3-2. 순서 번호를 붙인다.
		sql = "select rownum rnum, no, title, writer, writeDate, hit " 
			+ " from(" + sql + ")";
		// 3-3. page에 맞는 데이터만 가져온다.
		sql = "select rnum, no, title, writer, writeDate, hit "
			+ " from(" + sql + ") where rnum between ? and ?";
		
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		// 순서 번호의 변수 선언해서 사용한다.
		int idx = 1;
		//  검색 데이터 세팅을 한다. - ?가 생길 수도 있다.
		idx = searchDataSet(pstmt, idx, pageObject); // pstmt 데이터를 메서드 안에서 변경면 밖에서 변경된 상태 : 참조형변수 - 주소전달
		pstmt.setLong(idx++, pageObject.getStartRow());
		pstmt.setLong(idx++, pageObject.getEndRow());
		
		// 5. 실행 : select :executeQuery() -> rs, insert / update / delete :executeUpdate() -> Integer
		rs = pstmt.executeQuery();
		
		// 6. DB에서 가져온 데이터 채우기
		if(rs != null) {
			while(rs.next()) { // 데이터가 있는 만큼 반복 실행
				// 저장할 객체를 생성한다.
				BoardVO vo = new BoardVO();
				// 데이터를 저장한다.
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				// list에 담는다.
				list.add(vo);
			} // while() 끝
		} // if의 끝
		
		// 7. DB 닫기
		DB.close(con, pstmt, rs);
		
		return list;
	} // list()의 끝
	
	// 1-1. 일반 게시판 글의 개수
	public Long getTotalRow(PageObject pageObject) throws Exception{
		
		// System.out.println("BoardDAO.getTotalRow()----------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		Long totalRow = 0L;
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		String sql = "select count(*) from board ";
		
		// 검색 처리를 한다. -> list()의 검색 처리와 같다. -> 반복이 된다. 메서드를 만든다.
		sql += search(pageObject);
		
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		// - 검색 처리를 하면 데이터 세팅이 필요한다. ?가 생긴다.
		// 순서 번호의 변수 선언해서 사용한다.
		int idx = 1;
		// 검색 데이터 세팅을 한다. - ?가 생길 수도 있다. 
		idx = searchDataSet(pstmt, idx, pageObject); // pstmt 데이터 변경
		
		// 5. 실행 : select :executeQuery() -> rs, insert / update / delete :executeUpdate() -> Integer
		rs = pstmt.executeQuery();
		
		// 6. DB에서 가져온 데이터 채우기
		if(rs != null && rs.next()) {
			// 데이터를 저장한다.
			totalRow = rs.getLong(1);
		} // if의 끝
		
		// 7. DB 닫기
		DB.close(con, pstmt, rs);
		
		return totalRow;
	} // getTotalRow()의 끝
	
	
	// 1-2. 검색을 위한 메서드
	public String search(PageObject pageObject) {
		String sql = "";
		
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		//word가 비어있지 않으면 검색을 처리한다.
		if(word != null && word.length() != 0) {
			sql = " where 1=0 ";
			// key 에 t가 포함되어 있으면 제목에서 검색한다. ? 데이터 세팅할 때 데이터 앞뒤에 %를 붙여서 세팅해 준다.
			if(key.indexOf("t") >= 0)
				sql += " or title like ? ";
			// key 에 c가 포함되어 있으면 내용에서 검색한다.
			if(key.indexOf("c") >= 0)
				sql += " or content like ? ";
			// key 에 w가 포함되어 있으면 작성자에서 검색한다.
			if(key.indexOf("w") >= 0)
				sql += " or writer like ? ";
		}
		
		return sql;
	} // search()의 끝
	
	// 1-3. 검색 데이터 세팅을 위한 메서드
	public Integer searchDataSet(PreparedStatement pstmt, int idx, PageObject pageObject)
	 throws Exception{
		
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		
		//word가 비어있지 않으면 검색을 위한 데이터 세팅 처리한다.
		if(word != null && word.length() != 0) {
			// key 에 t가 포함되어 있으면 제목에서 검색한다. ? 데이터 세팅할 때 데이터 앞뒤에 %를 붙여서 세팅해 준다.
			if(key.indexOf("t") >= 0)
				pstmt.setString(idx++, "%" + word + "%");
			// key 에 c가 포함되어 있으면 내용에서 검색한다.
			if(key.indexOf("c") >= 0)
				pstmt.setString(idx++, "%" + word + "%");
			// key 에 w가 포함되어 있으면 작성자에서 검색한다.
			if(key.indexOf("w") >= 0)
				pstmt.setString(idx++, "%" + word + "%");
		}
		
		return idx;
	} // searchDataSet()의 끝
	
	
	// 2-0. 조회수 1 증가
	public Integer increase(Long no) throws Exception{
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 쿼리 
		String sql = "update board set hit = hit + 1 where no = ?";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행 + // 6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
	// 2. 일반 게시판 글보기 - 글번호, 제목, 내용, 작성자, 작성일, 조회수
	public BoardVO view(Long no) throws Exception {
		BoardVO vo = null;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL
		String sql = "select no, title, content, writer, writeDate, hit from board where no = ?";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 데이터 저장
		if(rs != null && rs.next()) {
			vo = new BoardVO();
			vo.setNo(rs.getLong("no"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setWriter(rs.getString("writer"));
			vo.setWriteDate(rs.getString("writeDate"));
			vo.setHit(rs.getLong("hit"));
		} // if 의 끝
		// 7. 닫기
		DB.close(con, pstmt, rs);
		
		return vo;
	} // view()의 끝
	
	// 3. 일반 게시판 글등록
	public Integer write(BoardVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "insert into board(no,title, content, writer, pw) values(board_seq.nextval,?,?,?,?)";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriter());
		pstmt.setString(4, vo.getPw());
		// 5. 실행 //6. 데이터 저장
		// select - executeQuery() : rs, insert, update, delete - executeUpdate() : Integer
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	// 4. 일반 게시판 글수정
	public Integer update(BoardVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "update board set title = ?, content = ?, writer = ? "
				+ " where no = ? and pw = ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriter());
		pstmt.setLong(4, vo.getNo());
		pstmt.setString(5, vo.getPw());
		// 5. 실행 & //6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
	// 5. 일반 게시판 글삭제
	public Integer delete(BoardVO vo) throws Exception{
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "delete from board where no = ? and pw = ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, vo.getNo());
		pstmt.setString(2, vo.getPw());
		// 5. 실행 //6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}

} // 클래스의 끝
