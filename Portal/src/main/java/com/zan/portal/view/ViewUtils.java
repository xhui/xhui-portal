package com.zan.portal.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.zan.portal.utils.Constant;
import com.zan.portal.utils.ErrorCode;
import com.zan.portal.utils.Utils;

public abstract class ViewUtils {

	public static void showFailMessage(ErrorCode code) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Failed", Utils.getErrorMessage(code));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void showSuccessMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Succeed", Utils.getMessage(Constant.OPERATION_SUCCEED));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void showInfoMessage(String code) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Note", Utils.getMessage(code));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}