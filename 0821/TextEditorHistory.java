import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();
    private String text = "";

    public void edit(String newText) {
        undoStack.push(text);
        text = newText;

        redoStack.clear();

        printState("新增操作");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("沒有可以 Undo 的內容");
            printState("Undo");
            return;
        }

        redoStack.push(text);
        text = undoStack.pop();

        printState("Undo");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("沒有可以 Redo 的內容");
            printState("Redo");
            return;
        }

        undoStack.push(text);
        text = redoStack.pop();

        printState("Redo");
    }

    public void printState(String action) {
        System.out.println("=== " + action + " ===");
        System.out.println("目前文字：" + text);
        System.out.println("undo stack：" + undoStack);
        System.out.println("redo stack：" + redoStack);
        System.out.println();
    }

    public static void main(String[] args) {

        TextEditorHistory editor = new TextEditorHistory();

        editor.edit("Hello");
        editor.edit("Hello Java");
        editor.edit("Hello Java World");

        editor.undo();
        editor.undo();

        editor.redo();

        editor.edit("Hello Python");

        editor.redo();
        editor.undo();
    }
}