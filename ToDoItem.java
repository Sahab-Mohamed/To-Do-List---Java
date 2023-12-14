package toDoListPackage;

public class ToDoItem implements Comparable<ToDoItem> {
    private static int nextOrder = 1;

    private String task;
    private int priority;
    private int order;

    public ToDoItem(String task, int priority) {
        this.task = task;
        this.priority = priority;
        this.order = nextOrder++;
    }

    

    public String getTask() {
		return task;
	}
    public void setPriority(int priority) {
		this.priority = priority;	
	}
    public int getPriority() {
		return priority;	
    }
    

	@Override
    public int compareTo(ToDoItem other) {
        if (this.priority == other.priority) {
            return Integer.compare(this.order, other.order);
        }
        return Integer.compare(other.priority, this.priority);
    }

}

