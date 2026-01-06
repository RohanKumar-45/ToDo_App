enum Status{
    PENDING ,
    IN_PROGRESS ,
    COMPLETED
}

public class TodoItem {

    private int id;
    private String title;
    private String description;
    private Status status;

    public TodoItem(int id,String title,String description,Status status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public Status getStatus(){
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
