import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logger implements Runnable{

    //Change LoggerInterval to change log-interval. Time in ms between reads.
    public static int LoggerInterval = 500;
    public static PrintWriter printWriter = null;
    public static FileWriter fileWriter = null;
    public static boolean exit = false;

    @Override
    public void run() {
        try {
            fileWriter = new FileWriter(Dashboard.getOutputPath()+"\\log.txt", true);
            System.out.println(Dashboard.getOutputPath()+"\\log.txt");
            System.out.println("Exit: " + exit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter = new PrintWriter(fileWriter);

        while(!exit){
            System.out.println("Printing!");
            String date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            if(Dashboard.getValueType().equals("Current Values: Metric")) {
                printWriter.println("OilTmpSca(C): " + toCelcius((int) Dashboard.getScav_OilTemperatureVal()) +
                        ", OilTmpOut(C): " + toCelcius((int) Dashboard.getOutlet_OilTemperatureVal()) +
                        ", OilPressure(kPa): " + tokPa((int) Dashboard.getAmbient_pressureVal()) +
                        ", Ambient Pressure(kPa): " + tokPa((int) Dashboard.getAmbient_pressureVal()) +
                        ", T1 Temp(C): " + toCelcius((int) Dashboard.getT1_TemperatureVal()) +
                        ", T1 Pres(kPa): " + tokPa((int) Dashboard.getT1_PressureVal()) +
                        ", T4.5 Temp(C): " + toCelcius((int) Dashboard.getT45_TemperatureVal()) +
                        ", T4.5 Pres(kPa): " + tokPa((int) Dashboard.getT45_PressureVal()) +
                        ", T7.5 Temp(C): " + toCelcius((int) Dashboard.getT75_TemperatureVal()) +
                        ", T7.5 Pres(kPa): " + tokPa((int) Dashboard.getT75_PressureVal()) +
                        ", T17 Temp(C): " + toCelcius((int) Dashboard.getT17_TemperatureVal()) +
                        ", T17 Pres(kPa): " + tokPa((int) Dashboard.getT17_PressureVal()) +
                        ", N1 RPM: " + Dashboard.getN1_EngineRPMVal() +
                        ", N2 RPM: " + Dashboard.getN2_EngineRPMVal() +
                        ", \t TIME: "+ date.substring(0,4) +
                        " " + date.substring(4, 6) +
                        " " + date.substring(6,8) +
                        " " + date.substring(9, 11) +
                        ":" + date.substring(11, 13) +
                        ":" + date.substring(13, 15));
            }else {
                printWriter.println("OilTmpSca(F): " + Dashboard.getScav_OilTemperatureVal() +
                        ", OilTmpOut(F): " + Dashboard.getOutlet_OilTemperatureVal() +
                        ", OilPres(PSI): " + Dashboard.getOilPressureVal() +
                        ", Ambient Pres(PSI): " + Dashboard.getAmbient_pressureVal() +
                        ", Ambient Temp(F): " + Dashboard.getAmbient_TemperatureVal() +
                        ", T1 Temp(F): " + Dashboard.getT1_TemperatureVal() +
                        ", T1 Pres(PSI): " + Dashboard.getT1_PressureVal() +
                        ", T4.5 Temp(F): " + Dashboard.getT45_TemperatureVal() +
                        ", T4.5 Pres(PSI): " + Dashboard.getT45_PressureVal() +
                        ", T7.5 Temp(F): " + Dashboard.getT75_TemperatureVal() +
                        ", T7.5 Pres(PSI): " + Dashboard.getT75_PressureVal() +
                        ", T17 Temp(F): " + Dashboard.getT17_TemperatureVal() +
                        ", T17 Pres(PSI): " + Dashboard.getT17_PressureVal() +
                        ", N1 RPM: " + Dashboard.getN1_EngineRPMVal() +
                        ", N2 RPM: " + Dashboard.getN2_EngineRPMVal() +
                        ", \t TIME: "+ date.substring(0,4) +
                        " " + date.substring(4, 6) +
                        " " + date.substring(6,8) +
                        " " + date.substring(9, 11) +
                        ":" + date.substring(11, 13) +
                        ":" + date.substring(13, 15));
            }
            try {
                Thread.sleep(LoggerInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stop(){
        System.out.println("Exiting!");
        printWriter.close();
        exit=true;
    }

    public Logger(Boolean exitValue){
        exit = exitValue;
    }

    public static int toFahrenheit(int C){
        return (int)(C*1.8+32);
    }

    public static int toCelcius(int F){
        return (int)((F-32)/1.8);
    }

    public static int tokPa(int PSI){return (int)(0.145*PSI);}

    public static int toPSI(int kPa){return (int)(6.895*kPa);}

    public static int toLPH(int GPH){return (int)(GPH*3.785);}
}
