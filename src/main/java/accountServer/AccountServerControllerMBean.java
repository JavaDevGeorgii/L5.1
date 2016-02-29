package accountServer;

/**
 * Created by GEO on 28.02.16.
 */
public interface AccountServerControllerMBean {

    long getUsers();
    long getUsersLimit();
    void setUsersLimit(long usersLimit);
}
