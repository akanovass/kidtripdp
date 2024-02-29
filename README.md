# KIDTRIP
Kidtrip- cервис для развозки детей. 
Проект по созданию приложения для развозки детей предназначен для облегчения жизни родителей, которые нуждаются в услугах доставки своих детей в школу, кружки и другие места. 
Этот проект является бэкэнд-частью этого приложения.

### Структура проекта
![test1](https://github.com/akanovass/kidtripdp/assets/101473127/1e23970b-b97c-47a1-bb19-3e52eeb94036)
UML диаграмма


 #### 4 роли пользователя:
   `[ADMIN,
    DRIVER,
    PARENT,
    CHILD]`

* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'ADMIN');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'DRIVER');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'PARENT');`
* `INSERT INTO public.roles (id, name) VALUES (DEFAULT, 'CHILD');`


#### TripStatuses
`[NEW, ACCEPTED, DECLINED, WAITING, DRIVING, END]`

#### ApplicationStatus
`[NEW, ACCEPTED, DECLINED]`

### Swagger url:  [http://localhost:8085/swagger-ui/index.html] 
  
