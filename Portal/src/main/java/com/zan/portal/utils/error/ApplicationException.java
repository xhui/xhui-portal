package com.zan.portal.utils.error;

import com.zan.portal.utils.ErrorCode;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 2764292105006576012L;
	private ErrorCode code;
	private Exception ex;

	public ApplicationException(ErrorCode code) {
		super();
		this.code = code;
	}

	public ApplicationException(ErrorCode code, Exception ex) {
		super(ex);
		this.code = code;
	}

	public ErrorCode getCode() {
		return code;
	}

	public Exception getEx() {
		return ex;
	}
}