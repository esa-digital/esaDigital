package uk.gov.dwp.esa.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.util.ControllerUrls;

@Controller
@RequestMapping(ControllerUrls.START_YOUR_APPLICATION_FORM)
public class StartYourApplicationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(HttpServletRequest request){
		
		return ControllerUrls.START_YOUR_APPLICATION_FORM;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String postForm(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		LinkedList<String> pageList = new LinkedList<String>();
		pageList.add(ControllerUrls.HELP_DETAILS_URL);
		session.setAttribute("PageArray", pageList);
		
		return "redirect:"+ControllerUrls.HELP_DETAILS_URL;
		
	}

}
