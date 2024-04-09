package org.polesmih.db;

import org.polesmih.gameTools.ListManager;

import java.time.LocalDateTime;

public class WriteUser {

    public static void writeUserIntoDb(LocalDateTime date, Long id, String firstName, String userCount) {

        UserModel user = new UserModel();

        user.setDate(date);
        user.setUserTgId(id);
        user.setFirstName(firstName);
        user.setUserCount(userCount);

        UserModel userModel = new UserModel(date, id, firstName, userCount);

        UserConnection.userAccounting(userModel);

    }

    public static void writeUserCount(LocalDateTime date, long id, String firstName) {

        long count = ListManager.countUserWord(id);

        if (count > 0) {
            writeUserIntoDb(
                    date,
                    id,
                    firstName,
                    String.valueOf(count)
            );
        }

    }

}
