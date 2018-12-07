package codebind;

public interface IDashboard {


    public  void setN1_EngineRPMVal(int n1_EngineRPMVal);

    public  void setN2_EngineRPMVal(int n2_EningeRPM);

    public  void setOilTempScavenge(int oilTempScavengeValue);

    public  void setOilTempOutlet(int oilTempOutletValue);

    public  void setOilPressure(int oilPressureValue);

    public  void setAmbientTemperature(int ambientTemperatureValue);

    public  void setAmbientPressure(int ambientPressureValue);

    public  void setT1Temperature(int T1TemperatureValue);

    public  void setT1Pressure(int T1PressureValue);

    public  void setT45Temperature(int T45TemperatureValue);

    public  void setT45Pressure(int T45PressureValue);

    public  void setT75Temperature(int T75TemperatureValue);

    public  void setT75Pressure(int T75PressureValue);

    public  void setT17Temperature(int T17TemperatureValue);

    public  void setT17Pressure(int T17PressureValue);

    public  void setFuelFlow(int fuelFlowValue);


    int toFahrenheit(int C);

    int toCelcius(int F);
    int tokPa(int PSI);
    int toPSI(int kPa);

    int toLPH(int GPH);
}
