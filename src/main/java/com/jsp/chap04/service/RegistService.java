package com.jsp.chap04.service;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RegistService implements IDancerService{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// POST 방식에서 전달된 한글 데이터의 깨짐 방지 메서드
		request.setCharacterEncoding("UTF-8");

		// 제공된 form에 작성된 사용자의 입력값 가져오기
		String name = request.getParameter("name");
		String crewName = request.getParameter("crewName");
		String danceLevel = request.getParameter("danceLevel");
		// checkbox같이 여러 값을 전달받는 경우에는 getParameterValues를 사용
		String[] genresArray = request.getParameterValues("genres");

		// 얻어온 입력값을 save()에 전달 -> 객체 생성 및 Map에 저장까지 일괄 처리
		DancerRepository.save(name, crewName, danceLevel, genresArray);

		///////////// 등록 완료 /////////////

		// 등록이 완료되었으므로, 지금까지 등록된 댄서의 목록을 화면에 보여주고 싶을 때
		// MVC 구조에서 화면 응답은 View가 전담하기 때문에
		// 서비스는 repository로부터 목록을 받아와서 View단으로 데이터를 넘김

		// 댄서 목록들 받아오기
		// setAttribute("이름", 저장할 값) -> 이름을 통해 값을 얻을 수 있다
		List<Dancer> dancerList = DancerRepository.findAll();
		request.setAttribute("dl", dancerList);
		
		// 서비스의 역할은 여기까지
		// 화면단 결정은 Controller의 몫
	}
}
