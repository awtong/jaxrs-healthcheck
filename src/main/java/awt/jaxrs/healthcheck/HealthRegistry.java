package awt.jaxrs.healthcheck;

import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HealthRegistry {
    @XmlElement(name = "status")
    private Collection<HealthStatus> statuses;

    public boolean addStatuses(final Collection<HealthStatus> statuses) {
	if (this.statuses == null) {
	    this.statuses = new HashSet<>();
	}

	return (statuses != null) && this.statuses.addAll(statuses);
    }

    /**
     *
     * @return collection of HealthStatus objects.
     */
    public Collection<HealthStatus> getStatuses() {
	return this.statuses == null ? Collections.emptyList() : this.statuses;
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
