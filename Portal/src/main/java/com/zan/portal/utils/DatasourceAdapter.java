package com.zan.portal.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DatasourceAdapter {

	private DriverManagerDataSource dmds;

	private String schema;
	private String host;
	private String port;
	private String userName;
	private String password;

	public DriverManagerDataSource buildDataSource() throws Throwable {
		String jsonData = System.getenv("VCAP_SERVICES");
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> actualObj = mapper.readValue(jsonData, Map.class);
		Iterator<?> iter = actualObj.entrySet().iterator();
		while (iter.hasNext()) {
			List<?> connections = (List<?>) ((Entry<?, ?>) iter.next())
					.getValue();
			for (Object o : connections) {
				Map<?, ?> tmp = (Map<?, ?>) o;
				if ("xhui-portal".equals(tmp.get("name"))) {
					Map<?, ?> credentialMap = (Map<?, ?>) tmp
							.get("credentials");
					schema = (String) credentialMap.get("name");
					host = (String) credentialMap.get("host");
					port = String.valueOf(credentialMap.get("port"));
					userName = (String) credentialMap.get("username");
					password = (String) credentialMap.get("password");
					dmds = new DriverManagerDataSource();
					dmds.setDriverClassName(Utils.getMessage("DBDriver"));
					dmds.setUrl(getUrl());
					dmds.setUsername(userName);
					dmds.setPassword(password);
					return dmds;
				}
			}
		}
		return null;
	}

	private String getUrl() {
		return Utils.getMessage("DBUrl", host, port, schema);
	}
}
