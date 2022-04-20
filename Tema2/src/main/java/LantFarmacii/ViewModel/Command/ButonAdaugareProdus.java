package LantFarmacii.ViewModel.Command;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.ProdusCuProducator;
import LantFarmacii.ViewModel.VMAngajat;

import java.time.LocalDate;

public class ButonAdaugareProdus implements Command{

    private String nume;
    private Boolean disponibilitate;
    private Integer id;
    private String producator;
    private Double pret;
    private Integer cantitate;
    private LocalDate valabilitate;

    public ButonAdaugareProdus(VMAngajat vm, String nume, Boolean disponibilitate, Integer id, String producator, Double pret, Integer cantitate, LocalDate valabilitate ){
        this.nume = nume;
        this.disponibilitate = disponibilitate;
        this.id = id;
        this.producator = producator;
        this.pret = pret;
        this.cantitate = cantitate;
        this.valabilitate = valabilitate;
    }

    @Override
    public void execute() {
        ProdusCuProducator p1 = new ProdusCuProducator(nume, disponibilitate, id, producator, pret, cantitate, valabilitate);
        PersistentaProduse.addIntoDB(p1);

    }
}
