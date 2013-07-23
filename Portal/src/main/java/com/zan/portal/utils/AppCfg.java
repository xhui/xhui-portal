package com.zan.portal.utils;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AppCfg
{
	@Inject
	private Properties	appProperties;

	public String getProperty(String key)
	{
		if (key != null)
		{
			String val = appProperties.getProperty(key);
			if (val != null)
			{
				return val.trim();
			}
		}
		return Constant.EMPTY;
	}
}