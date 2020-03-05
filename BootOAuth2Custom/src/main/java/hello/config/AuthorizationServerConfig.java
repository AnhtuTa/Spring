package hello.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;

import hello.service.UserService;
import hello.service.XyzService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT_ID_1 = "client1";
    private static final String CLIENT_ID_2 = "client2";
    private static final String CLIENT_SECRET = "{noop}secret@tuzaku";
    private static final String RESOURCE_ID = "resource_id";
    private static final String CUSTOM_GRANT_TYPE = "xyz_type";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private XyzService xyzService;

    private TokenGranter tokenGranter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID_1)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER")
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID)
                .secret(CLIENT_SECRET)
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(86400)
                .and()
                .withClient(CLIENT_ID_2)
                .authorizedGrantTypes(CUSTOM_GRANT_TYPE)
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER")
                .scopes("read", "write")
                .resourceIds(RESOURCE_ID)
                .secret(CLIENT_SECRET)
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(86400);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore)
            .userDetailsService(userDetailsService)
            .tokenGranter(tokenGranter(endpoints));
        // endpoints.pathMapping("/oauth/token", "/authentication/login");
    }

    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<TokenGranter>(Arrays.asList(endpoints.getTokenGranter()));
        granters.add(new CustomTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory(), CUSTOM_GRANT_TYPE, userService, xyzService));
        tokenGranter = new CompositeTokenGranter(granters);
        return new CompositeTokenGranter(granters);
    }

    public TokenGranter getTokenGranter() {
        return tokenGranter;
    }

}
