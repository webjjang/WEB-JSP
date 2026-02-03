package com.webjjang.member.controller;

import java.util.List;
import java.util.Random;

import com.webjjang.main.controller.Main;
import com.webjjang.main.service.Execute;
import com.webjjang.member.service.LoginService;
import com.webjjang.member.service.MemberChangeConDateService;
import com.webjjang.member.service.MemberChangeGradeService;
import com.webjjang.member.service.MemberChangePwService;
import com.webjjang.member.service.MemberChangeStatueService;
import com.webjjang.member.service.MemberCheckPwService;
import com.webjjang.member.service.MemberListService;
import com.webjjang.member.service.MemberSearchIdService;
import com.webjjang.member.service.MemberUserChangePwService;
import com.webjjang.member.service.MemberViewService;
import com.webjjang.member.service.MemberWriteService;
import com.webjjang.member.vo.Login;
import com.webjjang.member.vo.LoginVO;
import com.webjjang.member.vo.MemberVO;
import com.webjjang.util.io.In;
import com.webjjang.util.io.MemberPrint;

public class MemberController {

	@SuppressWarnings("unchecked")
	public void execute() {
		// 회원관리 처리 무한 반복
		while(true) {
			// 로그인 정보 출력
			Login.loginPrint();
			try { // 정상처리
				// 회원관리 처리
				// 로그인 안한 경우 메뉴
				//  - 1. 로그인  2. 회원가입  3. 아이디 찾기  4. 비밀번호 찾기
				// 로그인 한 경우 메뉴
				//  - 1. 로그아웃  2. 내 정보보기  3. 비밀번호 변경  4. 내 정보 수정 5. 회원 탈퇴
				//  -   관리자 : 6. 회원리스트 7. 회원 정보보기 8. 회원 상태 변경  9. 회원 등급 변경
				// 1. 일반게시판 메뉴 출력
				System.out.println("<<< 회원 관리 메뉴 >>>");
				System.out.println("======================================================");
				if(!Login.isLogin()) // 로그인을 안한 경우 메뉴 출력
					System.out.println(" 1. 로그인  2. 회원가입  3. 아이디 찾기  4. 비밀번호 찾기");
				else { // 로그인을 한 경우 메뉴 출력
					System.out.println(" 1. 로그아웃  2. 내 정보보기  3. 비밀번호 변경 4. 내 정보 수정 5. 회원 탈퇴");
					// 로그인을 했고 관리자인 경우 관리자 메뉴가 나와야 한다.
					if(Login.isAdmin()) {
						System.out.println(" 6. 회원리스트 7. 회원 정보보기 8. 회원 상태 변경  9. 회원 등급 변경");
					}
				}// 메뉴 출력의 끝
				System.out.println(" 0. 이전 메뉴");
				System.out.println("======================================================");
				// 2. 일반게시판 메뉴 입력
				String menu = In.getStr("메뉴 입력");
				System.out.println(); // 줄바꿈
				
				// 3. 메뉴 처리
				switch (menu) {
				case "1":
					if(!Login.isLogin()) { // 로그인을 안한 경우
					// 1. 로그인 처리
					// id와 비밀번호를 입력 받아서 VO객체에 넣는다.
						LoginVO userVO = new LoginVO();
						userVO.setId(In.getStr("아이디"));
						userVO.setPw(In.getStr("비밀번호"));
						// DB에서 데이터를 가져오는 것 실행
						LoginVO vo = (LoginVO) Execute.execute(new LoginService(), userVO);
						// 로그인 처리 - vo가 null이 아니면 특별한 위치에 저장할 장소에 가져온 데이터를 저장한다.
						if( vo == null) throw new Exception("회원 정보를 확인하시고 다시 실행해 보세요.");
						Login.setLoginVO(vo);// 로그인 처리를 한다.
						// 최신 접속일을 변경한다.
						Execute.execute(new MemberChangeConDateService(), vo.getId());
						
					} else {// 로그인을 한경우.
						// 1. 로그아웃 처리한다.
						Login.setLoginVO(null); // 없는 데이터 null을 세팅한다. - 로그아웃
						System.out.println("*** 로그아웃되셨습니다. ***\n");
					}
					break;
				case "2":
					if(!Login.isLogin()) { // 로그인을 하지 않은 경우 회원가입을 진행합니다.
						//2. 회원가입
						// 객체생성 - 정보 받기.
						MemberVO vo = new MemberVO();
						vo.setId(In.getStr("아이디"));
						vo.setPw(In.getStr("비밀번호"));
						vo.setName(In.getStr("이름"));
						vo.setGender(In.getStr("성별"));
						vo.setBirth(In.getStr("생년월일"));
						vo.setTel(In.getStr("연락처"));
						vo.setEmail(In.getStr("이메일"));
						Execute.execute(new MemberWriteService(), vo);
						System.out.println("*****************************************");
						System.out.println(" 축하드립니다. 회원가입이 되셨습니다. 로그인하세요.");
						System.out.println("*****************************************\n");
					} else { // 로그인을 한 경우 내 정보보기 진행
						// 2. 내 정보 보기
						//  Longin에서 id를 가져와서 정보를 가져오다.
						MemberVO vo = (MemberVO) Execute.execute(new MemberViewService(), Login.getId());
						MemberPrint.print(vo, 1);
					}
					break;
				case "3":
					if(!Login.isLogin()) { // 로그인을 하지 않은 경우 회원가입을 진행합니다.
						// 3. 아이디 찾기
						System.out.println("*** 아이디를 찾기 위해서 회원의 정보가 필요합니다. ***");
						// 객체생성 - 정보 받기.
						MemberVO vo = new MemberVO();
						vo.setName(In.getStr("이름"));
						vo.setEmail(In.getStr("이메일"));
						String id = (String) Execute.execute(new MemberSearchIdService(), vo);
						System.out.println("*****************************************");
						System.out.println(" 찾으시는 아이디 : " + id);
						System.out.println("*****************************************\n");
					} else { // 로그인을 한 경우 비밀번호 변경 진행
						// 3. 비밀번호 변경
						System.out.println("*** 비밀번호 세팅 진행 ***");
						MemberVO vo = new MemberVO();
						vo.setId(Login.getId());
						vo.setPw(In.getStr("현재 비밀번호"));
						vo.setNewPw(In.getStr("새로운 비밀번호"));
						Integer result = (Integer) Execute.execute(new MemberUserChangePwService(), vo);
						if(result == 1) System.out.println("*** 비밀번호가 변경되었습니다. ***");
						else System.out.println("*** 비밀번호가 변경되지 않았습니다. 정보를 다시 확인해 주세요. ***");
					}
					break;
				case "4":
					if(!Login.isLogin()) { // 로그인을 하지 않은 경우 회원가입을 진행합니다.
						// 4. 비밀번호 찾기
						System.out.println("*** 비밀번호 찾기 위해서 회원의 정보가 필요합니다. ***");
						// 객체생성 - 정보 받기.
						MemberVO vo = new MemberVO();
						vo.setId(In.getStr("아이디"));
						vo.setName(In.getStr("이름"));
						vo.setBirth(In.getStr("생년월일(XXXX-XX-XX)"));
						String id = (String) Execute.execute(new MemberCheckPwService(), vo);
						if(id != null) {// 입력한 정보가 맞다.
							// 임시 비밀번호를 가져와서 DB에 수정을 한다.
							Random r = new Random();
							int pwInt = r.nextInt(9000) + 1000; // 숫자로된 임시 비밀번호 4자리만들기
							// 비밀번호 변경 데이터
							vo = new MemberVO();
							vo.setId(id);
							vo.setNewPw("" + pwInt);
							Integer result = (Integer) Execute.execute(new MemberChangePwService(), vo);
							if(result == 1) {
								System.out.println("*********************************");
								System.out.println(" 임시비밀번호가 발급되었습니다. - " + vo.getNewPw());
								System.out.println(" 로그인하신 후 비밀번호를 변경해 주세요. ");
								System.out.println("*********************************\n");
							} else {
								System.out.println("*** 임시비밀번호 변경에 실패했습니다. ***\n");
							} // 임시 비밀번호 처리 끝
						} else {
							System.out.println("*********************************");
							System.out.println(" 정보를 다시 확인하고 다시 시도해 주세요 ");
							System.out.println("*********************************\n");
						}
					} else { // 로그인을 한 경우 내 정보 수정 진행
						// 4. 내 정보 수정
						
					}
					break;
				case "5": // 회원 탈퇴 진행 - 비밀번호가 맞으면 회원 탈퇴 진행(정상 -> 탈퇴 변경)
					// 아이디 - 로그인 정보 자동으로 가져온다. 비밀번호 - 사용자에게 받는다.
					break;
				case "6": // 관리자 - 회원리스트
					if(Login.isAdmin()) { // 관리자인 경우 처리
						System.out.println("5. 회원리스트 처리");
						// DB 에서 데이터 가져오기
						List<MemberVO> list = (List<MemberVO>) Execute.execute(new MemberListService(), null);
						// 데이터 출력하기
						MemberPrint.print(list);
					} else {
						// 잘못된 메뉴 처리
						Main.invalidMenuPrint();
					}
					break;
				case "7": // 관리자 - 회원 정보보기
					if(Login.isAdmin()) {
						MemberVO vo 
						= (MemberVO) Execute.execute(new MemberViewService(), In.getStr("정보를 보기의 아이디"));
						MemberPrint.print(vo, 0);
					} else {
						// 잘못된 메뉴 처리
						Main.invalidMenuPrint();
					}
					break;
				case "8": // 관리자 - 회원 상태 변경
					// 상태 변경할 아이디와 상태를 입력 받는다.
					if(Login.isAdmin()) {
						MemberVO vo = new MemberVO();
						vo.setId(In.getStr("아이디"));
						// 입력한 아이디가 로그인한 관리자의 아이디와 같으면 변경 불가능 출력하고 빠져나간다.
						if(vo.getId().equals(Login.getId())) {
							System.out.println("** 로그인한 관리자의 상태는 변경할 수 없습니다. **\n");
							break;
						}
						vo.setStatus(In.getStr("상태(정상/탈퇴/강퇴/휴면)"));
						Integer result = (Integer) Execute.execute(new MemberChangeStatueService(), vo);
						if(result == 1)
							System.out.println("** 아이디 " + vo.getId() + "의 상태가 " + vo.getStatus()
							+ "(으)로 변경되었습니다. **\n");
						else System.out.println("** 상태 변경에 실패하였습니다. **\n");
					} else {
						// 잘못된 메뉴 처리
						Main.invalidMenuPrint();
					}
					break;
				case "9": // 관리자 - 회원 등급변경
					if(Login.isAdmin()) {
						MemberVO vo = new MemberVO();
						vo.setId(In.getStr("아이디"));
						// 입력한 아이디가 로그인한 관리자의 아이디와 같으면 변경 불가능 출력하고 빠져나간다.
						if(vo.getId().equals(Login.getId())) {
							System.out.println("** 로그인한 관리자의 등급을 변경할 수 없습니다. **\n");
							break;
						}
						vo.setGradeNo(Integer.parseInt(In.getStr("1. 일반회원 / 9. 관리자")));
						Integer result = (Integer) Execute.execute(new MemberChangeGradeService(), vo);
						if(result == 1)
							System.out.println("** 아이디 " + vo.getId() + "의 등급을 " 
							+ ((vo.getGradeNo() == 1)?"일반회원":"관리자")
							+ "(으)로 변경되었습니다. **\n");
						else System.out.println("** 등급 변경에 실패하였습니다. **\n");
					} else {
						// 잘못된 메뉴 처리
						Main.invalidMenuPrint();
					}
					break;
				case "0":
					
					return;

				default:
					// 잘못된 메뉴 처리
					Main.invalidMenuPrint();
					break;
				} // 메뉴 처리 switch 문의 끝
				
			} catch (Exception e) {
				e.printStackTrace();
				// 사용자를 위한 예외 코드 작성
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(" 회원 관리 처리 중 오류가 발생되었습니다.");
				System.out.println(" 오류 : " + e.getMessage());
				System.out.println(" 다시 한번 실행해 주시고 오류가 계속되면 오류 게시판에 남겨 주세요.");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			} // try ~ catch의 끝
			
		} // while(true)의 끝
	} // execute()의 끝
	
} // 클래스의 끝
