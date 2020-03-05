package hello.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import hello.entity.Role;
import hello.entity.User;
import hello.service.UserService;
import hello.service.XyzService;

public class CustomTokenGranter extends AbstractTokenGranter {

    private UserService userService;

    private XyzService xyzService;

    protected CustomTokenGranter(AuthorizationServerTokenServices tokenServices,
            ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType,
            UserService userService, XyzService xyzService) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.userService = userService;
        this.xyzService = xyzService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> params = tokenRequest.getRequestParameters();

        String username = params.containsKey("username") ? params.get("username") : "";
        String password = params.containsKey("password") ? params.get("password") : "";

        // check user in database
        User user = userService.checkUser(username);

        // check user in another place
        xyzService.checkUserXyz(username, password);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "N/A", authorities);
        OAuth2Authentication oauth2Authentication = new OAuth2Authentication(tokenRequest.createOAuth2Request(client),
                authentication);
        return oauth2Authentication;
    }
}
