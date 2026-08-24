class Task {
    String id;
    String title;

    Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "id=" + id + ", title=" + title;
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    private TaskNode head;
    private int size;

    public void addFirst(Task task) {
        TaskNode newNode = new TaskNode(task);

        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Task task) {
        TaskNode newNode = new TaskNode(task);

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        TaskNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        size++;
    }

    public Task findById(String id) {
        TaskNode current = head;

        while (current != null) {
            if (current.task.id.equals(id)) {
                return current.task;
            }

            current = current.next;
        }

        return null;
    }

    public boolean removeById(String id) {
        if (head == null) {
            return false;
        }

        if (head.task.id.equals(id)) {
            head = head.next;
            size--;
            return true;
        }

        TaskNode current = head;

        while (current.next != null) {
            if (current.next.task.id.equals(id)) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean insertAfter(String existingId, Task task) {
        TaskNode current = head;

        while (current != null) {
            if (current.task.id.equals(existingId)) {
                TaskNode newNode = new TaskNode(task);

                newNode.next = current.next;
                current.next = newNode;

                size++;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void printAll() {
        TaskNode current = head;

        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {

        TaskLinkedList tasks = new TaskLinkedList();

        tasks.addFirst(new Task("T002", "寫作業"));
        tasks.addFirst(new Task("T001", "上課"));
        tasks.addLast(new Task("T003", "讀書"));
        tasks.addLast(new Task("T004", "運動"));

        System.out.println("原始資料：");
        tasks.printAll();

        System.out.println();
        System.out.println("size = " + tasks.size());

        System.out.println();
        System.out.println("查詢 T003：");
        System.out.println(tasks.findById("T003"));

        System.out.println();
        System.out.println("在 T002 後插入 T005：");
        tasks.insertAfter("T002", new Task("T005", "吃晚餐"));
        tasks.printAll();

        System.out.println();
        System.out.println("刪除 T001：");
        System.out.println("刪除成功：" + tasks.removeById("T001"));
        tasks.printAll();

        System.out.println();
        System.out.println("刪除 T005：");
        System.out.println("刪除成功：" + tasks.removeById("T005"));
        tasks.printAll();

        System.out.println();
        System.out.println("查詢不存在的 T999：");
        System.out.println(tasks.findById("T999"));

        System.out.println();
        System.out.println("最後 size = " + tasks.size());
    }
}