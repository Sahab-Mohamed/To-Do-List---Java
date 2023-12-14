package toDoListPackage;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) {
        ArrayHeapPriQueue<ToDoItem> todoList = new ArrayHeapPriQueue<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Finish Task");
            System.out.println("3. Promote Task");
            System.out.println("4. Demote Task");
            System.out.println("5. Print ToDo List");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = scanner.nextLine();
                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();
                    ToDoItem newTask = new ToDoItem(task, priority);
                    todoList.enqueue(newTask);
                    break;

                case 2:
                    if (!todoList.isEmpty()) {
                        ToDoItem finishedTask = todoList.dequeue();
                        System.out.println("Finished Task: " + finishedTask.getTask());
                    } else {
                        System.out.println("ToDo list is empty.");
                    }
                    break;

                case 3:
                    System.out.print("Enter task to promote: ");
                    String taskToPromote = scanner.nextLine();
                    todoList.promote(taskToPromote);
                    break;

                case 4:
                    System.out.print("Enter task to demote: ");
                    String taskToDemote = scanner.nextLine();
                    todoList.demote(taskToDemote);
                    break;

                case 5:
                    System.out.println("ToDo List:\n");

                    // Create a copy of the priority queue
                    ArrayHeapPriQueue<ToDoItem> todoListCopy = new ArrayHeapPriQueue<>();
                    for (int i = 0; i < todoList.size(); i++) {
                        todoListCopy.enqueue(todoList.getHeapArray()[i]);
                    }

                    // Print the items in the copy without modifying the original priority queue
                    while (!todoListCopy.isEmpty()) {
                        ToDoItem taskItem = todoListCopy.dequeue();
                        System.out.println("Task: " + taskItem.getTask() + ", Priority: " + taskItem.getPriority());
                    }
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting ToDo List App. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
