package com.webjjang.notice.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.main.dao.DAO;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.db.DB;
import com.webjjang.util.page.PageObject;

public class NoticeDAO extends DAO {

	// 1. list() - 복사 -> 수정
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
		
		// System.out.println("BoardDAO.list()----------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		List<NoticeVO> list = new ArrayList<>();
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		// 3-1. 원본 데이터 정렬해서 가져오기
		String sql = "select no, title, "
				+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
				+ " to_char(endDate, 'yyyy-mm-dd') endDate, "
				+ " to_char(updateDate, 'yyyy-mm-dd') updateDate "
				+ " from notice ";
		// period 처리
		// pre : 현재공지, old : 지난공지 , res : 예약공지, all : 전체공지 
		sql += period(pageObject.getPeriod());
		
		sql += " order by updateDate desc"; // 최근 수정 날짜로 정렬하자.
		// 3-2. 순서 번호를 붙인다.
		sql = "select rownum rnum, no, title, startDate, endDate, updateDate " 
			+ " from(" + sql + ")";
		// 3-3. page에 맞는 데이터만 가져온다.
		sql = "select rnum, no, title, startDate, endDate, updateDate "
			+ " from(" + sql + ") where rnum between ? and ?";
		
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		// 1page의 정보를 하드 코딩했다.
		pstmt.setLong(1, pageObject.getStartRow());
		pstmt.setLong(2, pageObject.getEndRow());
		
		// 5. 실행 : select :executeQuery() -> rs, insert / update / delete :executeUpdate() -> Integer
		rs = pstmt.executeQuery();
		
		// 6. DB에서 가져온 데이터 채우기
		if(rs != null) {
			while(rs.next()) { // 데이터가 있는 만큼 반복 실행
				// 저장할 객체를 생성한다.
				NoticeVO vo = new NoticeVO();
				// 데이터를 저장한다.
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
				// list에 담는다.
				list.add(vo);
			} // while() 끝
		} // if의 끝
		
		// 7. DB 닫기
		DB.close(con, pstmt, rs);
		
		return list;
	} // list()의 끝
	
	
	// 1-1. getTotalRow()
	public Long getTotalRow(PageObject pageObject) throws Exception{
		
		// System.out.println("BoardDAO.getTotalRow()----------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		Long totalRow = 0L;
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다.	// 2. 연결 객체 - 정보를 넣고 서버에 다녀온다.
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		String sql = "select count(*) from notice ";
		
		// period 처리
		// pre : 현재공지, old : 지난공지 , res : 예약공지, all : 전체공지 
		sql += period(pageObject.getPeriod());
		
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		
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
	

	// 1-2. 공지 종류 처리 메서드
	public String period(String period) {
		String sql = "";
		// period 처리
		// pre : 현재공지, old : 지난공지 , res : 예약공지, all : 전체공지 
		if(!period.equals("all")) { // all : 조건 없음.
			sql = " where ";
			if(period.equals("pre")) // 현재 공지 - 현재 날짜가 시작일과 종료일 사이에 있다.
				sql += " trunc(startDate) <= trunc(sysdate) and trunc(endDate) >= trunc(sysdate) ";
			if(period.equals("old")) // 지난 공지 - 종료일이 현재 날짜보다 작다.
				sql += " trunc(endDate) < trunc(sysdate) ";
			if(period.equals("res")) // 예약 공지 - 시작일이 현재 날짜보다 크다.
				sql += " trunc(startDate) > trunc(sysdate) ";
		} // if 의 끝
		
		return sql;
	} // period 메서드의 끝
	
	// 2. 공지 보기
	public NoticeVO view(Long no) throws Exception {
		NoticeVO vo = null;
		
		// 1. 2. 연결
		con = DB.getConnection();
		// 3. sql
		String sql = "select no, title, content,"
				+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
				+ " to_char(endDate, 'yyyy-mm-dd') endDate, "
				+ " to_char(writeDate, 'yyyy-mm-dd') writeDate, "
				+ " to_char(updateDate, 'yyyy-mm-dd') updateDate "
				+ " from notice "
				+ " where no = ? ";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, no);
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 저장
		if(rs != null && rs.next()) {
			vo = new NoticeVO();
			vo.setNo(rs.getLong("no"));
			vo.setTitle(rs.getString("title"));
			vo.setContent(rs.getString("content"));
			vo.setStartDate(rs.getString("startDate"));
			vo.setEndDate(rs.getString("endDate"));
			vo.setWriteDate(rs.getString("writeDate"));
			vo.setUpdateDate(rs.getString("updateDate"));
		} // if 의 끝
		// 7. 닫기
		DB.close(con, pstmt, rs);
		
		return vo;
	} // view()의 끝
	
	// 3. 공지 등록 처리
	public Integer write(NoticeVO vo) throws Exception {
		// 리턴 타입과 동일 변수 선언
		Integer result = 0;
		
		// 1. 2. 연결객체
		con = DB.getConnection();
		
		// 3. sql 작성
		String sql = "insert into notice(no, title, content, startDate, endDate) "
				+ " values(notice_seq.nextval, ?, ?, ?, ?)";				
		
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getStartDate());
		pstmt.setString(4, vo.getEndDate());
		
		// 5. 실행
		result = pstmt.executeUpdate();

		// 6. 결과 저장 - 5 번에서 실행
		
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
} // 클래스의 끝
