package awt.jaxrs.healthcheck;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class HealthStatus {
    @XmlElement(name = "resource")
    private String resource;

    @XmlElement(name = "successful")
    private boolean successful;

    @XmlElement(name = "message")
    private String message;

    @XmlElement(name = "time")
    private Long time;

    public HealthStatus() {
    }

    public HealthStatus(final String resource) {
	this.resource = resource;
    }

    public void setResource(final String resource) {
	this.resource = resource;
    }

    public String getResource() {
	return this.resource;
    }

    public boolean isSuccessful() {
	return this.successful;
    }

    public void setSuccessful(final boolean successful) {
	this.successful = successful;
    }

    public String getMessage() {
	return this.message;
    }

    public void setMessage(final String message) {
	this.message = message;
    }

    public Long getTime() {
	return this.time;
    }

    public void setTime(final Long time) {
	this.time = time;
    }

    @Override
    public String toString() {
	return "HealthStatus [resource=" + this.resource + ", successful=" + this.successful + ", message="
		+ this.message + ", time=" + this.time + "]";
    }
}
