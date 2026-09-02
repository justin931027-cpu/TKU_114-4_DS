import java.util.*;

public class LoginActivityReport {
    static class LoginRecord {
        String username;
        String ip;

        LoginRecord(String username, String ip) {
            this.username = username;
            this.ip = ip;
        }
    }

    public static void analyzeLogins(List<LoginRecord> records, int duplicateThreshold) {
        Map<String, Integer> userCounts = new HashMap<>();
        Set<String> uniqueIps = new HashSet<>();
        Map<String, Set<String>> userIpMap = new HashMap<>();

        for (LoginRecord record : records) {
            userCounts.put(record.username, userCounts.getOrDefault(record.username, 0) + 1);
            uniqueIps.add(record.ip);
            userIpMap.computeIfAbsent(record.username, k -> new HashSet<>()).add(record.ip);
        }

        System.out.println("=== 登入紀錄摘要 ===");
        System.out.println("總獨立登入 IP 數: " + uniqueIps.size());
        System.out.println("\n各帳號登入次數:");
        for (Map.Entry<String, Integer> entry : userCounts.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " 次");
        }

        System.out.println("\n=== 異常重複登入報告 (門檻: " + duplicateThreshold + " 次以上) ===");
        for (Map.Entry<String, Integer> entry : userCounts.entrySet()) {
            if (entry.getValue() >= duplicateThreshold) {
                System.out.printf("[警告] 帳號 %s 登入次數達 %d 次，關聯 IP 數: %d 個 %s\n",
                        entry.getKey(), entry.getValue(), userIpMap.get(entry.getKey()).size(), userIpMap.get(entry.getKey()));
            }
        }
    }

    public static void main(String[] args) {
        List<LoginRecord> records = Arrays.asList(
                new LoginRecord("alice", "192.168.1.1"),
                new LoginRecord("bob", "192.168.1.2"),
                new LoginRecord("alice", "192.168.1.1"),
                new LoginRecord("alice", "10.0.0.5"),
                new LoginRecord("charlie", "172.16.0.1"),
                new LoginRecord("alice", "192.168.1.1"),
                new LoginRecord("bob", "192.168.1.3")
        );

        analyzeLogins(records, 3);
    }
}