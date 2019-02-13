package com.example.myapplication2;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersControl implements Serializable {
    private List<User> users;
    private final String path = "/Data/";
    private final String fileName = "Users.txt";

    public UsersControl(Context cont) {
        users = new ArrayList<>();
        PrintWriter writer;
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
            Log.e("file", "dirs");
        }
        try {
            FileOutputStream fos = cont.openFileOutput(fileName, Context.MODE_APPEND);
            writer = new PrintWriter(fos, true);
        } catch (FileNotFoundException e) {
            Log.e("writer",e.getMessage());
        }
    }

    public void loadUsers(Context cont) {
        Scanner reader = null;
        String buff;
        String[] fields;
        try {
            FileInputStream fos = cont.openFileInput(fileName);
            reader = new Scanner(fos);
        } catch (FileNotFoundException e) {
            Log.e("reader",e.getMessage());
        }
        if (reader != null)
            while (reader.hasNextLine()) {
                buff = reader.nextLine();
                fields = buff.split(" ");
                if (fields.length != 4)
                    continue;
                User user = new User(fields[0], fields[1], fields[2], fields[3]);
                users.add(user);
            }
    }

    public void saveUsers(Context cont) {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = cont.openFileOutput(fileName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Log.e("clear",e.getMessage());
        }
        try {
            FileOutputStream fos = cont.openFileOutput(fileName, Context.MODE_APPEND);
            writer = new PrintWriter(fos, true);
        } catch (FileNotFoundException e) {
            Log.e("writer",e.getMessage());
        }
        if (writer != null)
            for (User user : users) {
                String fields = user.getName() + " " + user.getSurname() + " " + user.getEmail() + " " + user.getPassword();
                writer.println(fields);
            }
    }

    public void userAdd(User user){
        users.add(user);
    }

    public boolean emailExist(String email) {
        if (users.size() > 0) {
            for (User user : users) {
                if (user.getEmail().equals(email))
                    return true;
            }
        }
        return false;
    }

    public boolean userExist(String email, String password){
        if (users.size() > 0) {
            for (User user : users)
                if (user.getEmail().equals(email) && user.getPassword().equals(password))
                    return true;
        }
        return false;
    }

    public User getUser(String email) {
        if (users.size() > 0) {
            for (User user : users)
                if (user.getEmail().equals(email))
                    return user;
        }
        return null;
    }
}
