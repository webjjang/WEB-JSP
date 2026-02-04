package com.webjjang.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.webjjang.board.controller.BoardController;
import com.webjjang.member.controller.MemberController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet implementation class Init
 */
// @WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Controller를 저장하는 변수
	private static Map<String, Controller> controllerMap = new HashMap<>();
	
	// Controller 저장변수에서 Controller를 꺼내가는 메서드
	public static Controller getController(String module) {
		return controllerMap.get(module);
	}
	
    /**
     * Default constructor. 
     */
    public Init() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    // 서버가 돌아갈 때 실행되도록 하고 싶다.
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Init.init()-----------------------------------------------------");
		// 1. 생성하고, 2 저장 - map, 3. 조립
		// 생성해서 저장해 놓는다.
		controllerMap.put("/board", new BoardController());
		controllerMap.put("/member", new MemberController());
	}

}
