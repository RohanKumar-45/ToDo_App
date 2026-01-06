

public class TodoItem {

    private int id;
    private String title;
    private String description;
    private String status;

    public TodoItem(int id,String title,String description,String status){
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

    public String getStatus(){
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
