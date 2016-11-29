package usersService;

/**
 * Created by August on 2016-11-26.
 */
public class User {

    public final int id;
    private String name;
    private int age;

    private static int nextUserId = 0;

    public User(String name, int age){
        this.id = allocateId();
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public int getId(){
        return this.id;
    }

    private static int allocateId(){
        return nextUserId++;
    }
}
