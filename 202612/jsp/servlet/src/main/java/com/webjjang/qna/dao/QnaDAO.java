package com.webjjang.qna.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.qna.vo.QnaVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.util.db.DB;

//Main - QnaController - Qna***Service - (QnaDAO) // QnaVO
// DAO - Data Access Object : DB처리 프로그램
public class QnaDAO extends DAO{
	
	// DB필요한 객체 - DAO 클래스를 상속받아서 해결
	
	// DB 연결 정보 -> com.webjjang.util.db.DB 이동
	
	// 1. 질문답변 리스트
	public List<QnaVO> list() throws Exception{
		
		// System.out.println("QnaDAO.list()----------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		List<QnaVO> list = new ArrayList<>();
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		String sql = "select q.no, q.title, q.id, m.name, q.writeDate, q.hit, q.levNo "
				+ " from qna q, member m "
				+ " where q.id = m.id "
				+ " order by q.refNo desc, q.ordNo";
		
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		
		// 5. 실행 : select :executeQuery() -> rs, insert / update / delete :executeUpdate() -> Integer
		rs = pstmt.executeQuery();
		
		// 6. DB에서 가져온 데이터 채우기
		if(rs != null) {
			while(rs.next()) { // 데이터가 있는 만큼 반복 실행
				// 저장할 객체를 생성한다.
				QnaVO vo = new QnaVO();
				// 데이터를 저장한다.
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("Id"));
				vo.setName(rs.getString("Name"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				vo.setLevNo(rs.getLong("levNo"));
				// list에 담는다.
				list.add(vo);
			} // while() 끝
		} // if의 끝
		
		// 7. DB 닫기
		DB.close(con, pstmt, rs);
		
		return list;
	} // list()의 끝
	
	// 2-0. 조회수 1 증가
	public Integer increase(Long no) throws Exception{
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 쿼리 
		String sql = "update qna set hit = hit + 1 where no = ?";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행 + // 6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	} // increase()의 끝
	
	// 2. 질문답변 글보기 - 글번호, 제목, 내용, 작성자, 작성일, 조회수
	public QnaVO view(Long no) throws Exception {
		QnaVO vo = null;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL
		String sql = "select q.no, q.title, q.content, q.id, m.name,"
				+ " to_char(q.writeDate, 'yyyy-mm-dd') writeDate, "
				+ "      q.hit, q.refNo, q.ordNo, q.levNo "
				+ " from qna q, member m "
				+ " where (q.no = ?) and (q.id = m.id)";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 데이터 저장
		if(rs != null && rs.next()) {
			vo = new QnaVO();
			vo.setNo(rs.getLong("no"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setId(rs.getString("id"));
			vo.setName(rs.getString("name"));
			vo.setWriteDate(rs.getString("writeDate"));
			vo.setHit(rs.getLong("hit"));
			vo.setRefNo(rs.getLong("refNo")); // 참조하고 있는 원본 질문
			vo.setOrdNo(rs.getLong("ordNo")); // 같은 질문의 순서
			vo.setLevNo(rs.getLong("levNo")); // 들여쓰기
		} // if 의 끝
		// 7. 닫기
		DB.close(con, pstmt, rs);
		
		return vo;
	} // view()의 끝
	
	// 3-1. 질문 등록
	public Integer question(QnaVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
				+ " values(qna_seq.nextval, ?, ?, ?, qna_seq.nextval, 1, 0, null)";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getId());
		// 5. 실행 //6. 데이터 저장
		// select - executeQuery() : rs, insert, update, delete - executeUpdate() : Integer
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	} // question()의 끝
	
	// 3-2. 답변 등록
	public Integer answer(QnaVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "insert into qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
				+ " values(qna_seq.nextval,?,?,?,?,?,?,?)";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getId());
		pstmt.setLong(4, vo.getRefNo());
		pstmt.setLong(5, vo.getOrdNo());
		pstmt.setLong(6, vo.getLevNo());
		pstmt.setLong(7, vo.getParentNo());
		// 5. 실행 //6. 데이터 저장
		// select - executeQuery() : rs, insert, update, delete - executeUpdate() : Integer
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	} // answer()의 끝
	
	// 3-3. 답변 등록 전 같은 관련번호의 순서번호와 같거나 큰 번호 + 1 시킨다.
	public Integer increaseOrd(QnaVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "update qna set ordNo = ordNo + 1 where refNo = ? and ordNo >= ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, vo.getRefNo());
		pstmt.setLong(2, vo.getOrdNo());
		// 5. 실행 //6. 데이터 저장
		// select - executeQuery() : rs, insert, update, delete - executeUpdate() : Integer
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
	// 4. 질문답변 글수정
	public Integer update(QnaVO vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "update qna set title = ?, content = ? "
				+ " where no = ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getNo());
		// 5. 실행 & //6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
	// 5. 질문답변 글삭제
	public Integer delete(long no) throws Exception{
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "delete from qna where no = ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행 //6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}

} // 클래스의 끝
