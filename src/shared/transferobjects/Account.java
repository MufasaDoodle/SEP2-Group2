package shared.transferobjects;

import java.io.Serializable;

public class Account implements Serializable
{
  private String name;
  private String email;
  private String password;
  private String address;
  private String phoneNumber;
  private String bio;
  private int id;

  public Account(int id ,String name, String email, String password, String address,
      String phoneNumber, String bio)
  {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.bio = bio;
  }

  public Account(int id, String name, String email, String password, String address,
      String phoneNumber)
  {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getAddress()
  {
    return address;
  }

  public String getBio()
  {
    return bio;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
}