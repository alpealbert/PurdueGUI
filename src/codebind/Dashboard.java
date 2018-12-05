import eu.hansolo.steelseries.gauges.Linear;
import eu.hansolo.steelseries.gauges.Radial;
import eu.hansolo.steelseries.tools.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Dashboard extends JFrame implements IDashboard{

    private Linear oilTempScavenge = null;

    private float Ambient_TemperatureMaxVal = 300;
    private float Ambient_pressureMaxVal = 300;
    private float T1_TemperatureMaxVal = 300;
    private float T1_PressureMaxVal = 300;
    private float T45_TemperatureMaxVal = 300;
    private float T45_PressureMaxVal = 300;
    private float T75_TemperatureMaxVal = 300;
    private float T75_PressureMaxVal = 300;
    private float T17_TemperatureMaxVal = 300;
    private float T17_PressureMaxVal = 300;
    private float oilPressureMaxVal = 100;
    private float FuelFlowMaxVal = 1000;

    private int N1_EngineRPMVal = 0;
    private int N2_EngineRPMVal = 0;
    private int oilPressureVal = 0;
    private float Scav_OilTemperatureVal = 0;
    private float Outlet_OilTemperatureVal = 0;

    private float Ambient_TemperatureVal = 0;
    private float Ambient_pressureVal = 0;
    private float T1_TemperatureVal = 0;
    private float T1_PressureVal = 0;
    private float T45_TemperatureVal = 0;
    private float T45_PressureVal = 0;
    private float T75_TemperatureVal = 0;
    private float T75_PressureVal = 0;
    private  float T17_TemperatureVal = 0;
    private  float T17_PressureVal = 0;

    private  int FuelFlowVal = 0;
    private  Radial N1_EngineRPM_gauge = null;
    private  Radial N2_EngineRPM_gauge = null;
    private  Radial oilPressure_gauge = null;
    private  Radial FuelFlow_Radial = null;
    private  Linear oilTempOutlet = null;

    private  Linear AmbientTemperature_linear = null;
    private  Linear AmbientPressure_linear = null;
    private  Linear T1_Temperature_linear = null;
    private  Linear T1_Pressure_linear = null;
    private  Linear T45_Temperature_linear = null;
    private  Linear T45_Pressure_linear = null;
    private  Linear T75_Temperature_linear = null;
    private  Linear T75_Pressure_linear = null;
    private  Linear T17_Temperature_linear = null;
    private  Linear T17_Pressure_linear = null;


    private  JLabel valueLabel = null;
    private  String outputPath = null;

     JButton button3;



    public Dashboard(IDashboard Dashboard) {

        JFrame frame = new JFrame("JFrame Example");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3));
        panel.setBackground(Color.black);

        JPanel subPanel1 = new JPanel();
        subPanel1.setLayout(new GridLayout(5,1,0,2));
        subPanel1.setBackground(Color.black);
        subPanel1.setBorder(BorderFactory.createEmptyBorder(2,20,2,20));

        JPanel subPanel2 = new JPanel();
        subPanel2.setLayout(new GridLayout(5,1,0,2));
        subPanel2.setBackground(Color.black);
        subPanel2.setBorder(BorderFactory.createEmptyBorder(2,20,2,20));

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
        oilTempScavenge.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        oilTempScavenge.setFrameVisible(false);

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
        oilTempOutlet.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        oilTempOutlet.setFrameVisible(false);

        //Copy and paste
        AmbientTemperature_linear = new Linear();
        AmbientTemperature_linear.setUnitString("\u00b0F");
        AmbientTemperature_linear.setTitle("Ambient Temperature");
        AmbientTemperature_linear.setMaxValue(Ambient_TemperatureMaxVal);
        AmbientTemperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        AmbientTemperature_linear.setFrameVisible(false);
        AmbientTemperature_linear.setValueColor(ColorDef.GREEN);
        AmbientTemperature_linear.setLedVisible(false);

        //Ambient Pressure
        AmbientPressure_linear = new Linear();
        AmbientPressure_linear.setUnitString("PSI");
        AmbientPressure_linear.setTitle("Ambient Pressure");
        AmbientPressure_linear.setMaxValue(Ambient_pressureMaxVal);
        AmbientPressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        AmbientPressure_linear.setFrameVisible(false);
        AmbientPressure_linear.setValueColor(ColorDef.GREEN);
        AmbientPressure_linear.setLedVisible(false);

        //T1_Temperature_linear
        T1_Temperature_linear = new Linear();
        T1_Temperature_linear.setUnitString("\u00b0F");
        T1_Temperature_linear.setTitle("T1 Temperature");
        T1_Temperature_linear.setMaxValue(T1_PressureMaxVal);
        T1_Temperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T1_Temperature_linear.setFrameVisible(false);
        T1_Temperature_linear.setValueColor(ColorDef.GREEN);
        T1_Temperature_linear.setLedVisible(false);

        //T1_Pressure_linear
        T1_Pressure_linear = new Linear();
        T1_Pressure_linear.setUnitString("PSI");
        T1_Pressure_linear.setTitle("T1 Pressure");
        T1_Pressure_linear.setMaxValue(T1_PressureMaxVal);
        T1_Pressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T1_Pressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T1_Pressure_linear.setFrameVisible(false);
        T1_Pressure_linear.setValueColor(ColorDef.GREEN);
        T1_Pressure_linear.setLedVisible(false);

        //T45_Temperature_linear
        T45_Temperature_linear = new Linear();
        T45_Temperature_linear.setUnitString("\u00b0F");
        T45_Temperature_linear.setTitle("T4.5 Temperature");
        T45_Temperature_linear.setMaxValue(T45_TemperatureMaxVal);
        T45_Temperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T45_Temperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T45_Temperature_linear.setFrameVisible(false);
        T45_Temperature_linear.setValueColor(ColorDef.GREEN);
        T45_Temperature_linear.setLedVisible(false);

        //T45_Pressure_linear
        T45_Pressure_linear = new Linear();
        T45_Pressure_linear.setUnitString("PSI");
        T45_Pressure_linear.setTitle("T4.5 Pressure");
        T45_Pressure_linear.setMaxValue(T45_PressureMaxVal);
        T45_Pressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T45_Pressure_linear.setFrameVisible(false);
        T45_Pressure_linear.setValueColor(ColorDef.GREEN);
        T45_Pressure_linear.setLedVisible(false);

        //T75_Temperature_linear
        T75_Temperature_linear = new Linear();
        T75_Temperature_linear.setUnitString("\u00b0F");
        T75_Temperature_linear.setTitle("T7.5 Temperature");
        T75_Temperature_linear.setMaxValue(T75_TemperatureMaxVal);
        T75_Temperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T75_Temperature_linear.setFrameVisible(false);
        T75_Temperature_linear.setValueColor(ColorDef.GREEN);
        T75_Temperature_linear.setLedVisible(false);

        //T75_Pressure_linear
        T75_Pressure_linear = new Linear();
        T75_Pressure_linear.setUnitString("PSI");
        T75_Pressure_linear.setTitle("T7.5 Pressure");
        T75_Pressure_linear.setMaxValue(T75_PressureMaxVal);
        T75_Pressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T75_Pressure_linear.setFrameVisible(false);
        T75_Pressure_linear.setValueColor(ColorDef.GREEN);
        T75_Pressure_linear.setLedVisible(false);

        //T17_Temperature_linear
        T17_Temperature_linear = new Linear();
        T17_Temperature_linear.setUnitString("\u00b0F");
        T17_Temperature_linear.setTitle("T17 Temperature");
        T17_Temperature_linear.setMaxValue(T17_TemperatureMaxVal);
        T17_Temperature_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T17_Temperature_linear.setFrameVisible(false);
        T17_Temperature_linear.setValueColor(ColorDef.GREEN);
        T17_Temperature_linear.setLedVisible(false);

        //T17_Pressure_linear
        T17_Pressure_linear = new Linear();
        T17_Pressure_linear.setUnitString("PSI");
        T17_Pressure_linear.setTitle("T17 Pressure");
        T17_Pressure_linear.setMaxValue(T17_PressureMaxVal);
        T17_Pressure_linear.setBackgroundColor(BackgroundColor.LIGHT_GRAY);
        T17_Pressure_linear.setFrameVisible(false);
        T17_Pressure_linear.setValueColor(ColorDef.GREEN);
        T17_Pressure_linear.setLedVisible(false);



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



        oilPressure_gauge = new Radial();
        oilPressure_gauge.setTitle("Oil Pressure");
        oilPressure_gauge.setUnitString("PSI");

        //Set max value and background color
        oilPressure_gauge.setBackgroundColor(BackgroundColor.BLACK);
        oilPressure_gauge.setMaxValue(100);
        oilPressure_gauge.setMinValue(0);

        //Set frame visibility, design and color
        oilPressure_gauge.setFrameDesign(FrameDesign.BLACK_METAL);
        oilPressure_gauge.setFrameVisible(true);

        //Set LED threshold
        oilPressure_gauge.setThreshold(455);

        //Set track & sections
        final Section[] sectionsoilPressure = {
                new Section(0, 30, Color.RED),
                new Section(30, 35, Color.YELLOW),
                new Section(35, 50, Color.GREEN),
                new Section(50, 55, java.awt.Color.YELLOW),
                new Section(55, 100, java.awt.Color.RED) };

        oilPressure_gauge.setSections(sectionsoilPressure);
        oilPressure_gauge.setSectionsVisible(true);



        FuelFlow_Radial = new Radial();
        FuelFlow_Radial.setTitle("Fuel Flow");
        FuelFlow_Radial.setUnitString("gal/hr");
        FuelFlow_Radial.setThreshold(4000);

        //Set max value and background color
        FuelFlow_Radial.setBackgroundColor(BackgroundColor.BLACK);
        FuelFlow_Radial.setMaxValue(1000);
        FuelFlow_Radial.setMinValue(0);
        FuelFlow_Radial.setLedVisible(false);

        //Set frame visibility, design and color
        FuelFlow_Radial.setFrameDesign(FrameDesign.BLACK_METAL);
        FuelFlow_Radial.setFrameVisible(true);


        subPanel1.add(oilTempOutlet);
        subPanel1.add(oilTempScavenge);
        subPanel1.add(AmbientTemperature_linear);
        subPanel1.add(AmbientPressure_linear);
        subPanel1.add(T1_Temperature_linear);

        subPanel2.add(T1_Pressure_linear);
        subPanel2.add(T45_Temperature_linear);
        subPanel2.add(T45_Pressure_linear);
        subPanel2.add(T17_Temperature_linear);
        subPanel2.add(T17_Pressure_linear);

        panel.add(subPanel1);
        panel.add(oilPressure_gauge);
        panel.add(N1_EngineRPM_gauge);
        panel.add(subPanel2);
        panel.add(FuelFlow_Radial);
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

                        AmbientTemperature_linear.setMaxValue(Ambient_TemperatureMaxVal);
                        T1_Temperature_linear.setMaxValue(T1_TemperatureMaxVal);
                        T45_Temperature_linear.setMaxValue(T45_TemperatureMaxVal);
                        T75_Temperature_linear.setMaxValue(T75_TemperatureMaxVal);
                        T17_Temperature_linear.setMaxValue(T17_TemperatureMaxVal);

                        AmbientTemperature_linear.setUnitString("\u00b0F");
                        T1_Temperature_linear.setUnitString("\u00b0F");
                        T45_Temperature_linear.setUnitString("\u00b0F");
                        T75_Temperature_linear.setUnitString("\u00b0F");
                        T17_Temperature_linear.setUnitString("\u00b0F");

                        T1_Pressure_linear.setMaxValue(T1_PressureMaxVal);
                        T45_Pressure_linear.setMaxValue(T45_PressureMaxVal);
                        T75_Pressure_linear.setMaxValue(T75_PressureMaxVal);
                        T17_Pressure_linear.setMaxValue(T17_PressureMaxVal);

                        AmbientPressure_linear.setMaxValue(Ambient_pressureMaxVal);

                        System.out.println(toPSI((int) oilPressure_gauge.getValue()));
                        oilPressure_gauge.setValue(toPSI((int) oilPressure_gauge.getValue()));
                        oilPressure_gauge.setMaxValue(oilPressureMaxVal);
                        //Set track & sections
                        final Section[] sectionsoilPressure = {
                                new Section(0, 30, Color.RED),
                                new Section(30, 35, Color.YELLOW),
                                new Section(35, 50, Color.GREEN),
                                new Section(50, 55, java.awt.Color.YELLOW),
                                new Section(55, 100, java.awt.Color.RED) };
                        oilPressure_gauge.setSections(sectionsoilPressure);
                        oilPressure_gauge.setUnitString("PSI");

                        AmbientPressure_linear.setUnitString("PSI");
                        T1_Pressure_linear.setUnitString("PSI");
                        T45_Pressure_linear.setUnitString("PSI");
                        T75_Pressure_linear.setUnitString("PSI");
                        T17_Pressure_linear.setUnitString("PSI");

                        FuelFlow_Radial.setMaxValue(FuelFlowMaxVal);
                        FuelFlow_Radial.setUnitString("Gallon/Hour");

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

                        AmbientTemperature_linear.setMaxValue(toCelcius((int) Ambient_TemperatureMaxVal));
                        T1_Temperature_linear.setMaxValue(toCelcius((int) T1_TemperatureMaxVal));
                        T45_Temperature_linear.setMaxValue(toCelcius((int) T45_TemperatureMaxVal));
                        T75_Temperature_linear.setMaxValue(toCelcius((int) T75_TemperatureMaxVal));
                        T17_Temperature_linear.setMaxValue(toCelcius((int) T17_TemperatureMaxVal));

                        AmbientTemperature_linear.setUnitString("\u00b0C");
                        T1_Temperature_linear.setUnitString("\u00b0C");
                        T45_Temperature_linear.setUnitString("\u00b0C");
                        T75_Temperature_linear.setUnitString("\u00b0C");
                        T17_Temperature_linear.setUnitString("\u00b0C");

                        AmbientPressure_linear.setMaxValue(tokPa((int)Ambient_pressureMaxVal));
                        T1_Pressure_linear.setMaxValue(tokPa((int) T1_PressureMaxVal));
                        T45_Pressure_linear.setMaxValue(tokPa((int) T45_PressureMaxVal));
                        T75_Pressure_linear.setMaxValue(tokPa((int) T75_PressureMaxVal));
                        T17_Pressure_linear.setMaxValue(tokPa((int) T17_PressureMaxVal));

                        System.out.println(tokPa((int) oilPressure_gauge.getValue()));
                        oilPressure_gauge.setValue(tokPa((int) oilPressure_gauge.getValue()));
                        oilPressure_gauge.setMaxValue(tokPa((int)oilPressureMaxVal));
                        //Set track & sections
                        final Section[] sectionsoilPressure = {
                                new Section(0, 4, Color.RED),
                                new Section(4, 5, Color.YELLOW),
                                new Section(5, 7, Color.GREEN),
                                new Section(7, 8, java.awt.Color.YELLOW),
                                new Section(8, tokPa((int) oilPressureMaxVal), java.awt.Color.RED) };
                        oilPressure_gauge.setSections(sectionsoilPressure);
                        oilPressure_gauge.setUnitString("kPa");

                        AmbientPressure_linear.setUnitString("kPa");
                        T1_Pressure_linear.setUnitString("kPa");
                        T45_Pressure_linear.setUnitString("kPa");
                        T75_Pressure_linear.setUnitString("kPa");
                        T17_Pressure_linear.setUnitString("kPa");

                        FuelFlow_Radial.setMaxValue(toLPH((int) FuelFlowMaxVal));
                        FuelFlow_Radial.setUnitString("Litres/Hour");
                    }
                } catch(NumberFormatException ex) {
                    //TODO - handle invalid input
                    System.err.println("invalid input");
                }
            }
        });


        JButton button2 = new JButton("Choose Log Output Destination");
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    outputPath = selectedFile.toString();
                    System.out.println(selectedFile.getAbsolutePath());
                }

                if(outputPath != null){
                    button3.setEnabled(true);
                }
            }
        });

        button3 = new JButton("Begin Log");
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button3.getText().equals("Begin Log")){
                    button3.setText("Stop Log");
                    Logger LoggerThread = new Logger(false,Dashboard);
                    Thread t2 = new Thread(LoggerThread);
                    t2.start();
                }else{
                    button3.setText("Begin Log");
                    Logger.stop();
                }
            }
        });


        buttonsPanel.add(valueLabel);
        buttonsPanel.add(button);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        button3.setEnabled(false);

        frame.add(buttonsPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    public int getN1_EngineRPMVal() {
        return N1_EngineRPMVal;
    }

    public int getN2_EngineRPMVal() {
        return N2_EngineRPMVal;
    }

    public int getOilPressureVal() {
        return oilPressureVal;
    }

    public float getScav_OilTemperatureVal() {
        return Scav_OilTemperatureVal;
    }

    public float getOutlet_OilTemperatureVal() {
        return Outlet_OilTemperatureVal;
    }

    public float getAmbient_TemperatureVal() {
        return Ambient_TemperatureVal;
    }

    public float getAmbient_pressureVal() {
        return Ambient_pressureVal;
    }

    public float getT1_TemperatureVal() {
        return T1_TemperatureVal;
    }

    public float getT1_PressureVal() {
        return T1_PressureVal;
    }

    public float getT45_TemperatureVal() {
        return T45_TemperatureVal;
    }

    public float getT45_PressureVal() {
        return T45_PressureVal;
    }

    public float getT75_TemperatureVal() {
        return T75_TemperatureVal;
    }

    public float getT75_PressureVal() {
        return T75_PressureVal;
    }

    public float getT17_TemperatureVal() {
        return T17_TemperatureVal;
    }

    public float getT17_PressureVal() {
        return T17_PressureVal;
    }

    public int getN1_EningeRPM() {
        return N1_EngineRPMVal;
    }

    public void setN1_EngineRPMVal(int n1_EngineRPMVal) {
        N1_EngineRPMVal = n1_EngineRPMVal;
        N1_EngineRPM_gauge.setValue(n1_EngineRPMVal);
        N1_EngineRPM_gauge.setLcdValue(n1_EngineRPMVal*100);
    }

    public void setN2_EngineRPMVal(int n2_EningeRPM) {
        N2_EngineRPMVal = n2_EningeRPM;
        N2_EngineRPM_gauge.setValue(n2_EningeRPM);
        N2_EngineRPM_gauge.setLcdValue(n2_EningeRPM*100);
    }

    public void setOilTempScavenge(int oilTempScavengeValue) {
        if(getValueType().equals("Current Values: Metric")){
            oilTempScavenge.setValue(toCelcius(oilTempScavengeValue));
        }else{
            oilTempScavenge.setValue(oilTempScavengeValue);
        }
        Scav_OilTemperatureVal = oilTempScavengeValue;

    }

    public void setOilTempOutlet(int oilTempOutletValue) {
        if(getValueType().equals("Current Values: Metric")){
            oilTempOutlet.setValue(toCelcius(oilTempOutletValue));
        }else{
            oilTempOutlet.setValue(oilTempOutletValue);
        }
        Outlet_OilTemperatureVal = oilTempOutletValue;
    }

    public void setOilPressure(int oilPressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            oilPressure_gauge.setValue(tokPa(oilPressureValue));
        }else{
            oilPressure_gauge.setValue(oilPressureValue);
        }
        oilPressureVal = oilPressureValue;
    }

    public void setAmbientTemperature(int ambientTemperatureValue) {
        if(getValueType().equals("Current Values: Metric")){
            AmbientTemperature_linear.setValue(toCelcius(ambientTemperatureValue));
        }else{
            AmbientTemperature_linear.setValue(ambientTemperatureValue);
        }
        Ambient_TemperatureVal = ambientTemperatureValue;
    }

    public void setAmbientPressure(int ambientPressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            AmbientPressure_linear.setValue(tokPa(ambientPressureValue));
        }else{
            AmbientPressure_linear.setValue(ambientPressureValue);
        }
        Ambient_pressureVal = ambientPressureValue;
    }

    public void setT1Temperature(int T1TemperatureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T1_Temperature_linear.setValue(toCelcius(T1TemperatureValue));
        }else{
            T1_Temperature_linear.setValue(T1TemperatureValue);
        }
        T1_TemperatureVal = T1TemperatureValue;
    }

    public void setT1Pressure(int T1PressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T1_Pressure_linear.setValue(tokPa(T1PressureValue));
        }else{
            T1_Pressure_linear.setValue(T1PressureValue);
        }
        T1_PressureVal = T1PressureValue;
    }

    public void setT45Temperature(int T45TemperatureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T45_Temperature_linear.setValue(toCelcius(T45TemperatureValue));
        }else{
            T45_Temperature_linear.setValue(T45TemperatureValue);
        }
        T45_TemperatureVal = T45TemperatureValue;
    }

    public void setT45Pressure(int T45PressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T45_Pressure_linear.setValue(tokPa(T45PressureValue));
        }else{
            T45_Pressure_linear.setValue(T45PressureValue);
        }
        T45_PressureVal = T45PressureValue;
    }

    public void setT75Temperature(int T75TemperatureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T75_Temperature_linear.setValue(toCelcius(T75TemperatureValue));
        }else{
            T75_Temperature_linear.setValue(T75TemperatureValue);
        }
        T75_TemperatureVal = T75TemperatureValue;
    }

    public void setT75Pressure(int T75PressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T75_Pressure_linear.setValue(tokPa(T75PressureValue));
        }else{
            T75_Pressure_linear.setValue(T75PressureValue);
        }
        T75_PressureVal = T75PressureValue;
    }

    public void setT17Temperature(int T17TemperatureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T17_Temperature_linear.setValue(toCelcius(T17TemperatureValue));
        }else{
            T17_Temperature_linear.setValue(T17TemperatureValue);
        }
        T17_TemperatureVal = T17TemperatureValue;
    }

    public void setT17Pressure(int T17PressureValue) {
        if(getValueType().equals("Current Values: Metric")){
            T17_Pressure_linear.setValue(tokPa(T17PressureValue));
        }else{
            T17_Pressure_linear.setValue(T17PressureValue);
        }
        T17_PressureVal = T17PressureValue;
    }

    public void setFuelFlow(int fuelFlowValue) {
        if(getValueType().equals("Current Values: Metric")){
            FuelFlow_Radial.setValue(toLPH(fuelFlowValue));
        }else{
            FuelFlow_Radial.setValue(fuelFlowValue);
        }
        FuelFlowVal = fuelFlowValue;
    }

    public String getValueType(){
        return valueLabel.getText();
    }

    public String getOutputPath(){return outputPath;}

    public int toFahrenheit(int C){
        return (int)(C*1.8+32);
    }

    public int toCelcius(int F){
        return (int)((F-32)/1.8);
    }

    public int tokPa(int PSI){return (int)(0.145*PSI);}

    public int toPSI(int kPa){return (int)(6.895*kPa);}

    public int toLPH(int GPH){return (int)(GPH*3.785);}
}




