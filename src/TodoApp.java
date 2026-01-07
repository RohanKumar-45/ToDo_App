import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TodoApp {

    private long id;
    private Scanner sc = new Scanner(System.in);
    private JSONArray tasks = TodoUtil.readTodoArray();

    public TodoApp(){
        id = getNextId();
    }

    private long getNextId() {
        long max = 0;
        for(Object obj : tasks){
            JSONObject task = (JSONObject) obj;
            long taskId = (long) task.get("id");

            if(taskId > max){
                max = taskId;
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        new TodoApp().start();
    }

    public void start(){

        while(true) {
            System.out.println("\n- TODO MENU -");
            System.out.println("1. Create Todo");
            System.out.println("2. Get Todo");
            System.out.println("3. Get a Single Todo Item");
            System.out.println("4. Update Todo");
            System.out.println("5. Delete Todo");
            System.out.println("6. Update Status");
            System.out.println("7. Get TodoItem by status");
            System.out.println("8. Search TodoItem By Title");
            System.out.println("9. Exit");
            System.out.print("Choose option:");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createTodo();
                    break;
                case 2:
                    getTodos();
                    break;
                case 3:
                    System.out.print("Enter Task ID: ");
                    long taskId =  sc.nextInt();
                    JSONObject task = findTaskById(taskId);
                    displayTask(task);
                    break;
                case 4:
                    System.out.print("Enter Task ID: ");
                    long updateId =  sc.nextLong();
                    sc.nextLine();
                    updateTodo(updateId);
                    break;
                case 5:
                    System.out.print("Enter Task ID: ");
                    long delId =  sc.nextLong();
                    deleteTodo(delId);
                    break;
                case 6:
                    System.out.print("Enter Task ID: ");
                    long statusId =  sc.nextLong();
                    sc.nextLine();
                    updateStatus(statusId);
                    break;
                case 7:
                    Status status = readStatus();
                    getTodoByStatus(status);
                    break;
                case 8:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    getTodoByTitle(title);
                    break;
                case 9:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public JSONObject findTaskById(long id){
        for(Object obj : tasks){
            JSONObject task = (JSONObject) obj;

            if((long) task.get("id") == id){
                return task;
            }
        }
        return null;
    }
    public void getTodoByTitle(String title) {
        boolean found = false;
        for(Object obj : tasks){
            JSONObject task = (JSONObject) obj;
            String taskTitle = task.get("title").toString();

            if(taskTitle.equalsIgnoreCase(title)){
                found = true;
                displayTask(task);
            }
        }
        if(!found){
            System.out.println("No Tasks found");
        }
    }

    public void getTodoByStatus(Status status) {
        boolean found = false;
        for(Object obj : tasks){
            JSONObject task = (JSONObject) obj;
            if(task.get("status").equals(status.name())){
                found = true;
                displayTask(task);
            }
        }
        if(!found){
            System.out.println("No Tasks found");
        }
    }

    public void displayTask(JSONObject task) {
        if(task == null){
            System.out.println("Task not found");
            return;
        }
        System.out.println("ID: " + task.get("id") +
                " Title: " + task.get("title") +
                " Description: " + task.get("description") +
                " Status: " + task.get("status"));
    }

    public void createTodo(){
        System.out.print("Title:");
        String title = sc.nextLine();

        System.out.print("Description:");
        String description = sc.nextLine();

        JSONObject task = new JSONObject();

        task.put("id" , id++);
        task.put("title", title);
        task.put("description", description);
        task.put("status", Status.PENDING.name());

        tasks.add(task);
        TodoUtil.writeTodoArray(tasks);
        System.out.println("Todo Created");
    }

    public void getTodos(){
        if(tasks.isEmpty()){
            System.out.println("No Tasks found");
            return;
        }
        for(Object obj : tasks){
            JSONObject task = (JSONObject) obj;
            displayTask(task);
        }
    }

    public void updateTodo(long taskId){

        JSONObject task = findTaskById(taskId);
        if(task == null){
            System.out.println("No Task Found");
            return;
        }

        System.out.print("Title:");
        String title = sc.nextLine();

        System.out.print("Description:");
        String description = sc.nextLine();

        Status status = readStatus();

        task.put("title" , title);
        task.put("description" , description);
        task.put("status" , status.name());
        TodoUtil.writeTodoArray(tasks);

    }

    public Status readStatus() {
        while(true){
            System.out.print("Status (PENDING / IN_PROGRESS / COMPLETED): ");
            String input = sc.nextLine().toUpperCase();

            try{
                return Status.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Status input.");
            }
        }
    }

    public void deleteTodo(long delId) {
        Iterator<Object> iterator = tasks.iterator();
        while (iterator.hasNext()){
            JSONObject task = (JSONObject) iterator.next();
            long taskId = (long) task.get("id");

            if(taskId == delId){
                iterator.remove();
                TodoUtil.writeTodoArray(tasks);
                System.out.println("Task deleted");
                return;
            }
        }
        System.out.println("Task Not Found");
    }

    public void updateStatus(long statusId) {

        JSONObject task = findTaskById(statusId);
        if(task == null){
            System.out.println("No Task Found");
            return;
        }

        Status status = readStatus();

        task.put("status" , status.name());
        TodoUtil.writeTodoArray(tasks);
        System.out.println("Status updated");
    }
}