import javax.sound.midi.Soundbank;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //C:\Users\mbudn\Desktop\in.txt
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите абсолютный путь до файла, что нужно скопировать:");
                String path = scan.nextLine();
                Path fileInput = Path.of(path);
                String[] lines = path.split("\\\\");
                lines = lines[lines.length - 1].split("\\.");
                String out = "C:\\Users\\mbudn\\IdeaProjects\\untitled\\" + lines[0] + "_копия." + lines[1];
                Path fileOut = Path.of(out);
                Files.copy(fileInput, fileOut);
                try(FileWriter writer = new FileWriter("log.txt", true))
                {
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a zzz");
                    String text = formatForDateNow.format(dateNow) + " был скопирован файл : " + lines[0] + "." + lines[1] + " -> " + lines[0]+ "_копия." + lines[1] + "\n";
                    writer.write(text);
                    writer.flush();
                    System.out.println("Файл был успешно скопирован");
                }
                catch(IOException ex){

                    System.out.println(ex.getMessage());
                }
            }catch (NoSuchFileException e){
                System.out.println("Что-то не так с файлом");
            }catch (FileAlreadyExistsException e){
                System.out.println("Такой файл уже скопирован");
            }
        }
    }
}