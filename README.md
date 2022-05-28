# spring-boot-ltw

### Mô tả
-- Phần Data --
- Tạo các file Repository thao tác đến Database sử dụng Jdbctemplate
- Tạo các file Model đại diện cho từng model trong ứng dụng

-- Phần Controller --
- AdminController : Dành riêng cho người dùng có role là Admin, có thể quản lý người dùng
  - GetMapping /manage : Trả về giao diện quản lý người dùng bao gồm danh sách người dùng và form thống kê tìm kiếm người dùng
  - GetMapping /user/{id} : Trả về giao diện hiển thị chi iết thông tin về người dùng có id tương ứng
  - GetMapping /banUser/{id}: Cấm người dùng có id tương ứng khỏi hệ thống
  - PostMapping /searchUser : Tìm kiếm người dùng tương ứng với form search
- CommentController   
  - GetMapping /comment/{id} : Lấy ra các comment tương ứng với bài post có id tương ứng
  - PostMapping /comment : đăng tải comment tương ứng lên và lưu vào database
- HomeController 
  - GetMapping / : trả về giao diện trang hôm hiển thị danh sách các bài viết của hệ thống
- LoginController
  - PostMapping /signin : xác thực thông tin username, password của người dùng nếu chính xác lưu thông tin người đang đăng nhập vào session và trả về giao diện home
  - GetMapping /logou : Thoát người dùng đang đăng nhập khỏi hệ thống và xoá thông tin người dùng khỏi session
- PostController: Chỉ người dùng tạo ra bài viết hoặc admin của hệ thống mới có quyền chỉnh sửa và xoá bài viết
  - GetMapping /post/{id} : Trả về giao hiện hiển thị ra thông tin chi tiết của bài viết với id tương ứng
  - PostMapping /createNewPost : Lưu thông tin bài viết người dùng vừa đăng tải vào database
  - GetMapping /editPost/{id} : Trả về form chỉnh sửa thông tin chi tiết bài viết với id tương ứng
  - GetMapping /deletePost/{id} : Xoá bài viết với id tương ứng nếu là chủ nhân hoặc admin
  - PostMapping /filter : Tìm kiếm và hiển thị các bài viết phù hợp với yêu cầu tìm kiếm của người dùng
- RegisterController
  - PostMapping /register : Tạo mới người dùng sau khi xác thực thông tin phù hợp thì lưu xuống database vào lưu thông tin người dùng vào session và trả về giao diện home

-- Tạo các templates bằng file html để hiển thị giao diện:

  - home.html : để vào trang home giao diện hiện ra list các bài post của hệ thống
  - signup.html : để hiện thị form đăng ký người dùng
  - login.html : để hiển thị form đăng nhập người dùng
  - filterPost.html : để hiện thị trang tìm kiếm filter về các bài post phù hợp
  - formComment.html : để hiện thi trang tạo comment mới
  - formPost.html : để hiện thi trang tạo post mới
  - manage.html : hiển thị ra trang tìm kiếm filter về các user phù hợp
  - userDetail.html : hiển thị ra thông tin chi tiết về người dùng 
  - Các components html :
    - footer.html : phần footer của trang web
    - header.html : phần header của trang web
    - posts.html: phần list ra các bài viết 
    - users.html: phần list ra các user

-- Trong application.properties

chỉ đinh url của database: url=jdbc:mysql://localhost/sys

chỉ định username của db: username=root

chỉ định password của db: password=dtm0110

+) Trong pom.xml:
thêm dependencies tương ứng.

## Nhóm 13
- Đoàn Tuấn Mạnh
- Phan Trung Hưng
- Đoàn Huy Tuấn

### Đoàn Tuấn Mạnh (B19DCCN414) - làm package web

- Tạo base project, tổ chức database
- PostController
- CommentController
- AdminController
### Phan Trung Hưng (B19DCCN606)

- LoginController
- RegisterController
- Lưu đăng nhập sử dụng session
### Đoàn Huy Tuấn (B19DCCN330) 

- Làm các phần template sử dụng thymeleaf
- Html,Css cho giao diện ứng dụng
