package uk.gov.dwp.esa.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.util.GenericModelParser;

@Component
public abstract class GenericController<T> {

	private static final Logger logger = Logger.getLogger(GenericController.class);

	private Validator validator;

	private String propertyFile;

	private String sessionAttribute;

	private String currentUrl;

	private String nextPage;

	private String previousPage;

	private T object;

	@Autowired
	private GenericModelParser generator;

	public GenericController(String propertyFile, String sessionAttribute, String currentUrl, String nextPage, String previousPage) {
		this.propertyFile = propertyFile;
		this.sessionAttribute = sessionAttribute;
		this.currentUrl = currentUrl;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getFormData(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String sessionId = session.getId();
		LinkedList<String> pageList = (LinkedList<String>) session.getAttribute("PageArray");
		
		for(String item : pageList){
			logger.info(item);
		}
		
		if (pageList.contains("/api"+currentUrl)) {

			generator.setLocation(propertyFile);
			generator.parseModel(model);

			logger.info(sessionId + " Getting form :" + currentUrl);

			T object = (T) session.getAttribute(sessionAttribute);
			if (object == null) {
				object = getObject();
			}

			model.addAttribute(object);

			return currentUrl;
		} else {
			logger.info("Previous page not completed, redirecting to last completed for,");
			return "redirect:" + pageList.getLast();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postFormData(Model model, T object, BindingResult error, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String sessionId = session.getId();

		logger.info(sessionId + "Saving form data for :" + currentUrl);

		if (error.hasErrors()) {
			// this will check whether there are any preload errors
			// do something -- return to error page
			// currently returning the same page, but should redirect to error
			// page.
			return currentUrl;
		}

		validate(object, error);

		if (error.hasErrors()) {
			generator.setLocation(propertyFile);
			generator.parseModel(model);
			model.addAttribute(object);
			logger.debug(error);
			return currentUrl;
		}
		session.setAttribute(sessionAttribute, object);
		LinkedList<String> pageList = (LinkedList<String>) session.getAttribute("PageArray");
		if(!pageList.contains(nextPage)){
			pageList.addLast(nextPage);
		}

		logger.info(sessionId + "Finished saving data");
		return "redirect:"+ nextPage;

	}


	public abstract void validate(T object, BindingResult error);

	public String getPropertyFile() {
		return propertyFile;
	}

	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}

	public String getSessionAttribute() {
		return sessionAttribute;
	}

	public void setSessionAttribute(String sessionAttribute) {
		this.sessionAttribute = sessionAttribute;
	}

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

}