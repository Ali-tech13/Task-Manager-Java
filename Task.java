import java.time.LocalDate;
import java.time.LocalDateTime;
public class Task {
    LocalDate deadLine;
    LocalDateTime time;
    Priority priority;
    Status status;
    String title;
    String description;

    Task(String title, String description, Status status,Priority priority, LocalDateTime time, LocalDate deadLine){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.time = time;
        this.deadLine = deadLine;
    }
    public String toFileString(){
        return title + "|" + description + "|" + status;
    }
}
