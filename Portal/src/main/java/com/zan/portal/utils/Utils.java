package com.zan.portal.utils;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

@Component
public class Utils {

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static ApplicationContext getApplicationContext() {
		return FacesContextUtils.getRequiredWebApplicationContext(FacesContext
				.getCurrentInstance());
	}

	public static String getErrorMessage(ErrorCode code) {
		return getMessage(null == code ? null : code.toString());
	}

	public static String getMessage(String code, Object... args) {
		if (null == code) {
			return null;
		}
		String message = getApplicationContext().getMessage(code, args,
				Locale.getDefault());
		return message == null ? Constant.UNKNOWN : message;
	}
}