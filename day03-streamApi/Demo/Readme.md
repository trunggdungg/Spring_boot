### Bean là gì?
- Được coi là thành phần xướng sống của ứng udnjg Spring
- Là đối tượng được tạo ra ,lắp ráp,quản lý bới SPring Ioc contaier


### Tạo ra ben như thế nào?
- Sử dụng annotation đánh dấu lên class: @Component, @Controller,@Rescontroller,@Service,@Repository,...
- Sử dụng annotation #Bean trong method trong classs= #Configuration

### Sử dụng nó như thế nào?

- Bean thường được sử dụng trong 1 Bean khác(dependency) 
- ví dụ: (A -> B)  b nằm trong a, b là phụ thuộc của a, a là cha của b, b là dpdc của a

- 3 cách sử dụng bean:
 + Field-based Injection
 + Contructor-based Injection
 + Setter-based Injection

//DAO thao tác với db, service gọi dao, controller gọi service,