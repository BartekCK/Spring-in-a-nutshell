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
