import org.json.JSONObject;
import org.json.XML;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Convertir {

    public static void main(String[] args) {

        if(args.length!=2){
            System.out.println("Argumentos incorrectos");
        }

        boolean convertido = false;

        if (args[0].endsWith(".xml")) {

            convertido = convertirXMLToJSON(args[0], args[1]);

        } else if (args[0].endsWith(".json")) {

            convertido = convertirJSONToXML(args[0], args[1]);

        } else {
            System.out.println("Extension del archivo incorrecta. Solo se admite .xml o .json");
        }

        if(convertido){
            System.out.println("Convertido");
        }else{
            System.out.println("No convertido");
        }
    }

    public static boolean convertirXMLToJSON(String rutaxml, String rutajson){

        try{
            // Leer el archivo XML
            String xml = new String(Files.readAllBytes(Paths.get(rutaxml)));

            // Convertir a JSON
            JSONObject json = XML.toJSONObject(xml);

            // Guardar como archivo JSON
            Files.write(Paths.get(rutajson), json.toString(4).getBytes());

            System.out.println("XML convertido a JSON correctamente.");
            return true;
        }catch (Exception e){

            System.out.println("Error al convertir XML");
            return false;
        }
    }

    public static boolean convertirJSONToXML(String rutajson, String rutaxml){

        try{
            // Leer el archivo JSON
            String jsonStr = new String(Files.readAllBytes(Paths.get(rutajson)));

            // Convertir a XML
            JSONObject json = new JSONObject(jsonStr);
            String xml = XML.toString(json);

            // Guardar como archivo XML
            Files.write(Paths.get(rutaxml), xml.getBytes());

            System.out.println("JSON convertido a XML correctamente.");
            return true;
        }catch (Exception e){

            System.out.println("Error al convertir XML");
            return false;
        }
    }
}
