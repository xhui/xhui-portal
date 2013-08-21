package com.zan.portal.utils;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.zan.portal.utils.error.ApplicationException;

public class DefaultExceptionHandler extends ExceptionHandlerWrapper {
	// private static final Logger LOG = Logger
	// .getLogger(DefaultExceptionHandler.class.getName());
	private ExceptionHandler wrapper;

	public DefaultExceptionHandler(ExceptionHandler wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapper;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents()
				.iterator();
		while (i.hasNext()) {
			Throwable t = ((ExceptionQueuedEventContext) i.next().getSource())
					.getException();
			boolean isHandled = false;
			try {
				if (FacesContext.getCurrentInstance().getPartialViewContext()
						.isPartialRequest()) {
					Throwable tmpEx = getRootCause(t.getCause());
					String errorSummary = "Failed";
					String errorDetail = Constant.UNKNOWN;
					if (tmpEx instanceof ApplicationException) {
						ApplicationException aex = (ApplicationException) tmpEx;
						errorDetail = Utils.getErrorMessage(aex.getCode());
					} else {
						errorDetail = t.getLocalizedMessage();
					}
					isHandled = true;
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR, errorSummary,
							errorDetail);

					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} finally {
				// after exception is handled, remove it from queue
				if (isHandled) {
					i.remove();
				}
			}
		}
		// let the parent handle the rest
		getWrapped().handle();
	}
}