package LantFarmacii.Model.Persistenta;

import LantFarmacii.Model.Connection.ConnectionFactory;
import LantFarmacii.Model.ProdusCuProducator;
import LantFarmacii.Model.Utilizator;

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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PersistentaUtilizatori {

    private List<Utilizator> utilizatori ;

    public List<Utilizator> getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(List<Utilizator> utilizatori) {
        this.utilizatori = utilizatori;
    }

    static PersistentaUtilizatori lista_utilizatori = new PersistentaUtilizatori();
    static{
        lista_utilizatori.setUtilizatori(new ArrayList<Utilizator>());

    }


    private final static String readQuery = "Select * from utilizatori";
    private static final String insertQuery = "INSERT INTO utilizatori (rol, username, parola, nume, id_farmacie)" + " VALUES (?,?,?,?,?)";
    private static final String deleteQuery = "DELETE FROM utilizatori where username = ?";
    private static final String updateQuery = "UPDATE utilizatori SET rol = ?, username = ?, parola = ?, nume = ?, id_farmacie = ?  WHERE username = ?";

    public static void raportXML() throws JAXBException, IOException{

        JAXBContext context = JAXBContext.newInstance(LantFarmacii.Model.Persistenta.PersistentaUtilizatori.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(lista_utilizatori, new File("./users.xml"));
    }

    public static PersistentaUtilizatori readFromDB() throws SQLException
    {
        lista_utilizatori.getUtilizatori().clear();
        try{
            Connection connection = new ConnectionFactory().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(readQuery);
            while(rs.next()){
                Utilizator u = new Utilizator(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                lista_utilizatori.getUtilizatori().add(u);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return lista_utilizatori;
    }

    public static void addIntoDB(Utilizator utilizator) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, utilizator.getRol());
            insertStatement.setString(2, utilizator.getUsername());
            insertStatement.setString(3, utilizator.getParola());
            insertStatement.setString(4, utilizator.getNume());
            insertStatement.setInt(5, utilizator.getId_farmacie());
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteFromDB(Utilizator utilizator) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, utilizator.getUsername());
            deleteStatement.execute();
            ResultSet rs = deleteStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateDB(Utilizator u1, Utilizator u2) {
        Connection dbConnection = ConnectionFactory.singleInstance.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, u2.getRol());
            updateStatement.setString(2, u2.getUsername());
            updateStatement.setString(3, u2.getParola());
            updateStatement.setString(4, u2.getNume());
            updateStatement.setInt(5, u2.getId_farmacie());
            updateStatement.setString(6, u1.getUsername());
            updateStatement.executeUpdate();
            ResultSet rs = updateStatement.getGeneratedKeys();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        PersistentaUtilizatori p = readFromDB();
        Utilizator u1 = new Utilizator("administrator", "administrator_2", "12345", "Bianca Malaescu", 16);
        Utilizator u2 = new Utilizator("administrator", "administrator_2", "12345", "Pop Maria", 16);
        //addIntoDB(u1);

        updateDB(u1, u2);

        for(Utilizator u: p.getUtilizatori()){
            System.out.println(u.toString());
        }

    }
}
