package kr.co.spring3study.login;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import kr.co.spring3study.login.form.Login;
import kr.co.spring3study.vo.UserVo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login.html")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showLoginForm(Map model) {
		Login login = new Login();
		model.put("login", login);
		return "login";
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String processLogin(@Valid Login login, BindingResult result, Map model) {
//		System.out.println("LoginController.processLogin()");
//		if (result.hasErrors()) {
//			System.out.println("LoginController.processLogin(), hassErrors()");
//			return "login";
//		}
//		
//		if (!authUser(login)) {
//			return "login";
//		}
//		
//		model.put("login", login);
////		return "loginSuccess";
////		return "loginOk";
//		return "jobHistory";
//	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processLogin(@Valid Login login, BindingResult result, Map model) {
		System.out.println("LoginController.processLogin()");
		if (result.hasErrors()) {
			System.out.println("LoginController.processLogin(), hassErrors()");
			
			RedirectView redirect = new RedirectView("/login.html");
			redirect.setContextRelative(true);
			ModelAndView mav = new ModelAndView(redirect, model);
			return mav;
		}
		
		if (!authUser(login)) {
			RedirectView redirect = new RedirectView("/login.html");
			redirect.setContextRelative(true);
			ModelAndView mav = new ModelAndView(redirect, model);
			return mav;
		}
		
		RedirectView redirect = new RedirectView("/jobHistory.html");
		redirect.setContextRelative(true);
		ModelAndView mav = new ModelAndView(redirect, model);
		return mav;
	}
	
	
	private boolean authUser(Login login) {
		boolean ret = false;
		
		System.out.println(login.getId() + ":" + login.getPassword());
		
		String formId = login.getId();
		String formPwd = login.getPassword();
		UserVo formUserVo = new UserVo(formId, formPwd);
		
		try {
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//			SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("kr/co/spring3study/login/config/configuration.xml"));
			SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("kr/co/spring3study/login/config/config.xml"));
			
			SqlSession ss = factory.openSession(true);
			List<UserVo> users = ss.selectList("selectIdPwd", formUserVo);
			
			System.out.println("LoginController.authUser(), users.size() : " + users.size());
			
			if (users.size() > 0) {
				for (UserVo vo : users) {
					System.out.println(vo.getUser_id() + ":" + vo.getUser_pwd() + ":" + vo.getUser_name());
					login.setName(vo.getUser_name());
					break;
				}
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}
