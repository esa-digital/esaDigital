package uk.gov.dwp.esa.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class BankDetailsController {
	
	public static final String BankDetails = "bankDetails";
	public static final String BANK_DETAILS = "/bank-details";
	public static final Logger logger = LogManager.getLogger(BankDetailsController.class);

}
