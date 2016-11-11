package advertisement.adRepository;

/**
 * Creates a concrete AdRepository and hides it behind the
 * interface {@link AdRepository}.
 */
public class AdRepositoryFactory {

    public static AdRepository newAdRepository(){
        return new QueueAdRepository();
    }
}
