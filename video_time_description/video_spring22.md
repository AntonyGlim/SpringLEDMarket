**0:02** разбор ДЗ Как было сделано оформление не зарегистрированных пользоватлей  
**0:41** добавил фавикон  
**0:42** расширили Order добавлен enum Status и LocalDateTime (@CteationTimestump и @UpdateTimestump)  
**0:45** в ShopController добавили количество отображаемых страниц в cookie (@CookieValue) (список еще не добавлен)  
**0:46** импользована Map для requestParam  
**0:48** добавлены поля в таблицу БД  
**0:50** рзбираем вопросы  
**0:54** решили, что у нас только покупатели, а админки нет  
**1:02** перешли к оформлению аонимным пользователем  
**1:12** Добавка регистрации пользователей  
**1:15** registration-form.html Добавление SystemUser на front  
**1:16** SystemUser В пакете utils @NotNull @Size @Email javax.validation  
**1:18** добавление валидаторов FieldMatch и FieldMatchValidator  
**1:21** Описание класса SystemUser  
**1:22** RegistrationController сначала оздаем болванку а потом, если все поля норм - то сохраняем  
**1:26** отображение ошибок на фронте, если ввели что-то не то  
**1:28** добавление метода save() в UserService и описываем логику  
**1:32** RegistrationController initBinder() - отрезалка  
**1:39** изменены адреса для регистрации  
**1:40** добавили записи в навигацию  
**1:44** добавлена проверка пароля слишком короткая в SystemUser  
**1:45** добавляем телефон в SystemUser  
**1:46** UserServiceImpl добавлен телефон  
**1:46** телефон на фронт  
**1:48** Пользователей можем добавлять через форму. Сделано  
**1:49** Работа с почтой - храним в ресурсах логин и пароль в отдельном файле - чтобы не лить на гит. Добавление анотации @PropertySource в AppConfig   
**1:51** Добавляем starter mail  
**1:52** class MailConfig достаем проперти JavaMailSenderImpl  
**1:54** class MailMessageBuilder. TemplateEngine - строит письма из о-та типа ... (order В НАШЕМ СЛУЧАЕ)  
**1:56** order-mail.html добавляем. строки таблицы красим в разные цвета  
**1:58** class MailService - переодически отправляет классы в разных потоках  
**2:02** добавление полей в контроллеры. Галочку - разрешить всяким не понятным пользоваться  
**2:05** ДЗ  
**2:08** Подтверждение по email  