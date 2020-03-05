### Note
OAuth 2 provides several "grant types" for different use cases. The grant types defined are:
- Authorization Code
- Password
- Client credentials
- Implicit

Có thể xem project BootOauth2 trước vì project này kế thừa từ nó

Project này dùng 2 loại grant_type để lấy access_token:
- grant_type=password dùng cho client1
- grant_type=xyz_type dùng cho client2
- Trong ví dụ này, client1 có thể login = acc: att/1111 và sẽ có role=USER. password của acc này
  được lưu trong database
  Client2 có thể login = acc: zuka/1111 và sẽ có role=PARTNER. password của acc này
  KHÔNG lưu trong database (do đó trong database để giá trị "N/A")
- Role=USER thì gọi được API ở book, còn role=PARTNER thì gọi được API school
- Chỉ khi dùng client2 để logic (lấy token) thì mới có quyền role=PARTNER, vì acc zuka ko lưu password
  trong database, do đó ko thể login = client1 (grant_type=password)

### How to run (using Postman):
#### client1
- Lấy access_token:
    - POST: http://localhost:9008/boot-oauth2/oauth/token
    - Header: Authorization=Basic Y2xpZW50MTpzZWNyZXRAdHV6YWt1 (client1:secret@tuzaku in Base64)
    - Body (form-data):
        username=att
        password=1111
        grant_type=password
- Refresh access_token (xin cấp lại access_token)
    - POST: http://localhost:9008/boot-oauth2/oauth/token
    - Header: Authorization=Basic Y2xpZW50MTpzZWNyZXRAdHV6YWt1
    - Body (form-data):
        grant_type=refresh_token
        refresh_token=... (refresh token ở bước lấy access_token)
- Dùng access_token trên truy cập protected resource:
    - GET: http://localhost:9008/boot-oauth2/api/book/all
    - Header: Authorization=Bearer + access_token

#### client2
- Lấy access_token:
    - POST: http://localhost:9008/boot-oauth2/oauth/token
    - Header: Authorization=Basic Y2xpZW50MjpzZWNyZXRAdHV6YWt1 (client2:secret@tuzaku in Base64)
    - Body (form-data):
        username=zuka
        password=1111
        grant_type=xyz_type
- Dùng access_token trên truy cập protected resource:
    - GET: http://localhost:9008/boot-oauth2/api/school/all
    - Header: Authorization=Bearer + access_token

### Test API on swagger:
http://localhost:9008/boot-oauth2/swagger-ui.html