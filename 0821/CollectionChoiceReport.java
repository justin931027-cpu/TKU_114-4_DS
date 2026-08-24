import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Queue;

public class CollectionChoiceReport {
    public static void main(String[] args) {

        System.out.println("1. 保留搜尋紀錄且允許重複");
        List<String> searchHistory = new ArrayList<>();

        searchHistory.add("Java");
        searchHistory.add("Python");
        searchHistory.add("Java");

        System.out.println("Interface: List");
        System.out.println("Implementation: ArrayList");
        System.out.println("結果: " + searchHistory);

        System.out.println();

        System.out.println("2. 保存不重複會員編號");
        Set<String> memberIds = new HashSet<>();

        memberIds.add("M001");
        memberIds.add("M002");
        memberIds.add("M001");

        System.out.println("Interface: Set");
        System.out.println("Implementation: HashSet");
        System.out.println("結果: " + memberIds);

        System.out.println();

        System.out.println("3. 以學號查詢成績");
        Map<String, Integer> scores = new HashMap<>();

        scores.put("S001", 90);
        scores.put("S002", 85);
        scores.put("S003", 78);

        System.out.println("Interface: Map");
        System.out.println("Implementation: HashMap");
        System.out.println("S002 成績: " + scores.get("S002"));

        System.out.println();

        System.out.println("4. 依到達順序處理列印工作");
        Queue<String> printQueue = new LinkedList<>();

        printQueue.offer("文件A");
        printQueue.offer("文件B");
        printQueue.offer("文件C");

        System.out.println("Interface: Queue");
        System.out.println("Implementation: LinkedList");
        System.out.println("第一個列印: " + printQueue.poll());
        System.out.println("剩餘工作: " + printQueue);

        System.out.println();

        System.out.println("5. 復原最近操作");
        Stack<String> undoStack = new Stack<>();

        undoStack.push("輸入 Hello");
        undoStack.push("刪除文字");
        undoStack.push("修改標題");

        System.out.println("Interface: Stack");
        System.out.println("Implementation: Stack");
        System.out.println("復原操作: " + undoStack.pop());
        System.out.println("剩餘操作: " + undoStack);
    }
}