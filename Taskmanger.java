import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class Taskmanger {
    ArrayList<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Taskmanger() {
        loadFromFile();
    }

    public void addTasks(String title, String description, Status status, Priority priority, LocalDateTime time, LocalDate deadLine) {
        Task task = new Task(title, description, status,priority, time, deadLine);
        tasks.add(task);
        saveToFile();
    }

    public void delTask(int index){
        tasks.remove(index - 1);
        saveToFile();
    }

    public void viewTask(){
        if(tasks.isEmpty()){
            System.out.println("No Tasks yet! Add one now.");
        }

        for(int i = 0; i < tasks.size(); i++){
            System.out.println((i + 1) + ". " + tasks.get(i).title);
        }
    }
    public void viewDetail(int index){
        Task task = tasks.get(index - 1);
        System.out.println("Title: " + task.title);
        System.out.println("Description: " + task.description);
        System.out.println("[Status]: " + task.status);
        System.out.println("[Priority]: " + task.priority);
        System.out.println("[Time And Date]: " + task.time.format(formatter) );
        System.out.println("[DEADLINE!]: " + task.deadLine);
    }
    public void changeTask(int index, int choice){
        Task task = tasks.get(index - 1);
        switch(choice){
            case 1:
                task.status = Status.COMPLETED;
                break;
            case 2:
                task.status = Status.NOT_COMPLETED;
                break;
            case 3:
                task.status = Status.PINNED;
                break;
            default:
                System.out.println("INVALID CHOICE!");
                return;
        }
        saveToFile();
    }
    public int getTaskCount(){
        return tasks.size();
    }
    public void editTask(int index,String newTitle, String newDescription, LocalDate newDeadLine){
        Task task = tasks.get(index - 1);
        task.title = newTitle;
        task.description = newDescription;
        task.deadLine = newDeadLine;
    }
    public void editTask(int index, LocalDate newDeadLine){
        Task task = tasks.get(index - 1);
        task.deadLine = newDeadLine;
    }
    public void Priority(int index, int choice){
        Task task = tasks.get(index - 1);
        switch(choice){
            case 1:
                task.priority = Priority.HIGH;
                break;
            case 2:
                task.priority = Priority.MEDIUM;
                break;
            case 3:
                task.priority = Priority.LOW;
                break;
            default:
                System.out.println("INVALID CHOICE!");
                return;
        }
        saveToFile();
    }
    public void SortByPriority(){
        tasks.sort((t1,t2) -> t1.priority.ordinal() - t2.priority.ordinal());
    }
    public void saveToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        }catch( IOException e) {
            System.out.println("Error saving tasks to file");
        }
    }
    public void loadFromFile(){
        File file = new File(FILE_NAME);

        if(!file.exists()) {
            return;
        }
        tasks.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                if(parts.length == 6){
                    String title = parts[0];
                    String description = parts[1];
                    Status status = Status.valueOf(parts[2]);
                    Priority priority = Priority.valueOf(parts[3]);
                    LocalDateTime time = LocalDateTime.parse(parts[4]);
                    LocalDate deadLine = LocalDate.parse(parts[5]);

                    Task task = new Task(title, description, status, priority,time,deadLine);
                    tasks.add(task);
                }
            }
        }
    catch (IOException e) {
            System.out.println("Error loading tasks from file.");
    }
    }
}

