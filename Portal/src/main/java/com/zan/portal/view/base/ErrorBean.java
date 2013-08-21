package com.zan.portal.view.base;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.utils.Constant;

@Component
@Scope("request")
public class ErrorBean {

	private String errorCode;
	private String errorMessage;

	@PostConstruct
	public void inti() {
		Map<String, Object> requestMap = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap();
		Object code = requestMap.get("javax.servlet.error.status_code");
		errorCode = (code == null ? Constant.UNKNOWN : code.toString());
		Object message = requestMap.get("javax.servlet.error.message");
		errorMessage = (message == null ? Constant.UNKNOWN : message.toString());
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}