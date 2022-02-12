package com.example.demo.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.TokenService;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String token = request.getHeader("token");
		if(token != null){
            boolean result = TokenService.verify(token);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            JSONObject response_json = new JSONObject();
            response_json.put("msg","wrong token");
            response.getWriter().append(response_json.toJSONString());
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
	}
}
