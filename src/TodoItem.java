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
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof TodoItem item)){
            return false;
        }
        return id == item.id;
    }

    @Override
    public int hashCode(){
        return id;
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
