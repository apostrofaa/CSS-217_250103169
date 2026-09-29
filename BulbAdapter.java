public class BulbAdapter implements SmartDevice {
    private final LegacyBulb bulb;
    private final int calibrationSeedK;
    public static final int DEFAULT_K = 9;
    public BulbAdapter(LegacyBulb bulb) {
        this(bulb, DEFAULT_K);
    }
    public BulbAdapter(LegacyBulb bulb, int k) {
        if (bulb == null) {
            throw new IllegalArgumentException("Bulb cannot be null");
        }
        this.bulb = bulb;
        this.calibrationSeedK = k;
    }
    @Override
    public void turnOn() {
        bulb.setBrightness(255);
    }
    public void turnon() {
        turnOn();
    }
    @Override
    public void turnOff() {
        bulb.setBrightness(0);
    }
    public void turnoff() {
        turnOff();
    }
    @Override
    public boolean isOn() {
        if (!bulb.hasPower()) {
            return false;
        }
        return bulb.readBrightness() > 0;
    }
    public boolean ison() {
        return isOn();
    }
    @Override
    public int getPowerPercent() {
        if (!bulb.hasPower()) {
            return 0;
        }
        int rawBrightness = bulb.readBrightness();
        if (rawBrightness == 0) {
            return 0;
        }
        int rawPercent = (rawBrightness * 100) / 255;
        int result = rawPercent + this.calibrationSeedK;
        return Math.min(100, result);
    }
}
