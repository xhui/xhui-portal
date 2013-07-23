package com.zan.portal.view.landingpage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zan.portal.model.RecentActivity;
import com.zan.portal.view.base.AppBean;

@Component
@Scope("request")
public class RecentActivityBean implements Serializable
{
	private static final long		serialVersionUID	= -4733669591503939365L;

	@Inject
	private AppBean					appBean;

	private List<RecentActivity>	activities;

	@PostConstruct
	private void init()
	{
		activities = new ArrayList<RecentActivity>();

		String imgUrlPrefix = appBean.getResourcePath() + "/images/";
		RecentActivity act = new RecentActivity();
		act.setImgUrl(imgUrlPrefix + "slider-image1.jpg");
		act.setDesc("This is a test 1.");
		act.setLinkUrl("#");
		activities.add(act);

		act = new RecentActivity();
		act.setImgUrl(imgUrlPrefix + "slider-image2.jpg");
		act.setDesc("This is a test 2.");
		act.setLinkUrl("#");
		activities.add(act);

		act = new RecentActivity();
		act.setImgUrl(imgUrlPrefix + "slider-image3.jpg");
		act.setDesc("This is a test 3.");
		act.setLinkUrl("#");
		activities.add(act);
	}

	public List<RecentActivity> getActivities()
	{
		return activities;
	}
}