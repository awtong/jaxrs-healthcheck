package awt.jaxrs.healthcheck;

import java.util.Collection;

public interface HealthStatusProvider {
    Collection<HealthStatus> getHealthStatuses();
}
