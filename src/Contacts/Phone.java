package Contacts;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Phone {
    ArrayList<Book> list = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    boolean status = true;
    private String tName;
    private String tNum;

    public static void main(String[] args) throws IOException{
        Phone phone = new Phone();
        File file = new File("/Users/savegeilitvik/Desktop/Java/phoneBook.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        else{
            phone.readBook();
        }
        phone.operator();
    }

    private void operator() throws IOException {
        while(status){
            System.out.println("---- Телефонная книга ----");
            System.out.println("\t1. Добавить \t2. Изменить \t3. Показать всё\n \t4. Сохранить \t5. Удалить \t6. Выйти");
            System.out.print("Ввод: ");
            int choice = sc.nextInt();
            System.out.println("--------------------------");
            switch (choice){
                case 1:{
                    add();
                    break;
                }
                case 2:{
                    update();
                    break;
                }
                case 3:{
                    findAll();
                    break;
                }
                case 4:{
                    save();
                    break;
                }
                case 5:{
                    delete();
                    break;
                }
                case 6:{
                    sc.close();
                    status = false;
                    break;
                }
            }
        }

    }

    private void add() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Имя: ");
        tName = br.readLine();
        System.out.print("Телефон: ");
        tNum = br.readLine();
        Book book = new Book(tName,tNum);
        list.add(book);
    }

    private void save(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("phoneBook.txt"));
            for(Book book:list){
                bw.write(book.getName()+","+book.getNumber());
                bw.write("\r\n");
            }
            bw.flush();
            System.out.println("Успешно сохранено!");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void update(){
        System.out.print("Введите имя контакта, номер которго хотите изменить: ");
        String uName = sc.nextLine();
        System.out.print("Ввведите верный номер телефона: ");
        String uNum = sc.nextLine();
        for (Book book:list){
            if(book.getName().equals(uName)){
                book.setNumber(uNum);
                System.out.println("Успешно изменено!");
            }
        }
    }

    private void delete()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите имя контакта, которого хотите удалить: ");
        String dName = br.readLine();
        for (int i = 0; i < list.size(); i++) {
            Book book = (Book) list.get(i);
            if(book.getName().equals(dName)){
                list.remove(book);
                System.out.println("Успешно удалено!");
            }
            //else continue;
        }
    }
    private void readBook()throws IOException{
        FileInputStream filein;
        try{
            filein = new FileInputStream("phoneBook.txt");
            if (filein.available()==0){
                System.out.println("Содержимое тел. книги пустое, главня страница скоро будет загружена...");
            }else{
                BufferedReader br = new BufferedReader (new InputStreamReader(filein));
                String line = null;
                Book book = null;
                while ((line=br.readLine()) != null){
                    String[] str = line.split(",");
                    book = new Book(str[0],str[1]);
                    list.add(book);
                    book = null;
                }
                filein.close();
                br.close();
                System.out.println("Телефонная книга загружена, можете выполять операции с данными...");
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void findAll(){
        for(Book book:list){
            System.out.println(book.show());
        }
    }


}

