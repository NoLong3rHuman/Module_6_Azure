package org.example;

public class Person
{
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String imagePath;

    public Person() {}
    public Person(String name, String email, String phone, String address, String password, String imagePath)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.imagePath = imagePath;
    }

    public Person(int id, String name, String email, String phone, String address, String password, String imagePath)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.imagePath = imagePath;

    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;

    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getImagePath()
    {
        return imagePath;
    }
    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

}

