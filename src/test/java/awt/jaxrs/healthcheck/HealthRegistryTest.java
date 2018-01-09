package awt.jaxrs.healthcheck;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.junit.Test;

public class HealthRegistryTest {

    @Test
    public void testNoStatuses() {
	final HealthRegistry registry = new HealthRegistry();
	assertThat(registry.isSuccessful(), is(true));
    }

    @Test
    public void testAllSuccessfulStatuses() {
	final HealthRegistry registry = new HealthRegistry();
	final HealthStatus s1 = new HealthStatus("1");
	s1.setSuccessful(true);
	final HealthStatus s2 = new HealthStatus("2");
	s2.setSuccessful(true);

	final Collection<HealthStatus> statuses = new HashSet<>();
	statuses.add(s1);
	statuses.add(s2);
	assertThat(registry.addStatuses(statuses), is(true));
	assertThat(registry.isSuccessful(), is(true));
	assertThat(registry.getStatuses().size(), is(2));
    }

    @Test
    public void testNotAllSuccessfulStatuses() {
	final HealthRegistry registry = new HealthRegistry();
	final HealthStatus s1 = new HealthStatus("1");
	s1.setSuccessful(true);
	final HealthStatus s2 = new HealthStatus("2");
	s2.setSuccessful(false);

	final Collection<HealthStatus> statuses = new HashSet<>();
	statuses.add(s1);
	statuses.add(s2);

	assertThat(registry.addStatuses(statuses), is(true));
	assertThat(registry.isSuccessful(), is(false));
	assertThat(registry.getStatuses().size(), is(2));
    }

    @Test
    public void testCannotInsertNull() {
	final HealthRegistry registry = new HealthRegistry();
	final boolean added = registry.addStatuses(null);
	assertThat(added, is(false));
	assertThat(registry.getStatuses().isEmpty(), is(true));
    }

    @Test
    public void testCannotInsertEmpty() {
	final HealthRegistry registry = new HealthRegistry();
	final boolean added = registry.addStatuses(Collections.emptyList());
	assertThat(added, is(false));
	assertThat(registry.getStatuses().isEmpty(), is(true));
    }

    @Test
    public void testStatusesIsNotNullIfNotSet() {
	final HealthRegistry registry = new HealthRegistry();
	assertThat(registry.getStatuses(), is(notNullValue()));
    }
}
