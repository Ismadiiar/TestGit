
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class ElectronicVontingSystem {
    // Катталган шайлоочуларды сактоо үчүн
    private Map<String, String> voters;
    // Талапкерлерди сактоо тизмеси
    private List<String> candidates;
    // Ар бир талапкер үчүн добуштарды сактоо үчүн
    private Map<String, Integer> votes;

    public ElectronicVontingSystem() {
        voters = new HashMap<>();
        candidates = new ArrayList<>();
        votes = new HashMap<>();
    }

    public void registerVoter(String name, String id) {
        voters.put(id, name);
    }

    public void addCandidate(String name) {
        candidates.add(name);
        votes.put(name, 0);
        // Жаңы талапкер үчүн добуштарды нөлгө чейин саноону баштаңыз
    }

    public void vote(String id, String candidate) {
        if (!voters.containsKey(id)) {
            System.out.println("Ката: Шайлоочу катталган эмес.");
        } else if (votes.containsKey(candidate)) {
            int count = votes.get(candidate);
            if (count >= 1) {
                System.out.println("Ката: Шайлоочу буга чейин добуш берген.");
            } else {
                votes.put(candidate, count+1);
                System.out.println("Добуш берүү " + candidate + " катталды.");
            }
        } else {
            System.out.println("Ката: Жараксыз талапкер.");
        }
    }

    public void displayResults() {
        System.out.println("Шайлоонун Жыйынтыгы:");
        for (String candidate : candidates) {
            int count = votes.get(candidate);
            System.out.println(candidate + ": " + count + " добуш берүү");
        }
    }

    public static void main(String[] args) {
        ElectronicVontingSystem system = new ElectronicVontingSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Команданы киргизиңиз (Каттоо, Талапкерди кошуу, Добуш берүү, Жыйынтык, Чыгуу):");
            String command = scanner.nextLine();
            switch (command) {
                case "Каттоо":
                    System.out.println("Шайлоочунун атын киргизиңиз:");
                    String name = scanner.nextLine();
                    System.out.println("Шайлоочунун идентификаторун киргизиңиз:");
                    String id = scanner.nextLine();
                    system.registerVoter(name, id);
                    System.out.println("Шайлоочу катталды.");
                    break;
                case "Талапкерди кошуу":
                    System.out.println("Талапкердин атын киргизиңиз:");
                    String candidate = scanner.nextLine();
                    system.addCandidate(candidate);
                    System.out.println("Талапкер кошулду.");
                    break;
                case "Добуш берүү":
                    System.out.println("Шайлоочунун идентификаторун киргизиңиз:");
                    id = scanner.nextLine();
                    System.out.println("Талапкердин атын киргизиңиз:");
                    candidate = scanner.nextLine();
                    system.vote(id, candidate);
                    break;
                case "Жыйынтык":
                    system.displayResults();
                    break;
                case "Чыгуу":
                    running = false;
                    break;
                default:
                    System.out.println("Жараксыз буйрук.");
            }
        }
        scanner.close();
    }
}

