public class CaesarCipher {
    // Метод для шифрування тексту з використанням шифру Цезаря
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Шифрування великих літер
            if (Character.isUpperCase(ch)) {
                char c = (char) (((int) ch + shift - 65) % 26 + 65);
                result.append(c);
            }
            // Шифрування малих літер
            else if (Character.isLowerCase(ch)) {
                char c = (char) (((int) ch + shift - 97) % 26 + 97);
                result.append(c);
            }
            // Збереження символів, які не є літерами (пробіли, цифри, розділові знаки)
            else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // Метод для дешифрування тексту
    public static String decrypt(String text, int shift) {
        // Дешифрування — це шифрування з негативним зсувом
        return encrypt(text, 26 - shift);
    }
}
