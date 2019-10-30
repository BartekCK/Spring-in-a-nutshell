## 1. Creating Controller

```java
@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(){
        return "index.html";
    }
}
```

## 2. Tomcat jasper

Add dependencies to pom.xml

```maven
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jasper</artifactId>
    <version>9.0.24</version>
</dependency>
```

## 3.Accepting User Input

```java
@RequestMapping("add")
    public String add(HttpServletRequest req)
    {
        HttpSession httpSession = req.getSession();
        int i1 = Integer.parseInt(req.getParameter("num1"));
        int i2 = Integer.parseInt(req.getParameter("num2"));
        int i3 = i1 + i2;

        httpSession.setAttribute("num3",i3);

        return "result.jsp";
    }
```

```jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-2" pageEncoding="ISO-8859-2"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    Result is: ${num3}
</body>
</html>
```
## 4. @RequestParm

```java
@RequestMapping("add")
    public String add( @RequestParam("num1") int i1, @RequestParam(name = "num2") int i2, HttpSession session)
    {
        int i3 = i1 + i2;
        session.setAttribute("num3",i3);
        return "result.jsp";
    }
```

## 5. Model and View

```java
@RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int i1, @RequestParam(name = "num2") int i2)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result.jsp");
        int i3 = i1 + i2;
        mv.addObject("num3",i3);
        return mv;
    }
```

## 6. Prefix and Suffix

Changes in application.properties

```
spring.mvc.view.prefix= /views/
spring.mvc.view.suffix= .jsp
```

## 7. Model

```java
@RequestMapping("/add")
public String add(@RequestParam("num1") int i1, @RequestParam("num2") int i2, Model model)
{
    model.addAttribute("num3",i1 + i2);
    return "result";
}
```

## 8. ModelMap

```java
@RequestMapping("/add")
    public String add(@RequestParam("num1") int i1, @RequestParam("num2") int i2, ModelMap model)
    {
        model.addAttribute("num3",i1 + i2);
        return "result";
    }
```

## 9. ModelAttribute

#### BEFORE: 

```java
@RequestMapping("/addAlien")
    public String addAlien(@RequestParam("id") int id , @RequestParam("name") String name, Model m){
        Alien alien = new Alien(id,name);
        m.addAttribute("a1", alien);
        return "result";
    }
```

#### AFTER

```java
@RequestMapping("/addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        return "result";
    }
```


## 10. ModelAttribute at method level

```java
@ModelAttribute
    public void setName(Model model){
        model.addAttribute("alienName","Alien !!!");
    }
```


## 11. Post method

```java
 @PostMapping("/addAlien")
    public String addAlien(@ModelAttribute("a1") Alien alien){
        return "result";
    }
```

## 12. Get method

```java
@GetMapping("/getAlien")
    public String getAlien(Model m)
    {
        List <Alien> aliens = Arrays.asList(new Alien(1,"Roman"), new Alien(2,"Kacper"));
        m.addAttribute("aliens",aliens);
        return "result2";
    }
```

## 13. Add ORM configuration

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>2.1.8.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>
```
```
#### ===============================
#### = DATA SOURCE
#### ===============================

#### Set here configurations for the database connection

#### Connection url for the database "netgloo_blog"
spring.datasource.url = jdbc:mysql://localhost:3306/netgloo_blog?useSSL=false

#### Username and password
spring.datasource.username = root
spring.datasource.password = root

#### Keep the connection alive if idle for a long time (needed in production)
spring.datasource.testWhileIdle = true
spring.datasource.validationQuery = SELECT 1

#### ===============================
#### = JPA / HIBERNATE
#### ===============================

#### Use spring.jpa.properties.* for Hibernate native properties (the prefix is
#### stripped before adding them to the entity manager).

#### Show or not log for each sql query
spring.jpa.show-sql = true

#### Hibernate ddl auto (create, create-drop, update): with "update" the database
#### schema will be automatically updated accordingly to java entities found in
#### the project
spring.jpa.hibernate.ddl-auto = update

#### Naming strategy
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy

#### Allows Hibernate to generate SQL optimized for a particular DBMS
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
```

## 14. JPArepository fetchAll

```java
public interface AlienRepo extends JpaRepository<Alien,Integer> {
}

@Controller
public class HomeController {

    @Autowired
    AlienRepo repo;

    @GetMapping("/getAliens")
    public String getAliens(Model m){
        m.addAttribute("result",repo.findAll());
        return "result";
    }
}
```

## 15. JPArepository add and fetch

```java
@PostMapping("/addAlien")
    public String addAlien(@ModelAttribute Alien alien, Model m)
    {
        m.addAttribute("result",alien);
        repo.save(alien);
        return "result";
    }
    
@GetMapping("/getAlien")
    public String getAlien(@RequestParam int id, Model m){
        m.addAttribute("result", repo.getOne(id));
        return "result";
}
```

## 16. Query DSL 

```java
public interface AlienRepo extends JpaRepository<Alien,Integer> {

    Alien findByName(String name);
}

@GetMapping("/getAlien")
    public String getAlien(@RequestParam String name, Model m){
        m.addAttribute("result", repo.findByName(name));
        return "result";
    }
```

## 17. Query Annotations

```java
    @Query("from Alien where name = :name")
    Alien findAlienByTheBestParameter(@Param("name") String name);
```
## 18.RequestParm and PathVariable 

```java
@PostMapping("/alien")
public void addAlien(@RequestParam String name){
    Alien alien = new Alien();
    alien.setName(name);
    alenRepository.save(alien);
}

@GetMapping("aliens/{id}")
public Alien getAlien(@PathVariable("id") int id){
    return alenRepository.findById(id).get();
}
```

## 19.AOP

```java
@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logBefore(){
        LOGGER.info("getAlines() method will call");
    }


    @After("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logAfterWithException(){
        LOGGER.info("getAlines() method was called but maybe an exception occurred ");
    }

    @AfterReturning("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logAfter(){
        LOGGER.info("getAlines() method was called without exception");
    }

    @AfterThrowing("execution(public * com.example.demospring.controller.AlienController.getAliens())")
    public void logException(){
        LOGGER.warn("EXCEPTION");
    }
}
```

Configuration output file:

```
logging.level.root = info
logging.file=app.log
```
