### Topics
- OAuth2
- JWT
- Spring security
- Restful API

### How to run (using Postman)
- Lấy access_token:
    - POST: http://localhost:9009/boot-oauth2-jwt/oauth/token
    - Header: Authorization=Basic Y2xpZW50SWQ6c2VjcmV0QHR1emFrdQ (clientId:secret@tuzaku in Base64)
    - Body (form-data):
        username=att
        password=1111
        grant_type=password
- Refresh access_token (xin cấp lại access_token)
    - POST: http://localhost:9009/boot-oauth2-jwt/oauth/token
    - Header: Authorization=Basic Y2xpZW50SWQ6c2VjcmV0QHR1emFrdQ
    - Body (form-data):
        grant_type=refresh_token
        refresh_token=... (refresh token ở bước lấy access_token)
- Dùng access_token trên truy cập protected resource:
    - GET: http://localhost:9009/boot-oauth2-jwt/api/book/all
    - Header: Authorization=Bearer + access_token
- Test API on swagger: http://localhost:9009/boot-oauth2-jwt/swagger-ui.html