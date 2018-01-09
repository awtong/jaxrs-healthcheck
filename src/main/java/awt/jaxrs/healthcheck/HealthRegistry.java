package awt.jaxrs.healthcheck;

import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HealthRegistry {
    @XmlElement(name = "status")
    private final Collection<HealthStatus> statuses = new HashSet<>();

    public boolean addStatuses(final Collection<HealthStatus> statuses) {
	return (statuses != null) && this.statuses.addAll(statuses);
    }

    /**
     *
     * @return collection of HealthStatus objects. Will never return
     *         <code>null</code>
     */
    public Collection<HealthStatus> getStatuses() {
	return this.statuses;
    }

    @XmlElement(name = "successful")
    public boolean isSuccessful() {
	for (final HealthStatus status : this.getStatuses()) {
	    if (!status.isSuccessful()) {
		return false;
	    }
	}

	return true;
    }

    public static HealthRegistry fromServiceLoader() {
	final HealthRegistry registry = new HealthRegistry();
	final ServiceLoader<HealthStatusProvider> providers = ServiceLoader.load(HealthStatusProvider.class);
	for (final HealthStatusProvider provider : providers) {
	    registry.addStatuses(provider.getHealthStatuses());
	}

	return registry;
    }
}
