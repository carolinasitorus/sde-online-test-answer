import java.util.ArrayList;

public class Question01 {
    public static void main(String[] args) {
        Question01 solution = new Question01();
        System.out.println(solution.getSelfNumbersTotal(5000));
    }

    public int getSelfNumbersTotal(int Number) {
        ArrayList<Integer> result = new ArrayList<>();
        String strNumber;
        int sum;
        int selfNumbersTotal = 0;
        for (int i = 1; i <= Number; i++) {
            sum = 0;
            strNumber = String.valueOf(i);
            for (int j = 0; j < strNumber.length(); j++) {
                sum = sum + Integer.parseInt(String.valueOf(strNumber.charAt(j)));
            }
            sum = i + sum;
            result.add(sum);
        }

        for (int k = 1; k < Number; k++) {
            if (!result.contains(k)) {
                result.add(k);
                selfNumbersTotal += k;
            }
        }
        return selfNumbersTotal;
    }
}
