import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

class Patient {
    String medicalId;
    String name;

    Patient(String medicalId, String name) {
        this.medicalId = medicalId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "病歷號：" + medicalId + "，姓名：" + name;
    }
}

public class ClinicQueueSystem {

    private Deque<Patient> queue = new ArrayDeque<>();
    private List<Patient> completed = new ArrayList<>();

    public void register(Patient patient) {
        queue.offerLast(patient);
        System.out.println("掛號成功：" + patient);
    }

    public void cancel(String medicalId) {
        Iterator<Patient> iterator = queue.iterator();

        while (iterator.hasNext()) {
            Patient patient = iterator.next();

            if (patient.medicalId.equals(medicalId)) {
                iterator.remove();
                System.out.println("已取消掛號：" + patient);
                return;
            }
        }

        System.out.println("找不到病歷號：" + medicalId);
    }

    public Patient nextPatient() {
        return queue.peekFirst();
    }

    public Patient callNext() {
        Patient patient = queue.pollFirst();

        if (patient != null) {
            completed.add(patient);
        }

        return patient;
    }

    public void showCompleted() {
        System.out.println("當日完成清單：");

        if (completed.isEmpty()) {
            System.out.println("目前沒有完成看診的病人");
            return;
        }

        for (Patient patient : completed) {
            System.out.println(patient);
        }
    }

    public void showWaitingQueue() {
        System.out.println("目前候診名單：");

        if (queue.isEmpty()) {
            System.out.println("目前沒有候診病人");
            return;
        }

        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }

    public static void main(String[] args) {

        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.register(new Patient("P001", "Amy"));
        clinic.register(new Patient("P002", "Bob"));
        clinic.register(new Patient("P003", "Cindy"));
        clinic.register(new Patient("P004", "David"));

        System.out.println();

        clinic.showWaitingQueue();

        System.out.println();
        System.out.println("下一位：" + clinic.nextPatient());

        System.out.println();
        Patient called = clinic.callNext();
        System.out.println("叫號：" + called);

        System.out.println();
        clinic.cancel("P003");

        System.out.println();
        clinic.showWaitingQueue();

        System.out.println();
        System.out.println("叫號：" + clinic.callNext());
        System.out.println("叫號：" + clinic.callNext());

        System.out.println();
        clinic.showCompleted();

        System.out.println();
        System.out.println("下一位：" + clinic.nextPatient());

        System.out.println();
        Patient last = clinic.callNext();

        if (last == null) {
            System.out.println("目前沒有候診病人");
        } else {
            System.out.println("叫號：" + last);
        }
    }
}