package Homework;
public class Main {
    public static void main(String[] args) {
        // Текст для шифрования (более 100 символов)
        String originalText = "Сегодня мы будем тестировать шифровальный алгоритм на длинном тексте, " +
                "чтобы убедиться, что он корректно обрабатывает символы, пробелы, знаки препинания " +
                "и сохраняет целостность данных даже при больших объёмах информации. ";

        System.out.println("Демонстрация работы\n");
        System.out.println("Исходный текст:");
        System.out.println(originalText);
        System.out.println("\nДлина текста: " + originalText.length() + " символов\n");

        // Шифрование
        String encryptedText = PR1.encrypt(originalText);
        System.out.println("Зашифрованный текст (сдвиг = 29):"); // 27(порядковый номер в списке) + 2
        System.out.println(encryptedText);
        System.out.println();

        // Расшифрование
        String decryptedText = PR1.decrypt(encryptedText);
        System.out.println("Расшифрованный текст:");
        System.out.println(decryptedText);
        System.out.println();

        // Проверка корректности работы
        boolean isCorrect = originalText.equals(decryptedText);
        System.out.println("Проверка корректности");
        System.out.println("Исходный текст совпадает с расшифрованным: " + isCorrect);

        if (isCorrect) {
            System.out.println("✓ Шифрование и расшифрование работают корректно!");
        } else {
            System.out.println("✗ Обнаружены ошибки в работе алгоритма!");
        }
    }
}
