package com.zan.portal.utils;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	public DefaultExceptionHandlerFactory(ExceptionHandlerFactory p) {
		this.parent = p;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new DefaultExceptionHandler(parent.getExceptionHandler());
	}
}
