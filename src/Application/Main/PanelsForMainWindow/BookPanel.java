/*
 * Created by JFormDesigner on Fri Mar 15 00:40:40 MSK 2024
 */

package Application.Main.PanelsForMainWindow;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author 79531
 */
public class BookPanel extends JPanel {
    public BookPanel(String tittle, String coverLink) {
        setSize(520, 90);
        initComponents();
        label2.setText(tittle);
        if (!coverLink.equals("null")) {
            try {
                // Загружаем изображение по ссылке
                URL url = new URL(coverLink);
                Image image = ImageIO.read(url);

                // Устанавливаем изображение в качестве источника для JLabel
                label1.setIcon(new ImageIcon(image));

            } catch (IOException e) {
                e.printStackTrace();
                label1.setText("Ошибка загрузки обложки");
            }
        }
        setVisible(true);
        repaint();
        revalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Misha
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setBackground(new Color(0x333333));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {80, 80, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 80, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

        //---- label1 ----
        label1.setText("icon");
        label1.setMaximumSize(new Dimension(4444, 50));
        label1.setPreferredSize(new Dimension(27, 50));
        label1.setMinimumSize(new Dimension(80, 50));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.white);
        add(label1, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- label2 ----
        label2.setText("text");
        label2.setMinimumSize(new Dimension(4000, 18));
        label2.setForeground(Color.white);
        add(label2, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Misha
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    
}
