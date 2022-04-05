package Contacts;

public class Book {
    private String name;
    private String number;

    public Book(String name, String number){
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
    public String show(){
        return "\nИмя: "+name+"\nТелефон: "+number;
    }
}
