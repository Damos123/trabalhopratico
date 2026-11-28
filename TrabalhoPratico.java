import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TrabalhoPratico {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
// número de aluno: 1220019
        String[] people = input.nextLine().trim().split("\\s*,\\s*");
        String[] networks = input.nextLine().trim().split("\\s*,\\s*");
        int[][] time = new int[people.length][networks.length];

        for (int p = 0; p < people.length; p++) {
            for (int r = 0; r < networks.length; r++) {
                time[p][r] = input.nextInt();
            }
        }
        input.close();

        // b) tabela
        System.out.println("\nb) Social Media Usage (minutes/day)");
        System.out.printf("%-12s", "");
        for (String r : networks) System.out.printf("%12s", r);
        System.out.println();
        for (int p = 0; p < people.length; p++) {
            System.out.printf("%-12s", people[p]);
            for (int r = 0; r < networks.length; r++) {
                System.out.printf("%12d", time[p][r]);
            }
            System.out.println();
        }

        // c) tempo total e media
        System.out.println("\nc) Daily usage");
        for (int p = 0; p < people.length; p++) {
            int total = sum(time[p]);
            System.out.printf("%-12s: %4d min (%5.1f min average/network)%n",
                    people[p], total, (double) total / networks.length);
        }

        // d) rede mais usada por rede
        System.out.println("\nd) Most active Users");
        for (int r = 0; r < networks.length; r++) {
            int max = 0;
            for (int p = 0; p < people.length; p++) {
                if (time[p][r] > max) max = time[p][r];
            }
            System.out.printf("%-12s: ", networks[r]);
            for (int p = 0; p < people.length; p++) {
                if (time[p][r] == max && max > 0) {
                    System.out.print("\"" + people[p] + "\" ");
                }
            }
            System.out.println();
        }

        // e) acima da media em todas as redes
        System.out.println("\ne) Users consistently above average");
        ArrayList<String> above = new ArrayList<String>();
        for (int p = 0; p < people.length; p++) {
            boolean result = true;
            for (int r = 0; r < networks.length; r++) {
                int total = 0;
                for (int p2 = 0; p2 < people.length; p2++) {
                    total += time[p2][r];
                }
                if (time[p][r] <= (double) total / people.length) result = false;
            }
            if (result) above.add(people[p]);
        }
        if (above.isEmpty()) System.out.println("none");
        else for (String name : above) System.out.println(name);

        // f) rede mais usada por pessoa
        System.out.println("\nf) Most used social network");
        for (int p = 0; p < people.length; p++) {
            int max = max(time[p]);
            System.out.printf("%-12s: ", people[p]);
            for (int r = 0; r < networks.length; r++) {
                if (time[p][r] == max) {
                    System.out.print("[" + networks[r] + "] ");
                }
            }
            System.out.println();
        }

        // g) percentagens
        System.out.println("\ng) Percentage of use");
        System.out.printf("%-12s", "");
        for (String r : networks) System.out.printf("%12s", r);
        System.out.println();
        for (int p = 0; p < people.length; p++) {
            int total = sum(time[p]);
            System.out.printf("%-12s", people[p]);
            for (int r = 0; r < networks.length; r++) {
                if (time[p][r] == 0) System.out.printf("%12s", "");
                else System.out.printf("%11.2f%%", 100.0 * time[p][r] / total);
            }
            System.out.println();
        }

        // h) perfis
        System.out.println("\nh) Users profile");
        System.out.printf("%-16s%-16s%-16s%-16s%n",
                "Moderate", "Regular", "Intensive", "Very Intensive");
        System.out.println("------------|------------|------------|------------");
        ArrayList<ArrayList<String>> profiles = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 4; i++) profiles.add(new ArrayList<String>());
        for (int p = 0; p < people.length; p++) {
            int total = sum(time[p]);
            int profile = total < 90 ? 0 : total <= 180 ? 1 :
                           total <= 300 ? 2 : 3;
            profiles.get(profile).add(people[p]);
        }
        int lines = 0;
        for (ArrayList<String> list : profiles) {
            if (list.size() > lines) lines = list.size();
        }
        for (int line = 0; line < lines; line++) {
            for (int profile = 0; profile < 4; profile++) {
                String name = line < profiles.get(profile).size()
                        ? profiles.get(profile).get(line) : "";
                System.out.printf("%-16s", name);
            }
            System.out.println();
        }

        // i) redes ordenadas e histograma
        int[] totals = new int[networks.length];
        int[] users = new int[networks.length];
        int grandTotal = 0;
        for (int r = 0; r < networks.length; r++) {
            for (int p = 0; p < people.length; p++) {
                totals[r] += time[p][r];
                if (time[p][r] > 0) users[r]++;
            }
            grandTotal += totals[r];
        }
        int[] order = new int[networks.length];
        for (int i = 0; i < networks.length; i++) order[i] = i;
        for (int i = 0; i < networks.length - 1; i++) {
            for (int j = i + 1; j < networks.length; j++) {
                int a = order[i], b = order[j];
                boolean swap = totals[a] < totals[b]
                        || totals[a] == totals[b] && users[a] < users[b]
                        || totals[a] == totals[b] && users[a] == users[b]
                           && networks[a].compareTo(networks[b]) > 0;
                if (swap) {
                    int temp = order[i];
                    order[i] = order[j];
                    order[j] = temp;
                }
            }
        }
        System.out.println("\ni) Use of social networks");
        for (int r : order) {
            double percent = grandTotal == 0 ? 0 : 100.0 * totals[r] / grandTotal;
            int stars = (int) Math.round(percent / 5.0);
            System.out.printf("%-12s|", networks[r]);
            for (int k = 0; k < stars; k++) System.out.print("*");
            System.out.printf(" %.2f%%%n", percent);
        }
        System.out.println("            +----+----+----+----+");
        System.out.println("          %     25   50   75 100");

        // j) utilizadores mais equilibrados
        int smallestDifference = Integer.MAX_VALUE;
        ArrayList<String> balanced = new ArrayList<String>();
        for (int p = 0; p < people.length; p++) {
            int difference = max(time[p]) - min(time[p]);
            if (difference < smallestDifference) {
                smallestDifference = difference;
                balanced.clear();
                balanced.add(people[p]);
            } else if (difference == smallestDifference) {
                balanced.add(people[p]);
            }
        }
        System.out.println("\nj) Most balanced users");
        System.out.print("{");
        for (int i = 0; i < balanced.size(); i++) {
            if (i > 0) System.out.print(", ");
            System.out.print("\"" + balanced.get(i) + "\"");
        }
        System.out.println("}");
    }

    static int sum(int[] values) {
        int result = 0;
        for (int value : values) result += value;
        return result;
    }

    static int max(int[] values) {
        int result = values[0];
        for (int value : values) if (value > result) result = value;
        return result;
    }

    static int min(int[] values) {
        int result = values[0];
        for (int value : values) if (value < result) result = value;
        return result;
    }
}
