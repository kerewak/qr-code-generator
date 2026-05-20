import java.util.Scanner;
import java.io.File;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Lütfen bir link giriniz: ");

        String link = scanner.nextLine();
        System.out.println("Girilen link: " + link);

        scanner.close();

        int width = 300;
        int height = 300;

        BitMatrix matrix = new MultiFormatWriter().encode(
            link,
            BarcodeFormat.QR_CODE,
            width,
            height 
        );

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String fileName = "qrcode_" + timestamp + ".png";
        File outputFile = new File(fileName);

        MatrixToImageWriter.writeToPath(
            matrix,
            "PNG",
            outputFile.toPath()
        );

        System.out.println("QR kod oluşturuldu: qrcode.png");
    }
}
