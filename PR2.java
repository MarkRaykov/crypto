package Homework;

public class PR2 {
    // По условию d = 2 * (27 mod 3) + 2 = 2
    // Матрица ключа для шифрования (2x2)
    private static final int[][] KEY_MATRIX = {
            {7, 9},
            {5, 6}
    };

    // Размер матрицы
    private static final int MATRIX_SIZE = 2;

    // Размер английского алфавита
    private static final int ALPHABET_SIZE = 26;

    /**
     * Метод для шифрования текста по системе Хилла
     *
     * @param text исходный текст (только английские буквы)
     * @return зашифрованный текст
     */
    public static String encrypt(String text) {
        // Приводим текст к верхнему регистру и убираем пробелы
        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        // Если длина текста нечетная, добавляем 'X' в конец
        if (text.length() % MATRIX_SIZE != 0) {
            text += "X";
        }

        StringBuilder encryptedText = new StringBuilder();

        // Обрабатываем текст блоками по MATRIX_SIZE символов
        for (int i = 0; i < text.length(); i += MATRIX_SIZE) {
            // Получаем блок символов
            int[] vector = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                vector[j] = text.charAt(i + j) - 'A';
            }

            // Умножаем вектор на матрицу ключа
            int[] result = multiplyMatrixVector(KEY_MATRIX, vector);

            // Преобразуем результат обратно в символы
            for (int j = 0; j < MATRIX_SIZE; j++) {
                char encryptedChar = (char) ((result[j] % ALPHABET_SIZE) + 'A');
                encryptedText.append(encryptedChar);
            }
        }

        return encryptedText.toString();
    }

    /**
     * Метод для расшифрования текста по системе Хилла
     *
     * @param text зашифрованный текст
     * @return расшифрованный текст
     */
    public static String decrypt(String text) {
        // Приводим текст к верхнему регистру
        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        // Находим обратную матрицу
        int[][] inverseMatrix = findInverseMatrix(KEY_MATRIX);
        if (inverseMatrix == null) {
            throw new RuntimeException("Обратная матрица не существует");
        }

        StringBuilder decryptedText = new StringBuilder();

        // Обрабатываем текст блоками по MATRIX_SIZE символов
        for (int i = 0; i < text.length(); i += MATRIX_SIZE) {
            // Получаем блок символов
            int[] vector = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                vector[j] = text.charAt(i + j) - 'A';
            }

            // Умножаем вектор на обратную матрицу
            int[] result = multiplyMatrixVector(inverseMatrix, vector);

            // Преобразуем результат обратно в символы
            for (int j = 0; j < MATRIX_SIZE; j++) {
                // Обеспечиваем положительный результат
                int value = result[j] % ALPHABET_SIZE;
                if (value < 0) {
                    value += ALPHABET_SIZE;
                }
                char decryptedChar = (char) (value + 'A');
                decryptedText.append(decryptedChar);
            }
        }

        return decryptedText.toString();
    }

    /**
     * Умножает матрицу на вектор
     *
     * @param matrix квадратная матрица
     * @param vector вектор
     * @return результат умножения
     */
    private static int[] multiplyMatrixVector(int[][] matrix, int[] vector) {
        int[] result = new int[MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; i++) {
            result[i] = 0;
            for (int j = 0; j < MATRIX_SIZE; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    /**
     * Находит обратную матрицу по модулю
     *
     * @param matrix исходная матрица
     * @return обратная матрица или null, если не существует
     */
    private static int[][] findInverseMatrix(int[][] matrix) {
        // Вычисляем определитель
        int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % ALPHABET_SIZE;
        if (det < 0) {
            det += ALPHABET_SIZE;
        }

        // Находим модульное обратное к определителю
        int detInverse = modInverse(det, ALPHABET_SIZE);
        if (detInverse == -1) {
            return null; // Обратное не существует
        }

        // Создаем обратную матрицу
        int[][] inverse = new int[MATRIX_SIZE][MATRIX_SIZE];

        // Транспонированная матрица алгебраических дополнений
        inverse[0][0] = matrix[1][1];
        inverse[0][1] = -matrix[0][1];
        inverse[1][0] = -matrix[1][0];
        inverse[1][1] = matrix[0][0];

        // Умножаем на модульное обратное к определителю
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                int value = (inverse[i][j] * detInverse) % ALPHABET_SIZE;
                if (value < 0) {
                    value += ALPHABET_SIZE;
                }
                inverse[i][j] = value;
            }
        }

        return inverse;
    }

    /**
     * Находит модульное обратное число
     *
     * @param a число
     * @param m модуль
     * @id739365412 (@ return) модульное обратное или -1, если не существует
     */
    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }
}