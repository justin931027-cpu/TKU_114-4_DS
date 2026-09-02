import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {

    // 使用者 -> 好友集合
    private Map<String, Set<String>> friends;

    public SocialNetworkGraph() {
        friends = new HashMap<>();
    }

    // 新增使用者
    public void addUser(String username) {

        if (!friends.containsKey(username)) {
            friends.put(username, new HashSet<>());
        }
    }

    // 判斷使用者是否存在
    public boolean containsUser(String username) {
        return friends.containsKey(username);
    }

    // 新增好友關係
    public void addFriend(String user1, String user2) {

        if (user1.equals(user2)) {
            return;
        }

        addUser(user1);
        addUser(user2);

        friends.get(user1).add(user2);
        friends.get(user2).add(user1);
    }

    // 解除好友
    public void removeFriend(
            String user1,
            String user2) {

        if (!friends.containsKey(user1) ||
            !friends.containsKey(user2)) {
            return;
        }

        friends.get(user1).remove(user2);
        friends.get(user2).remove(user1);
    }

    // 查詢好友
    public List<String> getFriends(String username) {

        if (!friends.containsKey(username)) {
            return new ArrayList<>();
        }

        List<String> result =
            new ArrayList<>(friends.get(username));

        result.sort(String::compareTo);

        return result;
    }

    // 判斷是否為好友
    public boolean isFriend(
            String user1,
            String user2) {

        if (!friends.containsKey(user1)) {
            return false;
        }

        return friends.get(user1).contains(user2);
    }

    // 查詢共同好友
    public List<String> commonFriends(
            String user1,
            String user2) {

        List<String> result = new ArrayList<>();

        if (!friends.containsKey(user1) ||
            !friends.containsKey(user2)) {
            return result;
        }

        Set<String> firstFriends =
            friends.get(user1);

        Set<String> secondFriends =
            friends.get(user2);

        for (String friend : firstFriends) {

            if (secondFriends.contains(friend)) {
                result.add(friend);
            }
        }

        result.sort(String::compareTo);

        return result;
    }

    // 使用者數量
    public int userCount() {
        return friends.size();
    }

    // 顯示整個社群
    public void printNetwork() {

        System.out.println("===== Social Network =====");

        List<String> users =
            new ArrayList<>(friends.keySet());

        users.sort(String::compareTo);

        for (String user : users) {

            System.out.println(
                user + " -> " + getFriends(user)
            );
        }
    }

    public static void main(String[] args) {

        SocialNetworkGraph graph =
            new SocialNetworkGraph();

        graph.addUser("Alice");
        graph.addUser("Bob");
        graph.addUser("Charlie");
        graph.addUser("David");
        graph.addUser("Emma");

        // 建立好友
        graph.addFriend("Alice", "Bob");
        graph.addFriend("Alice", "Charlie");
        graph.addFriend("Alice", "David");

        graph.addFriend("Bob", "Charlie");
        graph.addFriend("Bob", "Emma");

        graph.addFriend("Charlie", "David");

        graph.addFriend("David", "Emma");

        graph.printNetwork();

        System.out.println();

        System.out.println(
            "Alice 的好友："
            + graph.getFriends("Alice")
        );

        System.out.println(
            "Bob 的好友："
            + graph.getFriends("Bob")
        );

        System.out.println();

        System.out.println(
            "Alice 和 Bob 的共同好友："
            + graph.commonFriends("Alice", "Bob")
        );

        System.out.println(
            "Alice 和 Emma 的共同好友："
            + graph.commonFriends("Alice", "Emma")
        );

        System.out.println();

        System.out.println(
            "Alice 和 Bob 是否為好友："
            + graph.isFriend("Alice", "Bob")
        );

        System.out.println();

        System.out.println("解除 Alice 和 Bob 的好友關係");

        graph.removeFriend("Alice", "Bob");

        System.out.println(
            "Alice 的好友："
            + graph.getFriends("Alice")
        );

        System.out.println(
            "Bob 的好友："
            + graph.getFriends("Bob")
        );
    }
}