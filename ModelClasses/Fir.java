package ModelClasses;
import java.time.*;

public class Fir {
    private String firNumber;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private String status;
    private Criminal criminal;

    public Fir(String firNumber, String description, String location, Criminal criminal) {
        this.firNumber = firNumber;
        this.description = description;
        this.location = location;
        this.criminal = criminal;
        this.dateTime = LocalDateTime.now();
        this.status = "Open";
    }

    public String getFirNumber() {
        return firNumber;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "FIR{" +
                "firNumber='" + firNumber + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", criminal=" + criminal +
                '}';
    }

}
