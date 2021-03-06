package hello.service;

import org.springframework.stereotype.Service;

import hello.common.ResponseStatus;
import hello.exception.RestException;

/**
 * Giả sử như ứng dụng này cần xác thực tài khoản ở chỗ khác, gọi chỗ
 * đó là xyz. Cái service này sẽ fake việc gọi sang module của xyz ở đâu đó
 * và xác thực tài khoản
 * @author Anhtu
 *
 */
@Service
public class XyzService {

    private static final String [] users = {"zuka", "liliana", "enzo", "zephys", "amily"};

    /**
     * Check if username belongs to XYZ organization or not
     * In this example, we assume that XYZ org has only users in array above,
     * and passwords of theirs are "1111"
     */
    public boolean checkUserXyz(String username, String password) {
        for (int i = 0; i < users.length; i++) {
            if(users[i].equals(username) && "1111".equals(password)) {
                return true;
            }
        }
        throw new RestException(ResponseStatus.USER_XYZ_NOT_FOUND);
    }
}
