package src.algorithm_study.week6;

import java.util.ArrayList;
import java.util.Arrays;

/*
    프로그래머스 150370 개인정보 수집 유효기간 (https://school.programmers.co.kr/learn/courses/30/lessons/150370)
 */

public class PG_150370 {
    public static void main(String[] args) {
        String today = "2020.01.01";
        String[] terms = {"Z 3", "D 5"};
        String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> result = new ArrayList<>();

        int[] todayNums = splitInts(today);

        // alphabet - 'A'를 index로 유효기간 저장
        int[] alphabet = new int['Z' - 'A' + 1];
        for (String term : terms) {
            String[] t = term.split(" ");
            char c = term.charAt(0);

            alphabet[c - 'A'] = Integer.parseInt(t[1]);
        }

        // privacies배열 반복하며 파기해야할 개인 정보 찾기
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] p = privacy.split(" ");

            String[] privacyDay = p[0].split("\\.");
            char c = p[1].charAt(0);

            int year = Integer.parseInt(privacyDay[0]);
            int month = Integer.parseInt(privacyDay[1]);
            int day = Integer.parseInt(privacyDay[2]);

            month += alphabet[c - 'A'];

            if (month > 12) {
                int quot = month / 12;
                int remain = month % 12;

                if (remain == 0) {
                    year += (quot - 1);
                    month = 12;
                } else {
                    year += quot;
                    month = remain;
                }
            }

            int todayYear = todayNums[0];
            int todayMonth = todayNums[1];
            int todayDay = todayNums[2];

            if (todayYear < year) {
                continue;
            } else if (todayYear == year) {
                if (todayMonth < month) {
                    continue;
                } else if (todayMonth == month) {
                    if (todayDay < day) {
                        continue;
                    }
                }
            }

            result.add(i + 1);
        }


        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int[] splitInts(String st) {
        String[] splits = st.split("\\.");

        int[] splitNums = new int[3];

        splitNums[0] = Integer.parseInt(splits[0]);
        splitNums[1] = Integer.parseInt(splits[1]);
        splitNums[2] = Integer.parseInt(splits[2]);

        return splitNums;
    }
}
