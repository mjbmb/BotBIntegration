package technikum.automation;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileAdministrator {

    public void makePdfDeliveryConfirmation(String filePath, String outputPath, int fontSize) throws IOException {
        String[] eintraege = getLinesOfTXT(filePath);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        for (int i = 0 ; i< eintraege.length; i++) {
            String eintrag = eintraege[i];
            Paragraph temp = new Paragraph("Ihr Produkt "+eintrag+" ist Lieferbereit und wird in kürze bei ihnen eintreffen.");
            temp.setAlignment(Paragraph.ALIGN_LEFT);

            Font textFont = FontFactory.getFont(FontFactory.TIMES);
            textFont.setSize(fontSize);
            temp.setFont(textFont);

            document.add(temp);
        }
        document.close();
    }

    public void convertTxtToPdf(String filePath, String outputPath, int fontSize) throws IOException {
        String[] eintraege = getLinesOfTXT(filePath);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        for (int i = 0 ; i< eintraege.length; i++) {
            String eintrag = eintraege[i];
            Paragraph temp = new Paragraph(eintrag);
            temp.setAlignment(Paragraph.ALIGN_LEFT);

            Font textFont = FontFactory.getFont(FontFactory.TIMES);
            textFont.setSize(fontSize);
            temp.setFont(textFont);

            document.add(temp);
        }
        document.close();
    }

    public void makeDirInRootDir(String path){
        String servicesFolder = System.getProperty("user.dir");
        if(!Files.exists(Paths.get(servicesFolder+path))){
            File dir = new File(servicesFolder+path);
            dir.mkdir();
        }
    }

    public String[] getLinesOfTXT(String txtPath) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(txtPath));
        ArrayList<String> eintraege = new ArrayList<>();

        String line = bf.readLine();

        while (line != null) {
            eintraege.add(line);
            line = bf.readLine();
        }

        bf.close();
        String[] readStingArray = new String[eintraege.size()];
        return eintraege.toArray(readStingArray);
    }

    public String getRootDir(){
        String root = System.getProperty("user.dir");
        return root;
    }

    public void writeToFile(String path, String value, boolean append){
        try {
            FileWriter writer = new FileWriter(path, append);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(value);
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) throws IOException {
        Path pathOj = Paths.get(path);
        Files.deleteIfExists(pathOj);
    }
}
