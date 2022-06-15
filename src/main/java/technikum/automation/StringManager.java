package technikum.automation;

public class StringManager {
    public String removeNumbers(String string){
        String regex = "\\d+";
        return string.replaceAll(regex, "");
    }

    public String removeCharacters(String string){
        String regex = "\\D+";
        return string.replaceAll(regex, "");
    }
}
