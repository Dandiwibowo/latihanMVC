package dw.tyhmeleaf.paractice1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dw.tyhmeleaf.paractice1.dto.ErrorMessage;
import dw.tyhmeleaf.paractice1.dto.IndexDTO;
import dw.tyhmeleaf.paractice1.entity.DataForm;
import dw.tyhmeleaf.paractice1.repo.IndexRepo;
import dw.tyhmeleaf.paractice1.services.IndexService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @Autowired
    private IndexService indexService;

	@Autowired
	private IndexRepo indexRepo;

    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("indexDTO", new IndexDTO());
        return "index";
    }
    
    @PostMapping
	public String save(@Valid IndexDTO indexDTO, 
		BindingResult bindingResult, 
		Model model, 
		RedirectAttributes redirectAttribute) {
		List<String> errorMessage = new ArrayList<String>();
		
		if(!bindingResult.hasErrors()) {
			if(indexService.findByEmail(indexDTO.getEmail()) == null) {
				DataForm dataForm = new DataForm();
				dataForm.setEmail(indexDTO.getEmail());
				dataForm.setPassword(indexDTO.getPassword());
				dataForm.setFirstname(indexDTO.getFirstname());
				dataForm.setLastname(indexDTO.getLastname());
				indexService.save(dataForm);
				return "redirect:/table";
			}else {
				ErrorMessage msg = new ErrorMessage();
				errorMessage.add("Email already used");
				msg.setMessages(errorMessage);
				model.addAttribute("ERROR", msg);
				model.addAttribute("indexDTO", indexDTO);
				return "index";
			}
		}else {
			ErrorMessage msg = new ErrorMessage();
			for(ObjectError err: bindingResult.getAllErrors()) {
				errorMessage.add(err.getDefaultMessage());
			}
			msg.setMessages(errorMessage);
			model.addAttribute("indexDTO", indexDTO);
			model.addAttribute("ERROR", msg);
			return "index";
		}
	}

	@GetMapping("/table")
	public String index(Model model) {
		Iterable<DataForm> listOfUser = indexRepo.findAll();
		model.addAttribute("listOfUser", listOfUser);
		return "table";
	}
}
