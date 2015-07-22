package se.tada.tutorial.hello.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import se.tada.tutorial.hello.SingletonBean;

public class SingletonBeanTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(SingletonBean.class);
	}

	@Test
	public void test() {
		String hello = target("singleton").request().get(String.class);
		assertEquals("OK GET uri=http://localhost:9998/singleton", hello);
	}

	@Test
	public void testCounter() {
		Integer count = target("singleton/counter").request().get(Integer.class);
		assertNotNull(count);
		assertEquals(42, count.intValue());
		count = target("singleton/counter").request().get(Integer.class);
		assertNotNull(count);
		assertEquals(43, count.intValue());
	}

	@Test
	public void testUpdateCounter() {
		Response response = target("singleton/counter").request().put(Entity.entity("15", MediaType.TEXT_PLAIN));
		assertNotNull(response);
		assertEquals(204, response.getStatus());
		Integer count = target("singleton/counter").request().get(Integer.class);
		assertNotNull(count);
		assertEquals(15, count.intValue());
	}
}
