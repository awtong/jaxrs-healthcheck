package awt.jaxrs.healthcheck;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class HealthCheckResourceTest {

    @Test
    public void testGetHeartbest() {
	final HealthCheckResource resource = new HealthCheckResource();
	final Response response = resource.getHeartbeat();

	assertThat(response, is(notNullValue()));
	assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void testGetDetailedHealthStatusNoHealthChecks() {
	final HealthCheckResource resource = new HealthCheckResource();
	final Response response = resource.getDetailedHealthStatus();

	assertThat(response, is(notNullValue()));
	assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
    }
}
