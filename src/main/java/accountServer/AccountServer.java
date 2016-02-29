package accountServer;

/**
 * Created by GEO on 28.02.16.
 */
public interface AccountServer {
    void addNewUser();
    void remiveUser();
    long getUsersLimit();
    void setUsersLimit(long usersLimit);
    long getUsersCount();
}
