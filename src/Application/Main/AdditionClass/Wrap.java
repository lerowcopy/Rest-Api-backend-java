package Application.Main.AdditionClass;

public class Wrap {
    public static void main(String[] args) {
        String line = "aiosdmfoasmdofimasodmfoasmdoifmaiosdmfasd";
        int count = 0;
        for (int i = 0; i < line.length(); ++i) {
            if (count == 10) {
                String first = line.substring(0, i);
                String second = line.substring(i);
                line = first + "<br/>" + second;
                count = -1;
                i += 4;
            }
            count += 1;
        }
        System.out.println(line);

    }
}
