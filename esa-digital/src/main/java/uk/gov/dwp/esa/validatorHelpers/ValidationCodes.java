/**
 * © Crown copyright 2015. This source code is licensed under the <a
 * href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/"
 * rel="license">Open Government Licence 3.0</a>. You may use and re-use the code free of charge in
 * any format or medium, under the terms of the Open Government Licence. When you use this code, you
 * should include the following attribution: "[fully qualified class name|full package name], Crown
 * copyright, 2015, licensed under the <a
 * href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/">Open Government
 * Licence 3.0</a>". Any email enquiries regarding the use and re-use of this information resource
 * should be sent to: psi@nationalarchives.gsi.gov.uk.
 */

package uk.gov.dwp.esa.validatorHelpers;

/**
 * This class provides the validation codes that will be returned by the validators
 * 
 * @author ESA
 */
public final class ValidationCodes {

    /**
     * Private constructor to prevent instantiation
     */
    private ValidationCodes() {
    }

    public static final String APP_REF_EMPTY = "atw.application.header.ref.empty";
    public static final String SUBMISSION_DATE_EMPTY = "atw.application.header.date.empty";
    public static final String QUESTION_VERSION = "atw.application.header.question.version.empty";
    
    //Claimant Messages
    
    public static final String CLAIMANT_ALPHA_COMMON = "claimant.field.alpha";
    public static final String CLAIMANT_FULLNAME_EMPTY = "claimant.fullname.empty";
    public static final String CLAIMANT_FIRSTNAME_EMPTY = "claimant.firstName.empty";
    public static final String CLAIMANT_SURNAME_EMPTY = "claimant.surname.empty";
    public static final String CLAIMANT_DOB_INVALID = "claimant.dob.invalid";
    public static final String CLAIMANT_DOB_UNDER_16 = "claimant.dob.less.than.sixteen";
    public static final String CLAIMANT_DOB_FUTURE = "claimant.dob.future";
    public static final String CLAIMANT_ADDRESS_EMPTY = "claimant.address1.empty";
    public static final String CLAIMANT_TITLE_EMPTY = "claimant.title.empty";
    
    public static final String CLAIMANT_TITLE_ALPHA = "claimant.title.alpha";
    public static final String CLAIMANT_OTHERNAME_ALPHA = "claimant.otherName.alpha";
    public static final String CLAIMANT_FIRSTNAME_ALPHA = "claimant.firstName.alpha";
    public static final String CLAIMANT_SURNAME_ALPHA = "claimant.surname.alpha";
    
    public static final String CLAIMANT_FULL_NAME_TOO_LONG = "claimant.fullName.greater.than.limit";
    public static final String CLAIMANT_FIRST_NAME_TOO_LONG = "claimant.firstName.greater.than.limit";
    public static final String CLAIMANT_SURNAME_TOO_LONG = "claimant.surname.greater.than.limit";
    public static final String CLAIMANT_MIDDLE_NAME_TOO_LONG = "claimant.middleName.greater.than.limit";


    public static final String CLAIMANT_ADDRESS1_TOO_LONG = "claimant.address1.greater.than.limit";
    public static final String CLAIMANT_POSTCODE_TOO_LONG = "claimant.postcode.greater.than.limit";
    public static final String CLAIMANT_TITLE_TOO_LONG = "claimant.title.greater.than.limit";

    
    public static final String CONTACT_PERSON_EMPTY = "contact.person.empty";
    public static final String CONTACT_PERSON_NOT_RECOGNISED = "contact.person.not.recognised";
    
    public static final String CONTACT_PHONE_AND_EMAIL_EMPTY = "contact.phone.and.email.empty";
    public static final String CONTACT_EMAIL_EMPTY = "contact.email.empty";
    public static final String CONTACT_EMAIL_TOO_LONG = "contact.email.too.long";
    public static final String CONTACT_PHONE_EMPTY = "contact.phone.empty";
    public static final String CONTACT_PHONE_TOO_LONG = "contact.phone.too.long";
    public static final String CONTACT_EMAIL_INVALID = "contact.email.invalid";
    
