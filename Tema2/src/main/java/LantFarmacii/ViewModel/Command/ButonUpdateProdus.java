package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.ProdusCuProducator;
import LantFarmacii.ViewModel.VMAngajat;

import java.time.LocalDate;

public class ButonUpdateProdus implements Command{

    private String nume;
    private Boolean disponibilitate;
    private Integer id;
    private String producator;
    private Double pret;
    private Integer cantitate;
    private LocalDate valabilitate;
    private String[] date;

    public ButonUpdateProdus (VMAngajat vm, String nume, Boolean disponibilitate, Integer id, String producator, Double pret, Integer cantitate, LocalDate valabilitate, String[] date){
        this.nume = nume;
        this.disponibilitate = disponibilitate;
        this.id = id;
        this.producator = producator;
        this.pret = pret;
        this.cantitate = cantitate;
        this.valabilitate = valabilitate;
        this.date = date;
    }

    @Override
    public void execute() {
        ProdusCuProducator p1 = new ProdusCuProducator(date[0], Boolean.parseBoolean(date[1]), Integer.parseInt(date[2]), date[3], Double.parseDouble(date[4]), Integer.parseInt(date[5]), LocalDate.parse(date[6]));
        ProdusCuProducator p2 = new ProdusCuProducator(nume, disponibilitate, id, producator, pret, cantitate, valabilitate);
        PersistentaProduse.updateDB(p1,p2);
    }
}
