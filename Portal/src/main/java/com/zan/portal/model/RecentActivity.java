package com.zan.portal.model;

import java.io.Serializable;

public class RecentActivity implements Serializable
{
	private static final long	serialVersionUID	= 1529381525850228226L;
	private String				imgUrl;
	private String				desc;
	private String				linkUrl;

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getLinkUrl()
	{
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}
}
