### Note
OAuth 2 provides several "grant types" for different use cases. The grant types defined are:
- Authorization Code
- Password
- Client credentials
- Implicit

Project này dùng grant_type=password để lấy access_token (người dùng cần cấp username
và password để lấy access_token)

Download Redis tại đây:
https://github.com/ServiceStack/redis-windows#current-version-30503-june-28-2016

### Ref:
- https://dzone.com/articles/how-to-configure-an-oauth2-authentication-with-spr
- https://www.baeldung.com/spring-security-oauth-revoke-tokens

### How to run (using Postman):
- Lấy access_token:
    - POST: http://localhost:9007/boot-oauth2/oauth/token
    - Header: Authorization=Basic Y2xpZW50SWQ6c2VjcmV0QHR1emFrdQ (clientId:secret@tuzaku in Base64)
    - Body (form-data):
        username=att
        password=1111
        grant_type=password
- Refresh access_token (xin cấp lại access_token)
    - POST: http://localhost:9007/boot-oauth2/oauth/token
    - Header: Authorization=Basic Y2xpZW50SWQ6c2VjcmV0QHR1emFrdQ
    - Body (form-data):
        grant_type=refresh_token
        refresh_token=... (refresh token ở bước lấy access_token)
- Dùng access_token trên truy cập protected resource:
    - GET: http://localhost:9007/boot-oauth2/api/book/all
    - Header: Authorization=Bearer + access_token
- Test API on swagger: http://localhost:9007/boot-oauth2/swagger-ui.html

### CHÚ Ý: Nếu muốn tách riêng authorization server và resource server, có thể tham khảo:
https://www.baeldung.com/spring-security-oauth-jwt