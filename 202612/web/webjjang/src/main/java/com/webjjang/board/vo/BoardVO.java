package com.webjjang.board.vo;

// private 변수, getter() & setter(p), toString(), 생성자, 그외 메서드
public class BoardVO {

	// 글번호, 제목, 내용, 작성자, 작성일, 조회수, 비밀번호.
	private long no;
	private String title;
	private String content;
	private String writer;
	private String writeDate; // 날짜 계산을 안한다.
	private long hit;
	private String pw; // 본인 확인용 - 수정, 삭제할 때 필요
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + ", hit=" + hit + ", pw=" + pw + "]";
	}
	
}
