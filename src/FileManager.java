import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    // Метод для зчитування вмісту файлу і повернення його у вигляді рядка
    public String readFile (String filePath) throws IOException {
        // Читання всіх рядків із файлу і об'єднання їх у один рядок
        Path path = Path.of(filePath);
        List<String> lines = Files.readAllLines(path);
        return String.join(System.lineSeparator(), lines);
    }

    // Метод для запису тексту у файл
    public void writeFile (String filePath, String content) throws IOException{
        // Запис тексту у файл
        Path path = Path.of(filePath);
        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
    }

    // Метод для створення нового шляху з суфіксом
    public Path createPathWithSuffix(String originalFilePath, String suffix) {
        Path originalPath = Paths.get(originalFilePath);
        String originalFileName = originalPath.getFileName().toString();
        String newFileName;

        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex == -1) {
            // Якщо у файлу немає розширення
            newFileName = originalFileName + suffix;
        } else {
            // Якщо у файлу є розширення
            newFileName = originalFileName.substring(0, dotIndex) + suffix + originalFileName.substring(dotIndex);
        }

        // Повертаємо новий шлях
        return originalPath.resolveSibling(newFileName);
    }

}
