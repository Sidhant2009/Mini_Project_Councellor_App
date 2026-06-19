package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.ashokit.dto.EnqFilterRequestDto;
import in.ashokit.dto.EnquiryDto;
import in.ashokit.entity.Enquiry;
import in.ashokit.service.CourseService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

    @Autowired
    private EnquiryService enqService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/add-enquiry")
    public String enquiryFormat(Model model) {

        EnquiryDto enquiryDtoObj = new EnquiryDto();

        model.addAttribute("enquiry", enquiryDtoObj);
        model.addAttribute("courses", courseService.getCourses());
       
        return "add-enq";
    }

    @PostMapping("/enquiry")
    public String addEnquiry(EnquiryDto enquiryDto,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("CID") == null) {
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("CID");

        boolean status = enqService.addEnquiry(enquiryDto, cid);

        if (status) {
            redirectAttributes.addFlashAttribute("smsg", "Enquiry Added");
        } else {
            redirectAttributes.addFlashAttribute("emsg", "Enquiry Not Added");
        }

        return "redirect:/add-enquiry";
    }
    

    @GetMapping("/view-enquiries")
    public String viewEnquiries(Model model,
                                HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("CID") == null) {
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("CID");

        model.addAttribute("filterRequestDto", new EnqFilterRequestDto());

        model.addAttribute("enqs", enqService.getAllEquiries(cid));

        model.addAttribute("courses", courseService.getCourses());

        return "view-enq";
    }
    
    @PostMapping("/filter-enquiries")
    public String filterEnquiries(EnqFilterRequestDto filterRequestDto,
                                  Model model,
                                  HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("CID") == null) {
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("CID");

        List<Enquiry> enquiriesWithFilter =
                enqService.getEnquiriesWithFilter(filterRequestDto, cid);

        model.addAttribute("enqs", enquiriesWithFilter);
        model.addAttribute("courses", courseService.getCourses());
        model.addAttribute("filterRequestDto", filterRequestDto);
        model.addAttribute("courses", courseService.getCourses());
        
        return "view-enq";
    }

    @GetMapping("/editEnq")
    public String editEnquiry(@RequestParam("enqId") Integer enqId,
                              Model model) {

        Enquiry enquiryById = enqService.getEnquiriesById(enqId);

        EnquiryDto dto = new EnquiryDto();

        BeanUtils.copyProperties(enquiryById, dto);

        dto.setCourseId(enquiryById.getCourse().getCourseId());

        model.addAttribute("enquiry", dto);   
        model.addAttribute("courses", courseService.getCourses());

        return "add-enq";
    }
}