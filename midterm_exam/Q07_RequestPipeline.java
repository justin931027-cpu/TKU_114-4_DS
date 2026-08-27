import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((c == ')' && top != '(')
                        || (c == ']' && top != '[')
                        || (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null) {
                continue;
            }

            command = command.trim();

            if (command.isEmpty()) {
                continue;
            }

            if (command.startsWith("NORMAL ")) {
                String id = command.substring(7).trim();

                if (!id.isEmpty()) {
                    normalQueue.offer(id);
                }
            } else if (command.startsWith("URGENT ")) {
                String id = command.substring(7).trim();

                if (!id.isEmpty()) {
                    urgentQueue.offer(id);
                }
            } else if (command.equals("PROCESS")) {
                if (!urgentQueue.isEmpty()) {
                    result.add(urgentQueue.poll());
                } else if (!normalQueue.isEmpty()) {
                    result.add(normalQueue.poll());
                } else {
                    result.add("EMPTY");
                }
            }
        }

        return result;
    }
}