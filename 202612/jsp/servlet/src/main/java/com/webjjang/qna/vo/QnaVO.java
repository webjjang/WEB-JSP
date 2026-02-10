package com.webjjang.qna.vo;

// private 변수, getter() & setter(p), toString(), 생성자, 그외 메서드
public class QnaVO {

	// 글번호, 제목, 내용, 작성자, 작성일, 조회수, 비밀번호.
	private long no;
	private String title;
	private String content;
	private String id; // 회원 아이디가 있어야 한다.
	private String name; // 회원 이름이 있어야 한다. Member table 에 있다.
	private String writeDate; // 날짜 계산을 안한다.
	private long hit;
	private long refNo; // 처음 작성한 질문 번호
	private long ordNo; // refNo로 모았을 때 순서 번호.
	private long levNo; // 들여쓰기
	private long parentNo; // 자동삭제
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public long getRefNo() {
		return refNo;
	}
	public void setRefNo(long refNo) {
		this.refNo = refNo;
	}
	public long getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(long ordNo) {
		this.ordNo = ordNo;
	}
	public long getLevNo() {
		return levNo;
	}
	public void setLevNo(long levNo) {
		this.levNo = levNo;
	}
	public long getParentNo() {
		return parentNo;
	}
	public void setParentNo(long parentNo) {
		this.parentNo = parentNo;
	}
	@Override
	public String toString() {
		return "QnaVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", name=" + name
				+ ", writeDate=" + writeDate + ", hit=" + hit + ", refNo=" + refNo + ", ordNo=" + ordNo + ", levNo="
				+ levNo + ", parentNo=" + parentNo + "]";
	}
	
}
