import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TodoApp {

    private Map<Integer,TodoItem> tasks = new HashMap<>();
    private int id = 1;
    private Scanner sc = new Scanner(System.in);

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
                    int taskId =  sc.nextInt();
                    TodoItem task = tasks.get(taskId);
                    displayTask(task);
                    break;
                case 4:
                    System.out.print("Enter Task ID: ");
                    int updateId =  sc.nextInt();
                    sc.nextLine();
                    updateTodo(updateId);
                    break;
                case 5:
                    System.out.print("Enter Task ID: ");
                    int delId =  sc.nextInt();
                    deleteTodo(delId);
                    break;
                case 6:
                    System.out.print("Enter Task ID: ");
                    int statusId =  sc.nextInt();
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

    public void getTodoByTitle(String title) {
        boolean found = false;
        for(TodoItem task : tasks.values()){
            if(task.getTitle().equalsIgnoreCase(title)){
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
        for(TodoItem task : tasks.values()){
            if(task.getStatus().equals(status)){
                found = true;
                displayTask(task);
            }
        }
        if(!found){
            System.out.println("No Tasks found");
        }
    }

    public void displayTask(TodoItem task) {
        if(task == null){
            System.out.println("Task not found");
            return;
        }
        System.out.println("ID: " + task.getId() +
                " Title: " + task.getTitle() +
                " Description: " + task.getDescription() +
                " Status: " + task.getStatus());
    }

    public void createTodo(){
        System.out.print("Title:");
        String title = sc.nextLine();

        System.out.print("Description:");
        String description = sc.nextLine();

        TodoItem task = new TodoItem(id++,title,description,Status.PENDING);
        tasks.put(task.getId(),task);
        System.out.println("Todo Created");
    }

    public void getTodos(){
        if(tasks.isEmpty()){
            System.out.println("No Tasks found");
            return;
        }
        for(TodoItem task : tasks.values()){
            displayTask(task);
        }
    }

    public void updateTodo(int taskId){
        TodoItem task = tasks.get(taskId);
        if(task == null){
            System.out.println("Task not found");
            return;
        }

        System.out.print("New Title: ");
        task.setTitle(sc.nextLine());

        System.out.print("New Description: ");
        task.setDescription(sc.nextLine());

        task.setStatus(readStatus());
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

    public void deleteTodo(int delId) {
        TodoItem task = tasks.get(delId);
        if(task == null){
            System.out.println("Task not found");
            return;
        }
        tasks.remove(delId);
        System.out.println("Task deleted successfully");
    }

    public void updateStatus(int statusId) {
        TodoItem task = tasks.get(statusId);
        if(task == null){
            System.out.println("Task not found");
            return;
        }
        task.setStatus(readStatus());
    }
}