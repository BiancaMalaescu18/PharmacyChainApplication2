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

public class RaportDisponibilitate extends ApplicationFrame {

    public RaportDisponibilitate(String title ) throws SQLException {
        super( title );
        setContentPane(createDemoPanel());
    }

    private static PieDataset createDataset( ) throws SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        PersistentaProduse produse  = PersistentaProduse.readFromDB();
        int true_nr = 0;
        int false_nr = 0;
        for(ProdusCuProducator p : produse.getProduse()) {
            if(p.isDisponibilitate()) {
                true_nr = true_nr + p.getCantitate();
            }
            else {
                false_nr = false_nr + p.getCantitate();
            }
        }

        dataset.setValue( "Disponibile" , true_nr );
        dataset.setValue( "Indisponibile" , false_nr );
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Disponibilitatea produselor",
                dataset,
                true,
                true,
                false);

        return chart;
    }

    public static JPanel createDemoPanel( ) throws SQLException {
        JFreeChart chart = createChart(createDataset( ) );
        return new ChartPanel( chart );
    }

    public static void creare() throws SQLException {
        RaportDisponibilitate r = new RaportDisponibilitate( "Statistica - disponibilitate" );
        r.setSize( 600 , 400 );
        RefineryUtilities.centerFrameOnScreen(r);
        r.setVisible( true );
    }
}