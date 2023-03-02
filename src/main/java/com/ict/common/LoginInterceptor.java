package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// spring 5.3부터 deprecated => AsyncHandlerInterceptor 사용
// public class LoginInterceptor extends HandlerInterceptorAdapter {
   public class LoginInterceptor implements AsyncHandlerInterceptor{
	// DispatcherServlet에서 Controller로 가기 전에 구동됨
	// 결과가 true이면 Controller, false이면 특정 jsp로 이동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 로그인 체크를 해서 만약! 로그인이 안된 상태라면 value에 false를 저장!
		// 먼저 HttpSession얻기
		HttpSession session = request.getSession(true);// true의 의미는
		// 만약! session이 삭제된 상태라면 새로운 session을 생성해 준다.
		// 삭제가 안된 상태라면 사용하고 있던 session을 그대로 전달해 준다.

		// 로그인 시 저장했던 객체(mvo)를 얻어낸다.
		Object obj = session.getAttribute("login");
		if (obj == null) {
			// 로그인을 하지 않은 경우를 잡아낸 상태!!!
			request.getRequestDispatcher("/WEB-INF/views/member/login_error.jsp").forward(request, response);
			
			return false;
		}

		// 로그인이 된 상태인 경우.. 이때 해야할 일들이 있으면 여기쯤에서
		// 구현하면 된다.
		return true;
	}
	
//	Controller 에서 리턴되어 뷰 리졸버로 가기전에 구동
//  @Override	
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}

//	뷰 리졸버가 뷰를 찾아서 내보내고 나면 구동 
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
	
	
	
	
	
	
	
	
	
	
	
}
