package com.jsp.chap04.service;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteService implements IDancerService {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		DancerRepository.delete(name.trim());

		// 삭제가 완료된 후에 적용된 댄서 목록을 list.jsp에 다시 보여주고 싶을 때
		List<Dancer> dancerList = DancerRepository.findAll();
		// list.jsp 재활용
		request.setAttribute("dl", dancerList);

	}
}
