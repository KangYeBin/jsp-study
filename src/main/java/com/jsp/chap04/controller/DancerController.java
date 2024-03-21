package com.jsp.chap04.controller;

import com.jsp.chap04.service.DeleteService;
import com.jsp.chap04.service.IDancerService;
import com.jsp.chap04.service.ListService;
import com.jsp.chap04.service.RegistService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 == 컨트롤러
// 클라이언트의 요청을 파악하고 모델에게 로직을 위임하여
// 응답할 view를 결정합니다

@WebServlet("*.do") // 확장자 패턴 -> 앞이 뭐든간에 .do로 끝나는 요청이라면 이 서블릿이 다 받는다
public class DancerController extends HttpServlet {

	// 인터페이스 타입의 변수를 선언해서, 상황에 맞게 서비스 객체를 갈아 끼운다
	private IDancerService service;
	private RequestDispatcher requestDispatcher;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		System.out.println("requestURI = " + requestURI);

		try {
			switch (requestURI) {
				case "/register.do":
					System.out.println("등록 폼으로 이동시켜주세요");
					// URL에 내부 주소가 나오지 않도록 수정
					requestDispatcher = request.getRequestDispatcher("/WEB-INF/chap03/dancer/register.jsp");
					requestDispatcher.forward(request, response);
					break;
				case "/regist.do":
					System.out.println("댄서 등록 요청");
					service = new RegistService();
					service.excute(request, response);
					// 서비스가 댄서 목록을 request에 담아놓은 상태
					// 디스패처에게 목적지를 알려주면서 request와 response 객체를 가지고 이동하라는 명령을 내림
					// 실제 페이지가 목적지로 이동되면서, request와 response 객체도 함께 전달됨
					// -> jsp에서 request를 꺼내서 목록을 화면에 뿌려 응답
					requestDispatcher = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
					requestDispatcher.forward(request, response);
					break;

				case "/delete.do":
					System.out.println("삭제 요청이 들어옴");
					service = new DeleteService();
					service.excute(request, response);
					requestDispatcher = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
					requestDispatcher.forward(request, response);
					break;

				case "/list.do":
					System.out.println("목록 화면 보여주기");
					service = new ListService();
					service.excute(request, response);
					requestDispatcher = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
					requestDispatcher.forward(request, response);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
