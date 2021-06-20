package location;

public class Location {
    private double latitude;
    private double longitude;

    // Constructor
    public Location(double longitude, double latitude) {
        this.setLatitude(latitude);
        this.setLongitude(longitude);

    }

    @Override
    public String toString() {
        return "Location: " + latitude + ", " + longitude;
    }

    // Getters and Setters
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
