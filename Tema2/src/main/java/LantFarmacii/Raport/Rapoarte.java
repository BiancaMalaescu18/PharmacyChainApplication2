package LantFarmacii.Raport;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.ProdusCuProducator;
import com.google.gson.*;
import com.opencsv.CSVWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Rapoarte {

    public static void raportJson(PersistentaProduse produse) throws IOException {
        Gson g = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("./raport.json")) {
            g.toJson(produse.getProduse(), writer);
        }
    }

    public  static  void raportCSV(PersistentaProduse produse) throws IOException {
        ArrayList<String[]> array = new ArrayList<>();
        String[] header = {"Nume", "Disponibilitate", "Id", "Producator", "Pret", "Cantitate", "Valabilitate"};
        for(ProdusCuProducator p: produse.getProduse()) {
            array.add(new String[] {p.getNume(), String.valueOf(p.isDisponibilitate()), String.valueOf(p.getId()), p.getProducator(), String.valueOf(p.getPret()), String.valueOf(p.getCantitate()), String.valueOf(p.getValabilitate())});
        }
        try(CSVWriter writer = new CSVWriter(new FileWriter("./raport.csv"))) {
            writer.writeAll(array);
        }
    }

    public static void raportXML(PersistentaProduse listaProduse) throws JAXBException {

        JAXBContext context2 = JAXBContext.newInstance(LantFarmacii.Model.Persistenta.PersistentaProduse.class);
        Marshaller mar2 = context2.createMarshaller();
        mar2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar2.marshal(listaProduse, new File("./raport.xml"));
    }


}
///Clasa care permite generarea reportului Json si pentru atribute de tip LocalDate
class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}