    public static final String CONTACT_REPRESENTATIVE_FULLNAME_EMPTY = "contact.representative.fullname.empty";
    public static final String CONTACT_REPRESENTATIVE_FULLNAME_TOO_LONG = "contact.representative.fullname.too.long";
    public static final String CONTACT_REPRESENTATIVE_PHONE_EMPTY = "contact.representative.phone.empty";
    public static final String CONTACT_REPRESENTATIVE_PHONE_TOO_LONG = "contact.representative.phone.too.long";
    public static final String CONTACT_REPRESENTATIVE_RELATION_EMPTY = "contact.representative.relation.empty";
    public static final String CONTACT_REPRESENTATIVE_RELATION_TOO_LONG = "contact.representative.relation.too.long";
    
    public static final String JOB_EMPTY = "job.status.job.empty";
    public static final String JOB_STATUS_HAVE_EMPLOYMENT_EMPTY = "job.status.have.employed.empty";

    public static final String EMPLOYED_JOB_TITLE_EMPTY = "employed.jobTitle.empty";
    public static final String EMPLOYED_EMPLOYER_EMPTY = "employed.employer.empty";
    public static final String EMPLOYED_ADDRESS_EMPTY = "employed.address1.empty";
    
    public static final String EMPLOYED_JOB_TITLE_TOO_LONG = "employed.job.title.greater.than.limit";
    public static final String EMPLOYED_EMPLOYER_TOO_LONG = "employed.employer.greater.than.limit";
    public static final String EMPLOYED_ADDRESS_LINE_TOO_LONG = "employed.address1.greater.than.limit";
    public static final String EMPLOYED_POSTCODE_TOO_LONG = "employed.postcode.greater.than.limit";

    public static final String SELF_EMPLOYED_JOB_EMPTY = "self.employed.jobTitle.empty";
    public static final String SELF_EMPLOYED_ADDRESS_DIFFERENT_EMPTY = "self.employed.work.address.different";
    public static final String SELF_EMPLOYED_ADDRESS_EMPTY = "self.employed.address1.empty";
    
    public static final String SELF_EMPLOYED_ADDRESS1_TOO_LONG = "self.employed.address1.greater.than.limit";
    public static final String SELF_EMPLOYED_POSTCODE_TOO_LONG = "self.employed.postcode.greater.than.limit";

    public static final String SELF_EMPLOYED_JOB_TITLE_TOO_LONG = "self.employed.jobTitle.gt.max";

    public static final String COMPANY_JOB_EMPTY = "company.work.address.different";
    public static final String COMPANY_DIRECTOR_EMPTY = "company.director.empty";
    public static final String COMPANY_ADDRESS_EMPTY = "company.address.empty";

    public static final String COMPANY_NAME_TOO_LONG = "company.name.greater.than.limit";
    public static final String COMPANY_ADDRESS_TOO_LONG = "company.address.greater.than.limit";
    public static final String COMPANY_POSTCODE_TOO_LONG = "company.postcode.greater.than.limit";
    
    public static final String WORK_CURRENT_ROLE_DURATION_EMPTY = "work.current.role.duration.cannot.be.empty";
    public static final String WORK_START_DATE_INVALID = "work.startDate.invalid";
    public static final String WORK_DAYS_PER_WEEK_EMPTY = "work.daysPerWeek.cannot.be.empty";
    public static final String WORK_HOURS_PER_WEEK = "work.hoursPerWeek.cannot.be.empty";
    public static final String WORK_START_DATE_NOT_SIX_WEEKS = "work.sixWeekStartDate";
    public static final String WORK_START_DATE_NOT_IN_FUTURE = "work.futureStartDate";

    public static final String WORK_DAYS_PER_WEEK_TOO_LONG = "work.daysPerWeek.greater.than.limit";
    public static final String WORK_HOURS_PER_WEEK_TOO_LONG = "work.hoursPerWeek.greater.than.limit";

