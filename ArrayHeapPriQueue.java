package toDoListPackage;

import java.util.Arrays;

public class ArrayHeapPriQueue<T extends Comparable<T>> implements PriQueueInterface<T> {
   
    private ToDoItem[] heapArray;
    private int size;
    private int capacity = 10;

    public ArrayHeapPriQueue() {
        heapArray = new ToDoItem[capacity];
        size = 0;
    }

    @Override
    public void enqueue(ToDoItem newTask) {
        if (size == capacity) {
            resizeHeap();
        }
        heapArray[size] = newTask;
        reHeapUp(size);
        size++;
    }

    @Override
    public ToDoItem dequeue() {
        if (size == 0) {
            System.out.println("The list is empty!");
            return null;
        } else {
            ToDoItem finishedItem = heapArray[0];
            heapArray[0] = heapArray[size - 1];
            heapArray[size - 1] = null;
            size--;
            reHeapDown(0);
            return finishedItem;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }

    public void promote(String task) {
        int index = findIndexByTask(task);
        if (index != -1) {
            heapArray[index].setPriority(heapArray[index].getPriority() - 1);
            reHeapUp(index);
        } else {
            System.out.println("Task not found.");
        }
    }

    public void demote(String task) {
        int index = findIndexByTask(task);
        if (index != -1) {
            heapArray[index].setPriority(heapArray[index].getPriority() + 1);
            reHeapDown(index);
        } else {
            System.out.println("Task not found.");
        }
    }

    private int findIndexByTask(String task) {
        for (int i = 0; i < size; i++) {
            if (heapArray[i].getTask().equals(task)) {
                return i;
            }
        }
        return -1;
    }

    private void reHeapUp(int nodeIndex) {
        int parentIndex = (nodeIndex - 1) / 2;

        if (parentIndex >= 0 && heapArray[nodeIndex].compareTo(heapArray[parentIndex]) > 0) {
            swap(nodeIndex, parentIndex);
            reHeapUp(parentIndex);
        }
    }

    private void reHeapDown(int rootIndex) {
        int leftChildIndex = 2 * rootIndex + 1;
        int rightChildIndex = leftChildIndex + 1;
        int largestIndex = rootIndex;

        if (leftChildIndex < size && heapArray[leftChildIndex].compareTo(heapArray[largestIndex]) > 0) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < size && heapArray[rightChildIndex].compareTo(heapArray[largestIndex]) > 0) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != rootIndex) {
            swap(rootIndex, largestIndex);
            reHeapDown(largestIndex);
        }
    }

    private void swap(int index1, int index2) {
        ToDoItem temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    private void resizeHeap() {
        int newCapacity = capacity * 2;
        heapArray = Arrays.copyOf(heapArray, newCapacity);
        capacity = newCapacity;
    }
    public ToDoItem[] getHeapArray() {
        return heapArray;
    }
}
