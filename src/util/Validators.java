package util;

import java.math.BigDecimal;

public class Validators {
    public static void requirePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Montant doit etre > 0");
        }
    }

    public static void requireNonEmpty(String s, String message) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