    public static final String MANAGER_FULLNAME_EMPTY = "manager.fullname.empty";
    public static final String MANAGER_CONTACT_EMPTY = "manager.contact.empty";
    public static final String MANAGER_FULLNAME_TOO_LONG = "manager.fullname.too.long";
    public static final String MANAGER_PHONE_TOO_LONG = "manager.phone.too.long";
    public static final String MANAGER_EMAIL_TOO_LONG = "manager.email.too.long";
    public static final String MANAGER_EMAIL_INVALID = "manager.email.invalid";

    public static final String CONDITION_DESCRIPTION_EMPTY = "condition.description.empty";
    public static final String CONDITION_DESCRIPTION_TOO_LONG = "condition.description.greater.than.limit";

    public static final String TRAVEL_IMPACT_Q1_EMPTY = "travel.impact.q1.empty";
    public static final String TRAVEL_IMPACT_Q2_EMPTY = "travel.impact.q2.empty";
    public static final String TRAVEL_IMPACT_Q3_EMPTY = "travel.impact.q3.empty";
    public static final String TRAVEL_IMPACT_Q4_EMPTY = "travel.impact.q4.empty";
    public static final String TRAVEL_IMPACT_Q5_EMPTY = "travel.impact.q5.empty";
    public static final String TRAVEL_IMPACT_Q3_MUST_BE_EMPTY = "travel.impact.q3.must.be.empty";
    public static final String TRAVEL_IMPACT_Q5_MUST_BE_EMPTY = "travel.impact.q5.empty";

    public static final String TRAVEL_IMPACT_Q1_TOO_LONG = "travel.impact.q1.greater.than.limit";
    public static final String TRAVEL_IMPACT_Q3_TOO_LONG = "travel.impact.q3.greater.than.limit";
    public static final String TRAVEL_IMPACT_Q5_TOO_LONG = "travel.impact.q5.greater.than.limit";
    
    public static final String WORK_IMPACT_Q1_EMPTY = "work.impact.q1.empty";
    public static final String WORK_IMPACT_Q2_EMPTY = "work.impact.q2.empty";
    public static final String WORK_IMPACT_Q3_EMPTY = "work.impact.q3.empty";
    public static final String WORK_IMPACT_Q4_EMPTY = "work.impact.q4.empty";
    public static final String WORK_IMPACT_Q5_EMPTY = "work.impact.q5.empty";
    public static final String WORK_IMPACT_Q3_MUST_BE_EMPTY = "work.impact.q3.must.be.empty";
    public static final String WORK_IMPACT_Q5_MUST_BE_EMPTY = "work.impact.q5.empty";

    public static final String WORK_IMPACT_Q1_TOO_LONG = "work.impact.q1.greater.than.limit";
    public static final String WORK_IMPACT_Q3_TOO_LONG = "work.impact.q3.greater.than.limit";
    public static final String WORK_IMPACT_Q5_TOO_LONG = "work.impact.q5.greater.than.limit";
    
    public static final String DECLARATION_AGREED_EMPTY = "declaration.agreed.empty";
    
    public static final String CLAIMANT_SECTION_MISSING = "claimant.section.empty";
    public static final String THIRD_PARTY_SECTION_MISSING = "claimant.section.empty";
    public static final String JOB_STATUS_SECTION_MISSING = "claimant.section.empty";
    //EMPLOYED, SELF EMPLOYED, COMPANY DIRECTOR
    public static final String WORK_SECTION_MISSING = "claimant.section.empty";
    public static final String DECLARATION_SECTION_MISSING = "claimant.section.empty";
    
    public static final String FEEDBACK_TYPE_EMPTY = "feedback.feedbackType.cannot.be.empty";
    public static final String FEEDBACK_SUGGESTION_TOO_LONG = "feedback.suggestion.greater.than.limit";

}
