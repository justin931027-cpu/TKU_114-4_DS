package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q11_BstHashDirectory {

    // BST Node
    private static class Node {
        int id;
        Node left;
        Node right;

        Node(int id) {
            this.id = id;
        }
    }

    // BST Root
    private Node root;

    // HashMap：id → name
    private Map<Integer, String> names;

    // Constructor
    public Q11_BstHashDirectory() {
        root = null;
        names = new HashMap<>();
    }

    // 新增 id / name
    public boolean add(int id, String name) {

        // id 必須大於 0
        if (id <= 0) {
            return false;
        }

        // name 必須 trim 後非空
        if (name == null) {
            return false;
        }

        name = name.trim();

        if (name.isEmpty()) {
            return false;
        }

        // 重複 id
        if (names.containsKey(id)) {
            return false;
        }

        // 加入 BST
        root = insert(root, id);

        // 加入 HashMap
        names.put(id, name);

        return true;
    }

    // BST Insert
    private Node insert(Node node, int id) {

        // 找到空位置
        if (node == null) {
            return new Node(id);
        }

        if (id < node.id) {
            node.left = insert(node.left, id);
        } else if (id > node.id) {
            node.right = insert(node.right, id);
        }

        return node;
    }

    // 透過 HashMap 直接找 name
    public String findName(int id) {
        return names.get(id);
    }

    // 移除 id
    public boolean remove(int id) {

        // HashMap 不存在
        if (!names.containsKey(id)) {
            return false;
        }

        // 從 BST 移除
        root = delete(root, id);

        // 從 HashMap 移除
        names.remove(id);

        return true;
    }

    // BST Delete
    private Node delete(Node node, int id) {

        if (node == null) {
            return null;
        }

        // 往左子樹找
        if (id < node.id) {
            node.left = delete(node.left, id);
        }

        // 往右子樹找
        else if (id > node.id) {
            node.right = delete(node.right, id);
        }

        // 找到了
        else {

            // 沒有左子樹
            if (node.left == null) {
                return node.right;
            }

            // 沒有右子樹
            if (node.right == null) {
                return node.left;
            }

            // 左右子樹都有
            // 找右子樹中最小的 Node
            Node successor = findMin(node.right);

            // 用 successor 取代目前 Node
            node.id = successor.id;

            // 刪除原本的 successor
            node.right = delete(node.right, successor.id);
        }

        return node;
    }

    // 找 BST 最小值
    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // 找出 low ~ high 範圍內的 id
    public List<Integer> idsBetween(int low, int high) {

        List<Integer> result = new ArrayList<>();

        // low > high → 空 List
        if (low > high) {
            return result;
        }

        // 使用 inorder traversal
        // 因為 BST inorder 本身就是遞增
        rangeSearch(root, low, high, result);

        return result;
    }

    // Range Search
    private void rangeSearch(
            Node node,
            int low,
            int high,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        // 如果 node.id > low
        // 左邊可能還有符合條件的資料
        if (node.id > low) {
            rangeSearch(node.left, low, high, result);
        }

        // 在範圍內
        if (node.id >= low && node.id <= high) {
            result.add(node.id);
        }

        // 如果 node.id < high
        // 右邊可能還有符合條件的資料
        if (node.id < high) {
            rangeSearch(node.right, low, high, result);
        }
    }

    // 目前資料筆數
    public int size() {
        return names.size();
    }
}