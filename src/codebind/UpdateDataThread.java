public class UpdateDataThread implements Runnable {

    private int var;

    public UpdateDataThread(int var) {
        this.var = var;
    }

    public int toFahrenheit(int C){ return (int)(C*1.8+32);}

    public int toCelcius(int F){ return (int)((F-32)/1.8); }

    public int tokPa(int PSI){return (int)(0.145*PSI);}

    public int toPSI(int kPa){return (int)(6.895*kPa);}

    public int toLPH(int GPH){return (int)(GPH*3.785);}

    public void run() {
        Dashboard.setOilPressure(45);
        while (true) {
            //ADD CODE READING NEW DATA AND CALL SET-FUNCTIONS IN MAIN THREAD FOR GRAPHICAL DISPLAY


            //For loop for demonstration
            for (int i = 0; i < 30000; i += 100) {
                Dashboard.setN1_EngineRPMVal(i/100);
                Dashboard.setN2_EngineRPMVal(i/60);
                Dashboard.setFuelFlow(i/30);
                Dashboard.setAmbientTemperature(i/100);
                Dashboard.setAmbientPressure(i/100);
                Dashboard.setOilTempScavenge(i/100);
                Dashboard.setOilTempOutlet(i/100);
                Dashboard.setT1Temperature(i/100);
                Dashboard.setT1Pressure(i/100);
                Dashboard.setT45Temperature(i/100);
                Dashboard.setT45Pressure(i/100);
                Dashboard.setT75Temperature(i/100);
                Dashboard.setT75Pressure(i/100);
                Dashboard.setT17Temperature(i/100);
                Dashboard.setT17Pressure(i/100);

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
