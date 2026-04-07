import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Main {
  public static void main(String[] args) {

    // TaskManger Program


    Scanner scanner = new Scanner(System.in);

    Taskmanger taskmanger = new Taskmanger();



    boolean isRunning = true;

    boolean wrongInput = true;



    while(isRunning) {
      System.out.println("\n------Welcome to TaskManger------");
      System.out.println("""
              1.add Task
              2.del Task
              3.view Tasks
              4.view Task Details
              5.Change Task status
              6.Edit Task
              7.Change Priority
              8.exit
              """);
      System.out.print("Choose (1-8): ");


      int Choice;
      try {
        Choice = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("ENTER A NUMBER BETWEEN (1-7)!");
        scanner.nextLine();
        continue;
      }

      if (Choice == 1) {
        System.out.print("Enter the title of the Task: ");
        String title = scanner.nextLine();
        System.out.print("Enter the description of the Task:");
        String description = scanner.nextLine();
        Status status = Status.NOT_COMPLETED;
        Priority priority = Priority.LOW;
        LocalDateTime time = LocalDateTime.now();
        LocalDate deadline = null;
        while(deadline == null) {
          try{
            System.out.print("Enter deadline (yyyy-MM-dd): ");
            String deadIineInput = scanner.nextLine();
            deadline = LocalDate.parse(deadIineInput);
          }
          catch(Exception e) {
            System.out.println("Enter deadline in format of (yyyy-MM-dd)!\n ");
          }
        }
        taskmanger.addTasks(title, description, status, priority, time, deadline);

        System.out.println("The Task have been added!");

      } else if (Choice == 2) {
        System.out.println("Enter the Task number to delete it: ");
        try{
          int taskNumber = scanner.nextInt();
          taskmanger.delTask(taskNumber);
          System.out.println("The Task have been deleted");
        }
        catch(IndexOutOfBoundsException e){
          System.out.println("There are only " + taskmanger.getTaskCount());
        }
      }
      else if (Choice == 3) {
        System.out.println("""
                Enter the way you want from (1-3)
                1.Show Tasks
                2.Show Tasks by Priority
                """);
        while (wrongInput){
          try{
            int number = scanner.nextInt();
            if(number == 1){
              taskmanger.viewTask();
              break;
            }
            else if (number == 2){
              taskmanger.SortByPriority();
              taskmanger.viewTask();
              break;
            }
            else{
              System.out.println("CHOOSE 1 OR 2");
            }
          } catch (Exception e) {
            System.out.println("INVALID CHOICE!");
            scanner.nextLine();
          }
        }
        }

      else if (Choice == 4) {
        System.out.println("Enter the number of the task: ");
        try{
          int number = scanner.nextInt();
          taskmanger.viewDetail(number);
        }
        catch(IndexOutOfBoundsException e){
          System.out.println("There are only " + taskmanger.getTaskCount());
        }
      }
      else if (Choice == 5) {
        while (wrongInput){
          try{
            System.out.println("Enter the number of the Task to change: ");
            int taskNumber = scanner.nextInt();
            if(taskNumber < 0 || taskNumber > taskmanger.getTaskCount()){
              System.out.println("There are only " + taskmanger.getTaskCount());
              continue;
            }
            System.out.println("""
                  CHOOSE FROM (1-3)
                  1.COMPLETED
                  2.NOT COMPLETED
                  3.PINNED
                  """);
            int statusChoice = scanner.nextInt();
            if(statusChoice < 0 || statusChoice > 3){
              System.out.println("INVALID CHOICE!");
              continue;
            }
            taskmanger.changeTask(taskNumber, statusChoice);
            System.out.println("The Task Status Have Been changed!");
            break;
          }
          catch(Exception e){
            System.out.println("INVALID CHOICE!");
            scanner.nextLine();
          }
        }
      }
      else if(Choice == 6){
        while(wrongInput){
          String Change = "";
          LocalDate newDeadLine = null;
          try{
            System.out.print("Enter the number of the Task: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("DO YOU WANT TO CHANGE EVERYTHING? (yes or no): ");
            Change = scanner.nextLine();
            if(number < 1 || number > taskmanger.getTaskCount() ){
              System.out.println("There are only " + taskmanger.getTaskCount() + " Task");
            }
            else if(Change.equalsIgnoreCase("yes")){
              System.out.println("Enter The new title: ");
              String newTitle = scanner.nextLine();
              System.out.println("Enter The new description: ");
              String newDescription = scanner.nextLine();
              System.out.println("Enter The new Deadline in format (yyyy-MM-dd): ");
              String deadIineInput = scanner.nextLine();
              newDeadLine = LocalDate.parse(deadIineInput);
              taskmanger.editTask(number,newTitle,newDescription,newDeadLine);
              System.out.println("The Task Have Been Edited!");
              break;
            }
            else if (Change.equalsIgnoreCase("no")) {
              System.out.println("Enter The New DeadLine: ");
              String deadIineInput = scanner.nextLine();
              newDeadLine = LocalDate.parse(deadIineInput);
              taskmanger.editTask(number , newDeadLine);
            }
          }
          catch(Exception e){
            System.out.println("INVALID CHOICE!");
            scanner.nextLine();
          }
        }
      }
      else if(Choice == 7) {
        while(wrongInput){
          System.out.println("Enter the number of the Task");
          try{
            int number = scanner.nextInt();
            scanner.nextLine();
            if(number < 1 || number > taskmanger.getTaskCount() ){
              System.out.println("There are only " + taskmanger.getTaskCount());
            }
            else{
              System.out.println("""
                      To Change Priority Enter From (1-3)
                      1.HIGH
                      2.MEDIUM
                      3.Low
                      """);
              int PriorityChoice = scanner.nextInt();
              if (PriorityChoice < 1 || PriorityChoice > 3) {
                System.out.println("INVALID CHOICE!");
                continue;
              }
              taskmanger.Priority(number, PriorityChoice);
              System.out.println("The Task Priority Have Been changed!");
              break;
            }
          }
          catch (Exception e) {
            System.out.println("INVALID CHOICE");
            scanner.nextLine();
          }
        }
      }
      else if (Choice == 8) {
        isRunning = false;
        System.out.println("THANKS FOR USING TASK MANGER!");
      }
      else{
        System.out.println("PLEASE ENTER A NUMBER BETWEEN (1-7)!");
      }
    }



    scanner.close();
  }
}