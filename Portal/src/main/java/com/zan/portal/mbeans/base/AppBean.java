package com.zan.portal.mbeans.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.zan.portal.utils.Constant;

@ManagedBean(name = "AppBean")
@ApplicationScoped
public class AppBean
{
	private static final String	APP_PROPERTIES_FILE_PATH	= "app.properties";
	private static final String	RESOURCE_URL				= "resource_url";
	private static Properties	APP_PROPERTIES				= new Properties();
	static
	{
		try (InputStream is = AppBean.class.getClassLoader()
				.getResourceAsStream(APP_PROPERTIES_FILE_PATH))
		{
			APP_PROPERTIES.load(is);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getValue(String key)
	{
		if (key != null)
		{
			String val = APP_PROPERTIES.getProperty(key);
			if (val != null)
			{
				return val.trim();
			}
		}
		return Constant.EMPTY;
	}

	/**
	 * Get static resource file path for CSS\JS\Images
	 * 
	 * @return URL for static resource files
	 */
	public String getResourcePath()
	{
		return getValue(RESOURCE_URL);
	}
}