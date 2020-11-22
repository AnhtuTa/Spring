package hello.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import hello.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Class JwtService.java dùng để tạo và validate token. (tạo token với thông tin username, thời gian
 * hết hạn; kiểm tra thời gian hết han, chữ ký có hợp lệ không…)
 */
@Service
public class JwtService {
    public static final String JWT_SECRET_KEY = "tuzakusecret";
    public static final int JWT_EXPIRE_TIME = 86400000;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRE_TIME);

        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("username", user.getUsername());
        claimMap.put("roles", user.getRoleArray());

        System.out.println("expiryDate: " + expiryDate);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder() //
                .setClaims(claimMap) // phải set claim trước bọn ở dưới
                .setIssuer("Tuzaku, who comes from the Earth") //
                .setIssuedAt(now) //
                .setSubject("JWT example") //
                .setExpiration(expiryDate) //
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY) //
                .compact();
    }

    public boolean validateToken(String token) {
        String err;
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            err = "Invalid JWT token";
        } catch (ExpiredJwtException ex) {
            err = "Expired JWT token";
        } catch (UnsupportedJwtException ex) {
            err = "Unsupported JWT token";
        } catch (IllegalArgumentException ex) {
            err = "JWT claims string is empty";
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request =
                    ((ServletRequestAttributes) requestAttributes).getRequest();
            System.err.println(err + ". URL: " + request.getRequestURI());
        }
        return false;
    }

    public Map<String, Object> getClaimsFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims;
    }
}
