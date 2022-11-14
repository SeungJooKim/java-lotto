package lotto.Domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {
    private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 45;

    private static final int LOTTO_LENGTH = 6;

    public static final int LOTTO_PRICE = 1000;

    public static int checkIsValidateAmount(int money) {
        if (money < LOTTO_PRICE || money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(Message.ERROR_PURCHASE_AMOUNT_IS_NOT_INT);
        }
        return money;
    }

    public static void checkIsValidWinningNums(List<Integer> winningNums) {
        if (!checkIsValidLottoNum(winningNums) || !checkIsNotDupicateLottoNum(winningNums) || !checkWinningnumsSize(winningNums)) {
            throw new IllegalArgumentException(Message.ERROR_WINNING_NUMS);
        }
    }

    public static boolean checkIsValidLottoNum(List<Integer> numbers) {
        return numbers.stream().allMatch(Validator::checkIsInLottoRange);
    }

    public static boolean checkIsInLottoRange(int num) {
        return MIN_NUM <= num && num <= MAX_NUM;
    }

    public static boolean checkIsNotDupicateLottoNum(List<Integer> winningNums) {
        Set<Integer> checkSet = new HashSet<>();
        return winningNums.stream().allMatch(checkSet::add);
    }

    public static boolean checkWinningnumsSize(List<Integer> winningNums) {
        return winningNums.size() == LOTTO_LENGTH;
    }

    public static void checkIsValidBonusNum(List<Integer> winningNums, int bonusNum) {

        if (winningNums.contains(bonusNum) || !checkIsInLottoRange(bonusNum)) {
            throw new IllegalArgumentException(Message.ERROR_BONUS_NUM);
        }
    }
}
