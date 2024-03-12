package Application.Main;

public class Wrap {
    public static void main(String[] args) {
        String message = "aiosdmfoasmdofimasodmfoasmdoifmaiosdmfasd";
        int count = 0;
        for (int i = 0; i < message.length(); ++i){
            if (count == 10){
                String first = message.substring(0, i);
                String second = message.substring(i);
                message = first + "\n" + second;
                count = -1;
            }
            count += 1;
        }
        System.out.println(message);

    }
}
