package com.example.gymrattrial3;

import java.util.ArrayList;

public class User {

    private int id = 0;
    private String firstName = null, lastName = null;
    private String email = null, password = null;
    private String race = null;
    private String gender = null;
    private Integer age = null;
    private Integer height = null, weight = null;  //Measured in inches and lbs.
    private String goals = null;
    private int[] prList = new int[] {0,0,0,0,0};  //5 PRs: bench, squat, deadlift, row, mile. IN THAT ORDER
    private ArrayList<String> gymIDs = new ArrayList<String>();
    private ArrayList<String> brotherList = new ArrayList<String>();

    public User(String firstName)
    {this.firstName = firstName;}

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getName(){return (firstName + " " + lastName);}

    public void setFirstName(String name){firstName = name;}
    public String getFirstName(){return firstName;}

    public void setLastName(String name){this.lastName = name;}
    public String getLastName(){return lastName;}

    public void setID(int id){this.id = id;}
    public int getID(){return id;}

    public void setEmail(String email){this.email = email;}
    public String getEmail(){return email;}

    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}

    public void setRace(String race){this.race = race;}
    public String getRace(){return race;}

    public void setGender(String gender){this.gender = gender;}
    public String getGender(){return gender;}

    public void setAge(int age){this.age = age;}
    public void setAge(String age){this.age = Integer.parseInt(age);}
    public int getAge(){return age;}

    public void setHeight(int h){height = h;}
    public void setHeight(String h){height = Integer.parseInt(h);}
    public int getHeight(){return height;}

    public void setWeight(int w){weight = w;}
    public void setWeight(String w){weight = Integer.parseInt(w);}
    public int getWeight(){return weight;}

    public void setGoals(String goals){this.goals = goals;}
    public String getGoals(){return goals;}

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

    public void addGym(String gymID){gymIDs.add(gymID);}
    public void removeGym(String gymID){gymIDs.remove(gymID);}
    public ArrayList<String> getGymIDs(){return gymIDs;}

    public void addBrother(String brotherID){brotherList.add(brotherID);}
    public void removeBrother(String brotherID){brotherList.remove(brotherID);}
    public ArrayList<String> getBrothersList(){return brotherList;}

    public String toString()
    {
        String data = "\n\t";
        data += "ID: " + id + "\n\t";
        data += "FIRST NAME: " + firstName + "\n\t";
        data += "LAST NAME: " + lastName + "\n\t";
        data += "EMAIL: " + email + "\n\t";
        data += "PASSWORD: " + password + "\n\t";
        data += "RACE: " + race + "\n\t";
        data += "GENDER: " + gender + "\n\t";
        data += "AGE: " + age + "\n\t";
        data += "HEIGHT: " + height + " in.\n\t";
        data += "WEIGHT: " + weight + " lbs.\n\t";
        data += "GOALS: " + goals + "\n\t";
        data += "PR LIST: {" + prList[0] + ", " + prList[1] + ", " + prList[2] + ", " + prList[3] +
                ", " + prList[4] + "}";
        data += "GYM IDs: ";
        for (int i  = 0; i < gymIDs.size(); i++)
        {
            data += gymIDs.get(i);
            if (i < gymIDs.size() - 1)
                data += ", ";
        }
        data += "BROTHERS: ";
        for (int i  = 0; i < brotherList.size(); i++)
        {
            data += brotherList.get(i);
            if (i < brotherList.size() - 1)
                data += ", ";
        }
        return data;
    }
}
