interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Email 傳送給 " + receiver + "：" + message);
    }
}

class SmsSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("SMS 傳送給 " + receiver + "：" + message);
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public void send(String receiver, String message) {
        System.out.println("Console 傳送給 " + receiver + "：" + message);
    }
}

public class MessageSenderSystem {

    public static void notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.trim().isEmpty()) {
            System.out.println("錯誤：receiver 不可為空");
            return;
        }

        if (message == null || message.trim().isEmpty()) {
            System.out.println("錯誤：message 不可為空");
            return;
        }

        sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        notify(email, "user@gmail.com", "Email 測試訊息");
        notify(sms, "0912345678", "SMS 測試訊息");
        notify(console, "使用者", "Console 測試訊息");

        notify(email, "", "測試訊息");
        notify(sms, "0912345678", "");
    }
}