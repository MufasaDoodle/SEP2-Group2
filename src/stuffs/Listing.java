package stuffs;

public class Listing
{
    private String title;
    private String description;
    private String category;
    private String location;
    private double price;
    private String duration;
    private String date;
    private int id;
    private double avgStarRating;

    public Listing(String title, String description, String category, String location, double price, String duration, String date, int id)
    {
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.id = id;
    }
    public Listing(String title, String category, String location, double price, String duration, String date, int id)
    {
        this.title=title;
        this.category=category;
        this.location=location;
        this.price=price;
        this.duration=duration;
        this.date=date;
        this.id=id;
    }

    public String getTitle()
    {
        return title;
    }
    public String getCategory()
    {
        return category;
    }
    public String getLocation()
    {
        return location;
    }
    public double getPrice()
    {
        return price;
    }
    public String getDuration()
    {
        return duration;
    }
    public String getDate()
    {
        return date;
    }
    public int getId()
    {
        return id;
    }
    public String getDescription()
    {
        return description;
    }
    public double getAvgStarRating()
    {
        return avgStarRating;
    }
    public void setAvgStarRating(double avgStarRating)
    {
        this.avgStarRating=avgStarRating;
    }
}
