package arena.users;

/**
 * Created by August on 2016-12-09.
 */
public class Operator implements IOperator {

    private final IUser user;

    private Operator(IUser user){
        this.user = user;
    }

    public static IOperator create(String name){
        return Operator.create(User.createMockUser(name));
    }

    public static IOperator create(IUser user){
        return new Operator(user);
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public int getId(){
        return user.getId();
    }
}
