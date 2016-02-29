package accountServer;

/**
 * Created by GEO on 28.02.16.
 */
public class AccountServerImpl implements AccountServer{

    private long usersCount;
    private long usersLimit;

    public AccountServerImpl(long usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public void addNewUser() {
        usersCount+=1L;
    }

    @Override
    public void remiveUser() {
        usersCount -=1L;
    }

    @Override
    public long getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(long usersLimit) {
        this.usersLimit=usersLimit;
    }

    @Override
    public long getUsersCount() {
        return usersCount;
    }
}
