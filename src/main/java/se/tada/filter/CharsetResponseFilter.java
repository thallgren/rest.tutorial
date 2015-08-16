package se.tada.filter;

import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;

@Singleton
@Provider
public class CharsetResponseFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) {
		MediaType type = response.getMediaType();
		if(type == null)
			return;

		Map<String, String> params = type.getParameters();
		if(params.containsKey(MediaType.CHARSET_PARAMETER))
			return;

		params = Maps.newHashMap(params);
		params.put(MediaType.CHARSET_PARAMETER, Charsets.UTF_8.name());
		response.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, new MediaType(type.getType(), type.getSubtype(), params));
	}
}
