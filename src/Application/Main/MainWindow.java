/*
 * Created by JFormDesigner on Fri Mar 15 00:25:06 MSK 2024
 */

package Application.Main;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Application.Main.PanelsForMainWindow.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.io.IOException;
import java.util.List;

/**
 * @author 79531
 */
public class MainWindow extends JFrame {
    private final String username;
    public MainWindow(String username) throws IOException {
        this.username = username;
        setSize(480, 330);
        initComponents();
        textField1.addActionListener(e -> {
            String tittle = textField1.getText();

            try {
                panel1.removeAll();
                final Books books = new Books.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(),
                        JSON_FACTORY,
                        null)
                        .setApplicationName(APPLICATION_NAME)
                        .setBooksRequestInitializer(new BooksRequestInitializer("AIzaSyBsQm00QtMgANvXCqO8UR8r25pSyhae8yg")) // Replace YOUR_API_KEY with your actual API key
                        .build();

                String query = tittle; // ваш запрос

                Books.Volumes.List list = books.volumes().list(query);

                Volumes volumes = list.execute();

                if (volumes != null && volumes.getTotalItems() != null) {
                    System.out.println("Total items: " + volumes.getTotalItems());
                    // Вывод информации о книгах
                    //volumes.getItems().forEach(volume -> System.out.println(volume.getVolumeInfo().getTitle()));
                    List<Volume> tittles = volumes.getItems();
                    for (Volume volume : tittles) {
                        /*JPanel bookPanel = new BookPanel(volume.getVolumeInfo().getTitle());
                        bookPanel.setSize(520, 90);
                        bookPanel.setPreferredSize(new Dimension(520, 90));
                        bookPanel.setMinimumSize(new Dimension(520, 90));
                        bookPanel.setMaximumSize(new Dimension(520, 90));*/
                        String imageLink = null;
                        try{
                            imageLink = volume.getVolumeInfo().getImageLinks().getThumbnail();
                        }catch (NullPointerException ex){
                            imageLink = "null";
                        }
                        panel1.add(new BookPanel(volume.getVolumeInfo().getTitle(), imageLink));
                    }
                    repaint();
                    revalidate();
                } else {
                    System.out.println("No results.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initComponents() throws IOException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        textField1 = new JTextField();
        button1 = new JButton();
        scrollPane1 = new JScrollPane();
        panel1 = new JPanel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        ((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {396, 0, 0};
        ((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {44, 242, 0};
        ((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};
        contentPane.add(textField1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- button1 ----
        button1.setText("text");
        contentPane.add(button1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== scrollPane1 ========
        {

            //======== panel1 ========
            {
                panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
                .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax
                . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,
                12 ) ,java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans
                .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e.
                getPropertyName () ) )throw new RuntimeException( ) ;} } );
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
            }
            scrollPane1.setViewportView(panel1);
        }
        contentPane.add(scrollPane1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JTextField textField1;
    private JButton button1;
    private JScrollPane scrollPane1;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private static final String APPLICATION_NAME = "YourApplicationName";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

}
