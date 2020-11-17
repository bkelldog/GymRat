package com.example.gymrattrial3;

import java.util.ArrayList;

public class User {

    private String name = null;
    private int id = 0;
    private int[] prList = new int[] {0,0,0,0,0};  //5 PRs: bench, squat, deadlift, row, mile. IN THAT ORDER

    public User()
    {}

    public User(String name)
    {this.name = name;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

    public void setID(int id){this.id = id;}
    public int getID(){return id;}

    public void setPR(String exercise, int PR){prList[findExerciseIndex(exercise)] = PR;}
    public void setPR(int index, int PR){prList[index] = PR;}
    public int[] getPRList(){return prList;}
    public int getPR(int index){return prList[index];}
    private int findExerciseIndex(String exercise)
    {
        switch (exercise)
        {
            case "BENCH":
                return 0;
            case "SQUAT":
                return 1;
            case "DEADLIFT":
                return 2;
            case "ROW":
                return 3;
            case "MILE":
                return 4;
        }
        return -1;
    }

    public String toString()
    {
        return "USER: " + name + "\n\tID:" + id;
    }
}
    /*
    private String ID;
    private String firstName, lastName;
    private String email, password;
    private String race;
    private String gender;
    private int age;
    private int height, weight;  //Measured in inches and lbs.
    private String goals;
    private ArrayList<String> gymIDs = new ArrayList<String>();
    private int[] prList = new int[] {0,0,0,0,0};  //5 PRs: bench, squat, deadlift, row, mile. IN THAT ORDER
    private ArrayList<String> brotherList = new ArrayList<String>();

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User (String firstName, String lastName, String email, String password, String race, String gender, int age, int height, int weight, String goals, int[] prList)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setRace(race);
        setGender(gender);
        setAge(age);
        setHeight(height);
        setWeight(weight);
        setGoals(goals);
        for (int i = 0; i < this.prList.length; i++)
        {
            addPR(i, prList[i]);
        }
    }

    public void setFirstName(String name){this.firstName = name;}
    public void setLastName(String name){this.lastName = name;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setRace(String race){this.race = race;}
    public void setGender(String gender){this.gender = gender;}
    public void setAge(int age){this.age = age;}
    public void setAge(String age){this.age = Integer.parseInt(age);}
    public void setHeight(int h){height = h;}
    public void setHeight(String h){height = Integer.parseInt(h);}
    public void setWeight(int w){weight = w;}
    public void setWeight(String w){weight = Integer.parseInt(w);}
    public void setGoals(String goals){this.goals = goals;}
    public void addGym(String gymID){gymIDs.add(gymID);}
    public void removeGym(String gymID){gymIDs.remove(gymID);}
    public void addPR(String exercise, int PR){
        switch (exercise)
        {
            case "BENCH":
                prList[0] = PR;
                break;
            case "SQUAT":
                prList[1] = PR;
                break;
            case "DEADLIFT":
                prList[2] = PR;
                break;
            case "ROW":
                prList[3] = PR;
                break;
            case "MILE":
                prList[4] = PR;
        }
    }
    public void addPR(int index, int PR){prList[index] = PR;}
    public void addBrother(String brotherID){brotherList.add(brotherID);}
    public void removeBrother(String brotherID){brotherList.remove(brotherID);}

    public String getID(){return ID;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getEmail(){return email;}
    public String getRace(){return race;}
    private String getGender(){return gender;}
    public int getAge(){return age;}
    public int getHeight(){return height;}
    public int getWeight(){return weight;}
    public String getGoals(){return goals;}
    public ArrayList<String> getGymID(){return gymIDs;}
    public int[] getPRList(){return prList;}
    public ArrayList<String> getBrothersList(){return brotherList;}

    public boolean allFieldsValid()
    {
        if (!(stringValid(ID) || stringValid(firstName) || stringValid(lastName)))
            return false;
        if (!(stringValid(email) || stringValid(race) || stringValid(gender)))
            return false;

    }

    private boolean stringValid(String s)
    {return (!(s == null || s.equals("")));}

    public String getDataTabulated()
    {
        String data = "";
        data += ID + ", " + firstName + ", " + lastName + ", " + email + ", ";
        data += password + ", " + race + ", " + gender + ", " + age + ", ";
        data += height + ", " + weight + ", " + goals;

        //TODO: Figure out format for moving arraylist data
        /*
        if (gymIDs.size() > 0) {
            data += ", (";
            for (int i = 0; i < gymIDs.size(); i++) {
                data += gymIDs.get(i) + ", ";
            }
            data += ")";
        }*/
/*
        return data;
    }
}*/
