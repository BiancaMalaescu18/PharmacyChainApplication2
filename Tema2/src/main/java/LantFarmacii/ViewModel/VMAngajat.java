package LantFarmacii.ViewModel;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.ViewModel.Command.*;

import java.time.LocalDate;

public class VMAngajat {

    public Command afisareProduse;
    public Command cautareDenumire;
    public Command stergereProdus;
    public Command filtrareDisponibilitate;
    public Command filtrareValabilitate;
    public Command filtrarePret;
    public Command filtrareProducator;
    public Command adaugareProdus;
    public Command updateProdus;
    public Command raportXML;
    public Command raportCSV;
    public Command raportJSON;
    public Command statistici;

    private String nume = "";
    private Boolean disponibilitate;
    private Integer id;
    private String producator;
    private Double pret;
    private Integer cantitate;
    private LocalDate valabilitate;
    private String[] date;
    private String search;




    public VMAngajat(){
        afisareProduse = new ButonAfisare(this);
        raportXML = new ButonRaportXML(this);
        raportCSV = new ButonRaportCSV(this);
        raportJSON = new ButonRaportJSON(this);
        statistici = new ButonStatistici(this);

    }

    public Object[][] tableData(PersistentaProduse produse) {
        int sz = produse.getProduse().size();
        Object[][] data = new Object[sz][];

        for (int i = 0; i < sz; i++) {
            data[i] = new Object[]{produse.getProduse().get(i).getNume(), produse.getProduse().get(i).isDisponibilitate(), produse.getProduse().get(i).getId(), produse.getProduse().get(i).getProducator(), produse.getProduse().get(i).getPret(), produse.getProduse().get(i).getCantitate(), produse.getProduse().get(i).getValabilitate()};
        }

        return data;
    }

    public void instAdd(){

        adaugareProdus = new ButonAdaugareProdus(this, nume, disponibilitate, id, producator, pret, cantitate, valabilitate);

    }

    public void instUpd(){
        updateProdus = new ButonUpdateProdus(this, nume, disponibilitate, id, producator, pret, cantitate, valabilitate, date);

    }
    public void instDel(){
        stergereProdus = new ButonStergereProdus(this, date);
    }

    public void instSearchName(){
        cautareDenumire = new ButonCautareDenumire(this, search);
    }

    public void instSearchDisp(){
        filtrareDisponibilitate = new ButonFiltrareDisponibilitate(this, search);
    }

    public void instSearchVal(){
        filtrareValabilitate = new ButonFiltrareValabilitate(this, search);
    }

    public void instSearchPrice(){
        filtrarePret = new ButonFiltrarePret(this, search);
    }

    public void instSearchManuf(){
        filtrareProducator = new ButonFiltrareProducator(this, search);
    }

    public void setValabilitate(LocalDate valabilitate) {
        this.valabilitate = valabilitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDisponibilitate(Boolean disponibilitate) {
        this.disponibilitate = disponibilitate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
