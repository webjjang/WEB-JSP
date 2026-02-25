package com.webjjang.image.dao;

import java.util.ArrayList;
import java.util.List;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.util.db.DB;
import com.webjjang.util.page.PageObject;

public class ImageDAO extends DAO{

	//1-1. 이미지 리스트
	public List<ImageVO> list(PageObject pageObject) throws Exception {
		List<ImageVO> list = new ArrayList<>();
		
		// 1. 2.
		con = DB.getConnection();
		// 3.
		// 3-1 원본 데이터 가져오기
		String sql = "select i.no, i.title, i.id, m.name, "
				+ " to_char(i.writeDate, 'yyyy-mm-dd') writeDate, i.fileName "
				+ " from image i, member m "
				+ " where i.id = m.id "
				+ " order by no desc";
		// 3-2. 순서 번호 붙이기
		sql = "select rownum rnum, no, title, id, name, writeDate, fileName "
				+ " from(" + sql + ")";
		// 3-3. 페이지에 맞는 데이터 가져오기
		sql = "select rnum, no, title, id, name, writeDate, fileName "
				+ " from(" + sql + ") "
				+ " where rnum between ? and ? ";
		
		// 4. 
		pstmt = con.prepareStatement(sql);
		pstmt.setLong(1, pageObject.getStartRow());
		pstmt.setLong(2, pageObject.getEndRow());
		
		// 5. 
		rs = pstmt.executeQuery();
		// 6.
		if(rs != null) {
			while(rs.next()) {
				ImageVO vo = new ImageVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setFileName(rs.getString("fileName"));
				list.add(vo);
			} // while의 끝
		} // if의 끝
		// 7.
		DB.close(con, pstmt, rs);
		
		return list;
	} // list()의 끝
	
	// 1-2. 전체 데이터의 개수를 가져오는 메서드
	public Long getTotalRow(PageObject pageObject) throws Exception {
		Long totalRow = 0L;
		
		// 1.2.
		con = DB.getConnection();
		// 3.
		String sql = "select count(*) from image";
		// 4.
		pstmt = con.prepareStatement(sql);
		// 5. 
		rs = pstmt.executeQuery();
		// 6.
		if(rs != null && rs.next())
			totalRow = rs.getLong(1);
		// 7. 
		DB.close(con, pstmt, rs);
		
		return totalRow;
	}
	
} // 클래스이 끝
