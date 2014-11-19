/**
 * 
 */
package com.xplink.random_cm.service;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * Simple base class to implement AJAX controller. This class tended to bind with only ONE view
 * named AJAX_VIEW_NAME which result is only print out of attribute named AJAX_RESULT_ATTRIBUTE.
 * <p>
 * <b>Note:</b> This class just offer static String attribute for convenient usage on top of
 * {@link SimpleFormController}.
 * 
 * @author Sorawit Laosinchai
 */
public class SimpleAjaxController extends SimpleFormController {

	public static String AJAX_VIEW_NAME = "editEvent";

	public static String AJAX_RESULT_ATTRIBUTE = "ajaxResult";

	public ModelAndView getModelAndViewWithResult(String resultString) {
		ModelAndView mv = new ModelAndView(AJAX_VIEW_NAME);
		mv.addObject(AJAX_RESULT_ATTRIBUTE, resultString);

		return mv;
	}
}
