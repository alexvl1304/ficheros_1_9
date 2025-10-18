import org.json.JSONArray;
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

    /**
     * Convierte un fichero XML a JSON y lo guarda en la ruta especificada.
     * @param rutaxml ruta del fichero XML
     * @param rutajson ruta del fichero JSON
     * @return si se ha guardado el fichero JSON con exito.
     */
    public static boolean convertirXMLToJSON(String rutaxml, String rutajson){

        try{
            String xml = new String(Files.readAllBytes(Paths.get(rutaxml)));

            JSONObject json = XML.toJSONObject(xml);

            Files.write(Paths.get(rutajson), json.toString(4).getBytes());

            System.out.println("XML convertido a JSON correctamente.");
            return true;
        }catch (Exception e){

            e.printStackTrace();
            System.out.println("Error al convertir XML");
            return false;
        }
    }

    /**
     * Convierte un fichero JSON a XML y lo guarda en la ruta especificada.
     * @param rutajson ruta del fichero JSON
     * @param rutaxml ruta del fichero XML
     * @return si se ha guardado el fichero XML con exito.
     */
    public static boolean convertirJSONToXML(String rutajson, String rutaxml){

        try{
            String jsonStr = new String(Files.readAllBytes(Paths.get(rutajson)));

            String xml;

            if (jsonStr.trim().startsWith("[")) {

                JSONArray jsonArray = new JSONArray(jsonStr);

                JSONObject wrapper = new JSONObject();
                wrapper.put("root", jsonArray);
                xml = XML.toString(wrapper);
            } else {

                JSONObject json = new JSONObject(jsonStr);
                xml = XML.toString(json);
            }

            Files.write(Paths.get(rutaxml), xml.getBytes());

            System.out.println("JSON convertido a XML correctamente.");
            return true;
        }catch (Exception e){

            e.printStackTrace();
            System.out.println("Error al convertir XML");
            return false;
        }
    }
}
