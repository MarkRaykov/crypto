package Homework;

public class MainPR2 {
    public static void main(String[] args) {
        // Текст для шифрования (более 100 символов)
        String originalText = "HELLO WORLD THIS IS A TEST MESSAGE FOR HILL CIPHER ENCRYPTION ALGORITHM" +
                "WITH ENGLISH TEXT CONTAINING MORE THAN ONE HUNDRED CHARACTERS TO DEMONSTRATE THE PROPER WORKING OF THE HILL CIPHER SYSTEM";

        System.out.println("Демонстрация работы\n");
        System.out.println("Исходный текст:");
        System.out.println(originalText);
        System.out.println("\nДлина текста: " + originalText.replaceAll("[^A-Z]", "").length() + " символов\n");

        try {
            // Шифрование
            String encryptedText = PR2.encrypt(originalText);
            System.out.println("Зашифрованный текст:");
            System.out.println(encryptedText);
            System.out.println();

            // Расшифрование
            String decryptedText = PR2.decrypt(encryptedText);
            System.out.println("Расшифрованный текст:");
            System.out.println(decryptedText);
            System.out.println();

            // Проверка корректности работы
            String cleanOriginal = originalText.toUpperCase().replaceAll("[^A-Z]", "");
            if (cleanOriginal.length() % 2 != 0) {
                cleanOriginal += "X"; // Добавляем X если длина нечетная
            }

            boolean isCorrect = cleanOriginal.equals(decryptedText);
            System.out.println("Проверка корректности");
            System.out.println("Исходный текст совпадает с расшифрованным: " + isCorrect);

            if (isCorrect) {
                System.out.println("Шифрование и расшифрование работают корректно!");
            } else {
                System.out.println("Обнаружены ошибки в работе алгоритма!");
            }

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении шифрования: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
