package cn.enn.testoauth2.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.enn.testoauth2.annotition.Authorization;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("in interceptor method");
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// 如果方法注明了 Authorization，并且验证 token 失败，并且返回 401 错误
		if (method.getAnnotation(Authorization.class) != null) {
			// 从 header 中得到 token
			String authorization = request.getHeader("authorization");
			if ("123".equals(authorization)) {
				// 如果 token 验证成功，将 token 对应的用户 id 存在 request 中，便于之后注入
				request.setAttribute("userId", "123");
				return true;
			}
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}

		return true;
	}
}
