package LantFarmacii.Raport;

import LantFarmacii.Model.Persistenta.PersistentaProduse;
import LantFarmacii.Model.ProdusCuProducator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.JPanel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaportProducator extends ApplicationFrame {

    public RaportProducator(String title ) throws SQLException {
        super( title );
        setContentPane(createDemoPanel());
    }

    private static PieDataset createDataset( ) throws SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        PersistentaProduse produse  = PersistentaProduse.readFromDB();
        List<String> producatori = new ArrayList<>();
        for(ProdusCuProducator p : produse.getProduse()) {
            int count = 0;
            if(! producatori.contains(p.getProducator())) {
                producatori.add(p.getProducator());
                for(ProdusCuProducator p2 : produse.getProduse()) {
                    if(p.getProducator().equals(p2.getProducator())){
                        count = count + p2.getCantitate();
                    }

                }

                dataset.setValue(String.valueOf(p.getProducator()), count);
            }
        }

        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Producatori",
                dataset,
                true,
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel( ) throws SQLException {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel(chart);
    }

    public static void creare() throws SQLException {
        RaportProducator r = new RaportProducator( "Statistica - producatori" );
        r.setSize( 600 , 400 );
        RefineryUtilities.centerFrameOnScreen(r);
        r.setVisible( true );
    }
}