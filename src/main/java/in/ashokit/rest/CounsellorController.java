package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.DashboardResponseDto;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;
	
	
	@Autowired
	private EnquiryService enquiryService;
	
	//model is used to send the data from controller to UI
	@GetMapping("/")
	public String index(Model model) {
	
		Counsellor cobj= new Counsellor();
		
		model.addAttribute("counsellor",cobj);
		
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		Integer cid = (Integer)session.getAttribute("CID");
		
		DashboardResponseDto dashboardInfoDto = counsellorService.getDashboardInfo(cid);
		
		model.addAttribute("dashboardInfo",dashboardInfoDto);
		
		return "dashboard";
	}
	
	
	@PostMapping("/login")
	public String logIn(Counsellor counselllor, Model model,HttpServletRequest request) {
		 
		Counsellor c = counsellorService.login(counselllor.getEmail(), counselllor.getPwd());
		
		if(c==null) {
			
		  model.addAttribute("emsg", "invalid Credenntials");
			
		  return "index";
		
		}else {
			
		    HttpSession session = request.getSession(true);
			
		    session.setAttribute("CID", c.getCounsellorId());
			
		    return "redirect:/dashboard";
		}
		
  }
	@GetMapping("/logout")
	       public String logOut(HttpServletRequest request) {
		
	       HttpSession session = request.getSession(false);
		
	       session.invalidate();
	
	       return "redirect:/";
  }
	
	@GetMapping("/register")
	public String register(Model model) {
		
		Counsellor cobj = new Counsellor();
		
		model.addAttribute("counsellor", cobj);
		
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegestration(Counsellor counsellor, Model model) {
		
		boolean emailUnique = counsellorService.isEmailUnique(counsellor.getEmail());
		
		if(emailUnique) {
			
		boolean registered = counsellorService.register(counsellor);
		
		if(registered){
		
			model.addAttribute("smsg", "Regestration Success, please login");
		
		}else {
			model.addAttribute("emsg", "Regestration Failed");
	  }
	}
		else {
		model.addAttribute("emsg", "duplicate email found");	
		}
		return "register";
	}
	
	
	
	
	
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	

