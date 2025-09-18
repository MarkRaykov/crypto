package Homework;

public class PR1 {
    private static final int SHIFT = 29;
    private static final int ALPHABET_SIZE = 32; // 32 буквы в русском алфавите

    /**
     * Метод для шифрования текста по системе Цезаря
     * @param text исходный текст
     * @return зашифрованный текст
     */
    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (isRussianLetter(ch)) {
                char encryptedChar = shiftChar(ch, SHIFT);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(ch); // оставляем другие символы без изменений
            }
        }

        return encryptedText.toString();
    }

    /**
     * Метод для расшифрования текста по системе Цезаря
     * @param text зашифрованный текст
     * @return расшифрованный текст
     */
    public static String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (isRussianLetter(ch)) {
                char decryptedChar = shiftChar(ch, -SHIFT);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(ch); // оставляем другие символы без изменений
            }
        }

        return decryptedText.toString();
    }

    /**
     * Проверяет, является ли символ буквой русского алфавита
     * @param ch символ для проверки
     * @return true, если символ - русская буква, иначе false
     */
    private static boolean isRussianLetter(char ch) {
        return (ch >= 'А' && ch <= 'Я') || (ch >= 'а' && ch <= 'я') || ch == 'Ё' || ch == 'ё';
    }

    /**
     * Сдвигает символ на заданное количество позиций
     * @param ch исходный символ
     * @param shift величина сдвига
     * @return сдвинутый символ
     */
    private static char shiftChar(char ch, int shift) {
        // Определяем регистр символа
        boolean isUpperCase = Character.isUpperCase(ch);

        // Приводим к нижнему регистру для удобства вычислений
        char lowerChar = Character.toLowerCase(ch);

        // Обрабатываем букву Ё отдельно
        if (lowerChar == 'Ё') {
            lowerChar = 'ё';
        }

        // Проверяем, что символ является буквой русского алфавита
        if (lowerChar >= 'а' && lowerChar <= 'я') {
            // Вычисляем позицию буквы в алфавите (а=0, б=1, ..., я=31)
            int position = lowerChar - 'а';

            // Применяем сдвиг с учетом цикличности
            int newPosition = (position + shift) % ALPHABET_SIZE;
            // Обрабатываем отрицательные значения
            if (newPosition < 0) {
                newPosition += ALPHABET_SIZE;
            }

            // Получаем новый символ
            char newChar = (char) ('а' + newPosition);

            // Восстанавливаем регистр
            if (isUpperCase) {
                newChar = Character.toUpperCase(newChar);
            }

            return newChar;
        }

        return ch;
    }
}
