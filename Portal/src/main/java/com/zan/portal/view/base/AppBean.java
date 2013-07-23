package com.zan.portal.view.base;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.utils.AppCfg;
import com.zan.portal.utils.Constant;

@Component
@Scope("singleton")
public class AppBean implements Serializable
{
	private static final long	serialVersionUID	= 1456334171981592128L;

	@Inject
	private AppCfg				appCfg;

	/**
	 * Get static resource file path for CSS\JS\Images
	 * 
	 * @return URL for static resource files
	 */
	public String getResourcePath()
	{
		return appCfg.getProperty(Constant.APP_PROPERTY_KEY_RESOURCE_URL);
	}
}