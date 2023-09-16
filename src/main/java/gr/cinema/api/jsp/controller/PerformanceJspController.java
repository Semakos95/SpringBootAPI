package gr.cinema.api.jsp.controller;

import gr.cinema.api.dto.PerformanceDTO;
import gr.cinema.api.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PerformanceJspController {

    private PerformanceService performanceService;
    @Autowired
    public PerformanceJspController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @RequestMapping(value = "/registerPerformance", method = RequestMethod.GET)//http://localhost:8080/registerPerformance OK
    public String showRegisterPerformanceForm(Model model) {
        PerformanceDTO performanceDTO = new PerformanceDTO();
        model.addAttribute("performance", performanceDTO);
        return "createPerformance";
    }


    @PostMapping("/registerPerformance")//http://localhost:8080/registerPerformance
    public String registerPerformance(@ModelAttribute("performance") PerformanceDTO performanceDTO) {
        performanceService.insertPerformanceDTO(performanceDTO);
        // You can add success messages or redirection logic here
        return "redirect:/showAllPerformances";
    }

    @GetMapping("/showAllPerformances")//http://localhost:8080/showAllPerformances
    public String listPerformances(Model model){
        List<PerformanceDTO> performanceDTOList = performanceService.getAllPerformanceDTOList();
        model.addAttribute("performances", performanceDTOList);
        return "performances";
    }
}
