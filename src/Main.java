import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        Hospital hospital = new Hospital( );
        hospital.add(new Patient("Mike", "Wagner", "12 12 1981", true));
        hospital.add(new Patient("Wendy", "Roads", "15 11 1980", false));
        hospital.add(new Patient("Chuk", "Roads", "11 10 1978", true));
        hospital.add(new Patient("Bobby", "Axelrod", "12 06 1980", true));
        Registry registry = new Registry(hospital,"fileName");
        registry.write();//запись в список с помощью Dom
        registry.read();// чтение из списка локального staxParse
        registry.uploadRemoteFile();//чтение удаленногофайла
    }
}
