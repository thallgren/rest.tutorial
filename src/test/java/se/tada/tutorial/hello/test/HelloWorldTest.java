package se.tada.tutorial.hello.test;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import se.tada.tutorial.hello.HelloWorld;

public class HelloWorldTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(HelloWorld.class);
	}

	@Test
	public void test() {
		final String hello = target("hello").request().get(String.class);
		assertEquals("JAX-RS säger \"Hej Världen\"", hello);
	}
}
