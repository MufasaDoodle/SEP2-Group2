package stuffs;

import java.io.Serializable;

public class Listing implements Serializable
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
    private int accountId;

    public Listing(String title, String description, String category, String location, double price, String duration, String date, int id, int accountId)
    {
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.price = price;
        this.duration = duration;
        this.date = date;
        this.id = id;
        this.accountId = accountId;
    }
    public Listing(String title, String category, String location, double price, String duration, String date, int id, int accountId)
    {
        this.title=title;
        this.category=category;
        this.location=location;
        this.price=price;
        this.duration=duration;
        this.date=date;
        this.id=id;
        this.accountId = accountId;
    }

    public String getTitle()
    {
        return title;
    }

    public int getAccountId()
    {
        return accountId;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
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
