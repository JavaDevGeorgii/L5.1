package accountServer;

/**
 * Created by GEO on 28.02.16.
 */
public class AccountServerController implements AccountServerControllerMBean {

    private AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public long getUsers() {
        return accountServer.getUsersCount();
    }

    @Override
    public long getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(long usersLimit) {
        accountServer.setUsersLimit(usersLimit);

    }
}
