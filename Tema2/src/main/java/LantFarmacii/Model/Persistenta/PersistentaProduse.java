package LantFarmacii.Model.Persistenta;

import LantFarmacii.Model.*;
import LantFarmacii.Model.Connection.ConnectionFactory;
import LantFarmacii.Raport.Rapoarte;
import com.google.gson.*;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "produse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersistentaProduse {
    @XmlElement(name = "produs")

    private List<ProdusCuProducator> produse = null;

    public List<ProdusCuProducator> getProduse() {
        return produse;
    }

    public void setProduse(List<ProdusCuProducator> produse) {
        this.produse = produse;
    }

    static PersistentaProduse listaProduse = new PersistentaProduse();
    static  {
        listaProduse.setProduse(new ArrayList<ProdusCuProducator>());
    }

    private final static String readQuery = "Select * from produse";
    private static final String insertQuery = "INSERT INTO produse (nume, disponibilitate, id, producator, pret, cantitate, valabilitate)" + " VALUES (?,?,?,?,?,?,?)";
    private static final String deleteQuery = "DELETE FROM produse where id = ?";
    private static final String updateQuery = "UPDATE produse SET nume = ?, disponibilitate = ?, id = ?, producator = ?, pret = ? , cantitate = ?, valabilitate = ?  WHERE id = ?";


    public static PersistentaProduse readFromDB() throws SQLException
    {
        listaProduse.getProduse().clear();
        try{
            Connection connection = new ConnectionFactory().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(readQuery);
            while(rs.next()){
                ProdusCuProducator p = new ProdusCuProducator(
                        rs.getString(1),
                        rs.getBoolean(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getDate(7).toLocalDate());
                        listaProduse.getProduse().add(p);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return listaProduse;
    }

    public static void addIntoDB(ProdusCuProducator produs ) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, produs.getNume());
            insertStatement.setBoolean(2, produs.isDisponibilitate());
            insertStatement.setInt(3, produs.getId());
            insertStatement.setString(4, produs.getProducator());
            insertStatement.setDouble(5, produs.getPret());
            insertStatement.setInt(6, produs.getCantitate());
            insertStatement.setDate(7, java.sql.Date.valueOf(produs.getValabilitate()));
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteFromDB(ProdusCuProducator produs) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, produs.getId());
            deleteStatement.execute();
            ResultSet rs = deleteStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDB(ProdusCuProducator p1, ProdusCuProducator p2) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, p2.getNume());
            updateStatement.setBoolean(2, p2.isDisponibilitate());
            updateStatement.setInt(3, p2.getId());
            updateStatement.setString(4, p2.getProducator());
            updateStatement.setDouble(5, p2.getPret());
            updateStatement.setInt(6, p2.getCantitate());
            updateStatement.setDate(7, java.sql.Date.valueOf(p2.getValabilitate()));
            updateStatement.setInt(8, p1.getId());
            updateStatement.executeUpdate();
            ResultSet rs = updateStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, JAXBException, IOException {
        //ProdusCuProducator prod = new ProdusCuProducator("Aspirina", true, 15, "PharmaR", 16.55, 50, LocalDate.parse("2022-05-05"));
        ProdusCuProducator p2 = new ProdusCuProducator("Plantusin", false, 15, "PharmaR", 16.55, 50, LocalDate.parse("2022-05-05"));
        //addIntoDB(prod);
        //deleteFromDB(prod);
        //updateDB(prod, p2);
        PersistentaProduse p = readFromDB();
        Rapoarte.raportXML(p);
        for(ProdusCuProducator produs: p.getProduse()){
            System.out.println(produs.toString());
        }

    }
}

