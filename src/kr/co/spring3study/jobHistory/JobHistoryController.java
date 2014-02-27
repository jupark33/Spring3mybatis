package kr.co.spring3study.jobHistory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import kr.co.spring3study.vo.JobItemVo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jobHistory.html")
public class JobHistoryController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showJobHistory(Map model) {
		System.out.println("JobHistoryController.showJobHistory()");
		
		try {
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(Resources.getResourceAsReader("kr/co/spring3study/login/config/config.xml"));
			
			SqlSession ss = factory.openSession(true);
			List<JobItemVo> jobItems = ss.selectList("getList");
			
			System.out.println("JobHistoryController.showJobHistory(), jobItems.size() : " + jobItems.size());
			
			model.put("jobItems", jobItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "jobHistory";
	}
	
	
	
}
