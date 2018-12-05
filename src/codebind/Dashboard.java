import eu.hansolo.steelseries.gauges.Linear;
import eu.hansolo.steelseries.gauges.Radial;
import eu.hansolo.steelseries.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame{

    private static Linear oilTempScavenge = null;
    public static float Scav_OilPressure = 0;

    public static float Tank_OilTemperature = 0;
    public static float Tank_OilPressure = 0;

    public static float FuelFlow = 0;

    private static int N1_EngineRPM = 0;
    private static int N2_EngineRPM = 0;
    private static int oilPressureVal = 0;
    private static float Scav_OilTemperature = 0;
    private static float Outlet_OilTemperature = 0;
    private static Radial N1_EngineRPM_gauge = null;
    private static Radial N2_EngineRPM_gauge = null;
    private static Radial oilPressure = null;
    private static Linear oilTempOutlet = null;

    private static JLabel valueLabel = null;



    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));
        panel.setBackground(Color.black);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridLayout(2,1,0,40));
        subPanel.setBackground(Color.black);
        subPanel.setBorder(BorderFactory.createEmptyBorder(40,20,40,20));


        oilTempScavenge = new Linear();
        oilTempScavenge.setTitle("Oil Temp Scavenge");
        oilTempScavenge.setUnitString("\u00b0F");
        oilTempScavenge.setMaxValue(300);
        oilTempScavenge.setMinValue(32);
        oilTempScavenge.setThreshold(290);

        //Set track & sections
        final Section[] sectionOilTempScavenge = {
                new Section(0, 270, java.awt.Color.GREEN),
                new Section(270, 290, java.awt.Color.YELLOW),
                new Section(290, 300, java.awt.Color.RED) };

        oilTempScavenge.setSections(sectionOilTempScavenge);
        oilTempScavenge.setSectionsVisible(true);

        oilTempOutlet = new Linear();

        oilTempOutlet.setTitle("Oil Temp Outlet");
        oilTempOutlet.setUnitString("\u00b0F");
        oilTempOutlet.setMaxValue(300);
        oilTempOutlet.setMinValue(32);
        oilTempOutlet.setThreshold(255);


        //Set track & sections
        final Section[] sectionOilTempOutlet = {
                new Section(0, 230, java.awt.Color.GREEN),
                new Section(230, 255, java.awt.Color.YELLOW),
                new Section(255, 300, java.awt.Color.RED) };

        oilTempOutlet.setSections(sectionOilTempOutlet);
        oilTempOutlet.setSectionsVisible(true);

        N1_EngineRPM_gauge = new Radial();
        N1_EngineRPM_gauge.setTitle("N1");
        N1_EngineRPM_gauge.setUnitString("RPM x 100");

        //Set max value and background color
        N1_EngineRPM_gauge.setBackgroundColor(BackgroundColor.BLACK);
        N1_EngineRPM_gauge.setMaxValue(300);
        N1_EngineRPM_gauge.setMinValue(0);

        //Set frame visibility, design and color
        N1_EngineRPM_gauge.setFrameDesign(FrameDesign.BLACK_METAL);
        N1_EngineRPM_gauge.setFrameVisible(true);

        //Set LED threshold
        N1_EngineRPM_gauge.setThreshold(268);

        //Set track & sections
        final Section[] SECTIONS = {
                new Section(0, 260, Color.GREEN),
                new Section(260, 268, java.awt.Color.YELLOW),
                new Section(268, 300, java.awt.Color.RED) };

        N1_EngineRPM_gauge.setSections(SECTIONS);
        N1_EngineRPM_gauge.setSectionsVisible(true);




        N2_EngineRPM_gauge = new Radial();
        N2_EngineRPM_gauge.setTitle("N2");
        N2_EngineRPM_gauge.setUnitString("RPM x 100");

        //Set max value and background color
        N2_EngineRPM_gauge.setBackgroundColor(BackgroundColor.BLACK);
        N2_EngineRPM_gauge.setMaxValue(500);
        N2_EngineRPM_gauge.setMinValue(0);

        //Set frame visibility, design and color
        N2_EngineRPM_gauge.setFrameDesign(FrameDesign.BLACK_METAL);
        N2_EngineRPM_gauge.setFrameVisible(true);

        //Set LED threshold
        N2_EngineRPM_gauge.setThreshold(455);

        //Set track & sections
        final Section[] sectionsN1_EngineRPM_gauge = {
                new Section(0, 450, Color.GREEN),
                new Section(450, 455, java.awt.Color.YELLOW),
                new Section(455, 500, java.awt.Color.RED) };

        N2_EngineRPM_gauge.setSections(sectionsN1_EngineRPM_gauge);
        N2_EngineRPM_gauge.setSectionsVisible(true);

        oilPressure = new Radial();
        oilPressure.setTitle("Oil Pressure");
        oilPressure.setUnitString("PSI");

        //Set max value and background color
        oilPressure.setBackgroundColor(BackgroundColor.BLACK);
        oilPressure.setMaxValue(100);
        oilPressure.setMinValue(0);

        //Set frame visibility, design and color
        oilPressure.setFrameDesign(FrameDesign.BLACK_METAL);
        oilPressure.setFrameVisible(true);

        //Set LED threshold
        oilPressure.setThreshold(455);

        //Set track & sections
        final Section[] sectionsoilPressure = {
                new Section(0, 30, Color.RED),
                new Section(30, 35, Color.YELLOW),
                new Section(35, 50, Color.GREEN),
                new Section(50, 55, java.awt.Color.YELLOW),
                new Section(55, 100, java.awt.Color.RED) };

        oilPressure.setSections(sectionsoilPressure);
        oilPressure.setSectionsVisible(true);
        subPanel.add(oilTempScavenge);
        subPanel.add(oilTempOutlet);
        panel.add(subPanel);
        panel.add(N1_EngineRPM_gauge);
        panel.add(oilPressure);
        panel.add(N2_EngineRPM_gauge);


        frame.add(panel);
        JPanel buttonsPanel = new JPanel();
        valueLabel = new JLabel("Current Values: Imperial");

        /*final JTextField valueField = new JTextField(7);
        valueField.setText("30")*/;
        JButton button = new JButton("Switch to Metric");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(valueLabel.getText().equals("Current Values: Metric")){
                        valueLabel.setText("Current Values: Imperial");
                        button.setText("Switch to Metric");

                        //Adjust oilTempScavenge
                        oilTempScavenge.setUnitString("\u00b0F");
                        oilTempScavenge.setMaxValue(300);
                        oilTempScavenge.setMinValue(32);
                        oilTempScavenge.setThreshold(290);
                        //Set track & sections
                        final Section[] sectionOilTempScavenge = {
                                new Section(0, 270, java.awt.Color.GREEN),
                                new Section(270, 290, java.awt.Color.YELLOW),
                                new Section(290, 300, java.awt.Color.RED) };
                        oilTempScavenge.setSections(sectionOilTempScavenge);

                        //Adjust oilTempOutlet
                        oilTempOutlet.setUnitString("\u00b0F");
                        oilTempOutlet.setMaxValue(255);
                        oilTempOutlet.setMinValue(32);
                        oilTempOutlet.setThreshold(255);
                        //Set track & sections
                        final Section[] sectionOilTempOutlet = {
                                new Section(0, 250, java.awt.Color.GREEN),
                                new Section(250, 255, java.awt.Color.YELLOW),
                                new Section(255, 300, java.awt.Color.RED) };

                        oilTempOutlet.setSections(sectionOilTempOutlet);

                    }else{
                        valueLabel.setText("Current Values: Metric");
                        button.setText("Switch to Imperial");


                        //Adjust oilTempScavenge
                        oilTempScavenge.setUnitString("\u00b0C");
                        oilTempScavenge.setMaxValue(150);
                        oilTempScavenge.setMinValue(0);
                        oilTempScavenge.setThreshold(143);
                        //Set track & sections
                        final Section[] sectionOilTempScavenge = {
                                new Section(0, 130, java.awt.Color.GREEN),
                                new Section(130, 143, java.awt.Color.YELLOW),
                                new Section(143, 150, java.awt.Color.RED) };
                        oilTempScavenge.setSections(sectionOilTempScavenge);


                        //Adjust oilTempOutlet
                        oilTempOutlet.setUnitString("\u00b0C");
                        oilTempOutlet.setMaxValue(150);
                        oilTempOutlet.setMinValue(0);
                        oilTempOutlet.setThreshold(143);
                        //Set track & sections
                        final Section[] sectionOilTempOutlet = {
                                new Section(0, 115, java.awt.Color.GREEN),
                                new Section(115, 123, java.awt.Color.YELLOW),
                                new Section(123, 150, java.awt.Color.RED) };
                        oilTempOutlet.setSections(sectionOilTempOutlet);
                    }
                } catch(NumberFormatException ex) {
                    //TODO - handle invalid input
                    System.err.println("invalid input");
                }
            }
        });

        DemoJFileChooser panel = new DemoJFileChooser();
        frame.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        buttonsPanel.add(valueLabel);
        buttonsPanel.add(button);
        buttonsPanel.add(panel);

        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        UpdateDataThread UDThread = new UpdateDataThread(10);
        Thread t = new Thread(UDThread);
        t.start();


    }

    public static int getN2_EngineRPM() {
        return N2_EngineRPM;
    }

    public static int getOilPressureVal() {
        return oilPressureVal;
    }

    public static float getScav_OilTemperature() {
        return Scav_OilTemperature;
    }

    public static float getOutlet_OilTemperature() {
        return Outlet_OilTemperature;
    }

    public int getN1_EningeRPM() {
        return N1_EningeRPM;
    }

    public static void setN1_EngineRPM(int n1_EngineRPM) {
        N1_EngineRPM = n1_EngineRPM;
        N1_EngineRPM_gauge.setValue(n1_EningeRPM/100);
        N1_EngineRPM_gauge.setLcdValue(n1_EningeRPM);
    }

    public static void setN2_EngineRPM(int n2_EningeRPM) {
        N2_EningeRPM = n2_EningeRPM;
        N2_EngineRPM_gauge.setValue(n2_EningeRPM/100);
        N2_EngineRPM_gauge.setLcdValue(n2_EningeRPM);
    }

    public static void setOilTempScavenge(int oilTempScavengeValue) {
        Scav_OilTemperature = oilTempScavengeValue;
        oilTempScavenge.setValue(oilTempScavengeValue);
    }

    public static void setOilTempOutlet(int oilTempOutletValue) {
        Outlet_OilTemperature = oilTempOutletValue;
        oilTempOutlet.setValue(oilTempOutletValue);
    }

    public static void setOilPressure(int oilPressureValue) {
        oilPressureVal = oilPressureValue;
        oilPressure.setValue(oilPressureValue);
    }

    public static String getValueType(){
        return valueLabel.getText();
    }
}




