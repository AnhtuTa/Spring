### Topics
- JWT
- RESTful API

### Ref
- https://loda.me/huong-dan-spring-security-jwt-json-web-token-hibernate-loda1556683105052/
- https://stackjava.com/spring/code-vi-du-spring-boot-json-web-token-jwt.html

### How it works
- Project này dùng Filter tự định nghĩa để xác thực người dùng. Các request đều phải đi qua 1 filter JwtAuthenticationTokenFilter. Filter này sẽ check xem request có token jwt ở header hay ko. Nếu có nó sẽ lôi ra và check xem jwt đó có valid hay ko. Nếu valid thì cho request đó đi tiếp, chỉ đơn giản vậy thôi. Request sau đó có thể truy cập được API bảo mật
- Vẫn chưa dùng được Method security với Filter jwt trong project này. Project chỉ xác thực user chứ chưa có chức năng phân quyền
- Xem thêm project BootOauth2Jwt, sẽ có chức năng phân quyền

### Swagger local:
http://localhost:9011/boot-jwt/swagger-ui.html