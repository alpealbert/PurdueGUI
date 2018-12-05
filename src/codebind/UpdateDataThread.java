public class UpdateDataThread implements Runnable {

    private int var;

    public UpdateDataThread(int var) {
        this.var = var;
    }

    public int toFahrenheit(int C){
        return (int)(C*1.8+32);
    }

    public int toCelcius(int F){
        return (int)((F-32)/1.8);
    }

    public void run() {
        while (true)
        for(int i = 0; i< 30000; i+=100){
            //ADD CODE READING NEW DATA AND CALL SET-FUNCTIONS IN MAIN THREAD FOR GRAPHICAL DISPLAY


            try {
                if(Dashboard.getValueType().equals("Current Values: Metric")){
                    Dashboard.setOilTempScavenge((int) (((i/100)-32)/1.8));
                    Dashboard.setOilTempOutlet((int) (((i/100)-32)/1.8));
                }else{
                    Dashboard.setOilTempScavenge(i/100);
                    Dashboard.setOilTempOutlet(i/100);
                }
                Dashboard.setN1_EningeRPM(i);
                Dashboard.setN2_EningeRPM(i);
                Dashboard.setOilPressure(45);
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}