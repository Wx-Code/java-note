**一、开闭原则**

　　**开闭原则（Open-Closed Principle, OCP）是指一个软件实体如类、模块和函数应该对 扩展开放，对修改关闭。**

　　所谓的开闭，也正是对扩展和修改两个行为的一个原则。强调 的是用抽象构建框架，用实现扩展细节。

　　可以提高软件系统的可复用性及可维护性。开 闭原则，是面向对象设计中最基础的设计原则。它指导我们如何建立稳定灵活的系统，实现开闭原则的核心思想就是面向抽象编程。

**二、依赖倒置原则**

　　**依赖倒置原则（Dependence Inversion Principle,DIP）是指设计代码结构时，高层模 块不应该依赖底层模块，二者都应该依赖其抽象。**

　　通过依赖倒置，可以减少类与类之间的耦合性，提高系统的稳定性，提高代码的 可读性和可维护性，并能够降低修改程序所造成的风险。

　　下面我们通过一个例子来具体阐述：

　　首先创建一个MyCar类，假如你现在有两辆车

```java
public class MyCar {
    public void AudiCarDriving() {
        System.out.println("AudiCar is drving ……");
    }
    
    public void BWMCarDriving() {
        System.out.println("BWMCar is drving ……");
    }
}
```

　　但是随着你经济的逐渐飙升，又买下一辆车，这个时候，业务扩展，我们的代码要从底层到高层（调用层）依次修改代码。在 MyCar 类中增加 xxxCarDriving()的方法，在高层也要追加调用。如此一来，系统发布以后，实际上是非 常不稳定的，在修改代码的同时也会带来意想不到的风险。接下来我们优化代码，创建 一个课程的抽象 CarDriving 接口：

```java
public interface CarDriving {
    void driving();
}
/**<br> * 创建AudiCar 类<br> */
public class AudiCar implements CarDriving {
 
    @Override
    public void driving() {
        System.out.println(this.getClass().getSimpleName()+"is drving ……");
    }
}
/**<br> * 创建BWMCar类<br> */
public class BWMCar implements CarDriving {
 
    @Override
    public void driving() {
        System.out.println(this.getClass().getSimpleName()+"is driving ……");
    }
}
/**<br> * 优化之前的MyCar 类<br> */
public class MyCar {
 
    public void driving(CarDriving carDriving) {
        carDriving.driving();
    }
}
 
//调用
public static void main(String[] args) {
    MyCar myCar = new MyCar();
    myCar.driving(new AudiCar());
    myCar.driving(new BWMCar());
}
```

　　我们这时候再看来代码，无论你有多少车，对于新车，我只需要新建一 类，通过传参的方式告诉MyCar，而不需要修改底层代码。实际上这是一种大家非常熟悉 的方式，叫依赖注入。注入的方式还有构造器方式和 setter 方式。我们来看构造器注入 方式：

```java
/**
 * @Description 构造器注入
 * @Date 2019\5\20
 */
public class MyCar_01 {
 
    private CarDriving carDriving;
 
    public MyCar_01(CarDriving carDriving) {
        this.carDriving = carDriving;
    }
 
    public void driving() {
        carDriving.driving();
    }
}
   /**
     * @Description 调用的时候一样
     */
    public static void main(String[] args) {
        new MyCar_01(new AudiCar()).driving();
        new MyCar_01(new BWMCar()).driving();
    }
```

　　根据构造器方式注入，在调用时，每次都要创建实例。那么，如果 MyCar 是全局单例，则 我们就只能选择用 Setter 方式来注入，继续修改 MyCar 类的代码：

```java
/**
 * @Description setter 注入
 * @Date 2019\5\20
 */
public class MyCar_02 {
 
    private CarDriving carDriving;
 
    public void setCarDriving(CarDriving carDriving) {
        this.carDriving = carDriving;
    }
 
    public void driving() {
        carDriving.driving();
    }
}
 
/*
 * @Description 调用方法如下
 */
public static void main(String[] args) {
    MyCar_02 myCar = new MyCar_02();
    myCar.setCarDriving(new AudiCar());
    myCar.driving();
    myCar.setCarDriving(new BWMCar());
    myCar.driving();
}
```

　　执行结果

```java
AudiCaris drving ……
BWMCaris driving ……
```

**三、单一职责原则**

　　**单一职责（Simple Responsibility Pinciple，SRP）是指不要存在多于一个导致类变更的原因。**

　　假设我们有一个 Class 负责两个职责，一旦发生需求变更，修改其中一个职责的逻辑代码，有可能会导致另一个职责的功能发生故障。这样一来，这个 Class 存在两个导 致类变更的原因。如何解决这个问题呢？我们就要给两个职责分别用两个 Class 来实现， 进行解耦。后期需求变更维护互不影响。这样的设计，可以降低类的复杂度，提高类的 可 读 性 ， 提高系统的可维护性，降低变更引起的风险。总体来说就是一个Class/Interface/Method 只负责一项职责。

```java
public class DriveCar {
 
    public void driving(String carType){
        if("SUV".equals(carType)){
            System.out.println(carType+"动力强悍，适合越野");
        }else {
            System.out.println(carType+"乘坐舒适");
        }
    }
}
 
/*调用如下*/
public static void main(String[] args) {
    DriveCar driveCar = new DriveCar();
    driveCar.driving("SUV");
    driveCar.driving("MPV");
}
```

　　从上面代码来看，DriveCar 类承担了两种处理逻辑。假如，现在要对SUV做更多处理，那么 SUV和MPV的大小、功能都不一样，必须要修改代码。而修改代码逻辑势必会相互影 响容易造成不可控的风险。我们对职责进行分离解耦，来看代码，分别创建两个类 SUVDrive 和 MPVDrive：

```java
public class SUVDrive {
    public void driving(String carType){
        System.out.println(carType + "动力强悍，适合越野");
    }
}
 
public class MPVDrive {
    public void driving(String carType){
        System.out.println(carType + "乘坐舒适");
    }
}
 
/*调用如下*/
public static void main(String[] args) {
    SUVDrive suv = new SUVDrive();
    suv.driving("SUV");
    MPVDrive mpv = new MPVDrive();
    mpv.driving("MPV");
}
```

　　如果有跟多的业务需要，可以设计一个顶层接口，然后再根据情况拆分成不同的接口，使其满足单一职责原则，便于后期维护

**四、接口隔离原则**

　　**接口隔离原则（Interface Segregation Principle, ISP）是指用多个专门的接口，而不使 用单一的总接口，客户端不应该依赖它不需要的接口。**

　　这个原则指导我们在设计接口时 应当注意一下几点：

　　　　 1、一个类对一类的依赖应该建立在最小的接口之上。

　　　　2、建立单一接口，不要建立庞大臃肿的接口。

　　　　3、尽量细化接口，接口中的方法尽量少（不是越少越好，一定要适度）。 接口隔离原则符合我们常说的高内聚低耦合的设计思想，从而使得类具有很好的可读性、 可扩展性和可维护性。我们在设计接口的时候，要多花时间去思考，要考虑业务模型， 包括以后有可能发生变更的地方还要做一些预判。所以，对于抽象，对业务模型的理解 是非常重要的。

```java
public interface IAnimal {
    void eat();
    void fly();
    void swim();
}
 
/**
 * @Description Bird 类实现：
 */
public class Bird implements IAnimal{
    @Override
    public void eat() { }
 
    @Override
    public void fly() { }
 
    @Override
    public void swim() { }
}
 
/**
 * @Description Dog 类实现
 */
public class Dog implements IAnimal {
    @Override
    public void eat() {}
 
    @Override
    public void fly() {}
 
    @Override
    public void swim() {}
}
```

　　可以看出，Bird 的 swim()方法可能只能空着，Dog 的 fly()方法显然不可能的。这时候， 我们针对不同动物行为来设计不同的接口，分别设计 IEatAnimal，IFlyAnimal 和 ISwimAnimal 接口，来看代码：

```java
public interface IEatAnimal{
    void eat();
}
 
public interface IFlyAnimal {
    void fly();
}
 
public interface ISwimAnimal {
    void swim();
}
 
/**
 * @Description Dog 只实现 IEatAnimal 和 ISwimAnimal 接口
 */
public class Dog implements ISwimAnimal,IEatAnimal {
    @Override
    public void eat() {}
    @Override
    public void swim() {}
}
```

**五、迪米特法则**

　　**迪米特原则（Law of Demeter LoD）是指一个对象应该对其他对象保持最少的了解，又 叫最少知道原则（Least Knowledge Principle,LKP），尽量降低类与类之间的耦合。**

　　迪米特原则主要强调只和朋友交流，不和陌生人说话。出现在成员变量、方法的输入、输 出参数中的类都可以称之为成员朋友类，而出现在方法体内部的类不属于朋友类。现在来设计一个权限系统，Boss 需要查看目前发布到线上的课程数量。这时候，Boss 要找到 TeamLeader 去进行统计，TeamLeader 再把统计结果告诉 Boss。接下来我们还是来看代码：

```java
public class Course {
}
 
import java.util.List;
 
public class TeamLeader {
    public void checkNumberOfCourses(List<Course> courseList){
        System.out.println("目前已发布的课程数量是："+courseList.size());
    }
}
 
import java.util.ArrayList;
import java.util.List;
 
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader){
    //模拟 Boss 一页一页往下翻页，TeamLeader 实时统计
        List<Course> courseList = new ArrayList<Course>();
        for (int i= 0; i < 20 ;i ++){
            courseList.add(new Course());
        }
        teamLeader.checkNumberOfCourses(courseList);
    }
}
 
public static void main(String[] args) {
    Boss boss = new Boss();
    TeamLeader teamLeader = new TeamLeader();
    boss.commandCheckNumber(teamLeader);
}
```

　　写到这里，其实功能已经都已经实现，代码看上去也没什么问题。根据迪米特原则，Boss 只想要结果，不需要跟 Course 产生直接的交流。而 TeamLeader 统计需要引用 Course 对象。Boss 和 Course 并不是朋友，从下面的类图就可以看出来

![img](https://img2018.cnblogs.com/blog/1424024/201905/1424024-20190520231318279-44109994.png)

下面来对代码进行改造：

```java
import java.util.ArrayList;
import java.util.List;
 
public class TeamLeader {
    public void checkNumberOfCourses(){
        List<Course> courseList = new ArrayList<Course>();
        for(int i = 0 ;i < 20;i++){
            courseList.add(new Course());
        }
        System.out.println("目前已发布的课程数量是："+courseList.size());
    }
}
 
 
public class Boss {
    public void commandCheckNumber(TeamLeader teamLeader){
        teamLeader.checkNumberOfCourses();
    }
}
```

　　再来看下面的类图，Course 和 Boss 已经没有关联了

![img](https://img2018.cnblogs.com/blog/1424024/201905/1424024-20190520231513785-296528758.png)

**六、里氏替换原则**

　　**里氏替换原则（Liskov Substitution Principle,LSP）是指如果对每一个类型为 T1 的对 象 o1,都有类型为 T2 的对象 o2,使得以 T1 定义的所有程序 P 在所有的对象 o1 都替换成 o2 时，程序 P 的行为没有发生变化，那么类型 T2 是类型 T1 的子类型。** 定义看上去还是比较抽象，我们重新理解一下，可以理解为一个软件实体如果适用一个 父类的话，那一定是适用于其子类，所有引用父类的地方必须能透明地使用其子类的对 象，子类对象能够替换父类对象，而程序逻辑不变。根据这个理解，我们总结一下： 引申含义：子类可以扩展父类的功能，但不能改变父类原有的功能。

　　　　1、子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法。

　　　　2、子类中可以增加自己特有的方法。

　　　　3、当子类的方法重载父类的方法时，方法的前置条件（即方法的输入/入参）要比父类 方法的输入参数更宽松。

　　　　4、当子类的方法实现父类的方法时（重写/重载或实现抽象方法），方法的后置条件（即 方法的输出/返回值）要比父类更严格或相等。

　　使用里氏替换原则有以下优点： 1、约束继承泛滥，开闭原则的一种体现。 2、加强程序的健壮性，同时变更时也可以做到非常好的兼容性，提高程序的维护性、扩 展性。降低需求变更时引入的风险。 现在来描述一个经典的业务场景，用正方形、矩形和四边形的关系说明里氏替换原则， 我们都知道正方形是一个特殊的长方形，那么就可以创建一个长方形父类 Rectangle 类：

```java
/**
 * @Description 创建一个长方形父类 Rectangle 类
 */
public class Rectangle {
    private long height;
    private long width;
 
    public long getWidth() {
        return width;
    }
 
    public void setWidth(long width) {
        this.width = width;
    }
 
    public long getHeight() {
        return height;
    }
 
    public void setHeight(long height) {
        this.height = height;
    }
}
 
/**
 * @Description 创建正方形 Square 类继承长方形
 */
public class Square extends Rectangle{
    private long length;
    public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }
    @Override
    public long getWidth() {
        return getLength();
    }
    @Override
    public long getHeight() {
        return getLength();
    }
    @Override
    public void setHeight(long height) {
        setLength(height);
    }
    @Override
    public void setWidth(long width) {
        setLength(width);
    }
}
 
public class LspMain {
    public static void resize(Rectangle rectangle){
        while (rectangle.getWidth() >= rectangle.getHeight()){
            rectangle.setHeight(rectangle.getHeight() + 1);
            System.out.println("width:"+rectangle.getWidth() + ",height:"+rectangle.getHeight());
        }
        System.out.println("resize 方法结束" +
                "\nwidth:"+rectangle.getWidth() + ",height:"+rectangle.getHeight());
    }
 
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(10);
        resize(rectangle);
    }
}
```

　　运行结果：

```java
width:20,height:11
width:20,height:12
width:20,height:13
width:20,height:14
width:20,height:15
width:20,height:16
width:20,height:17
width:20,height:18
width:20,height:19
width:20,height:20
width:20,height:21
resize 方法结束
width:20,height:21
```

　　发现高比宽还大了，在长方形中是一种非常正常的情况。现在我们再来看下面的代码， 把长方形 Rectangle 替换成它的子类正方形 Square，修改测试代码：

```java
public static void main(String[] args) {
        Square square = new Square();
        square.setLength(10);
        resize(square);
    }
```

　　这时候我们运行的时候就出现了死循环，违背了里氏替换原则，将父类替换为子类后， 程序运行结果没有达到预期。因此，我们的代码设计是存在一定风险的。里氏替换原则 只存在父类与子类之间，约束继承泛滥。我们再来创建一个基于长方形与正方形共同的 抽象四边形 Quadrangle 接口：

```java
public interface Quadrangle {
    long getWidth();
    long getHeight();
}
 
/**
 * @Description 修改 Rectangle 类
 */
public class Rectangle implements Quadrangle{
    private long height;
    private long width;
    @Override
    public long getWidth() {
        return width;
    }
    public long getHeight() {
        return height;
    }
    public void setHeight(long height) {
        this.height = height;
    }
    public void setWidth(long width) {
        this.width = width;
    }
}
 
/**
 * @Description 修改 Square 类
 */
public class Square implements Quadrangle{
    private long length;
    public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }
    @Override
    public long getWidth() {
        return length;
    }
    @Override
    public long getHeight() {
        return length;
    }
}
```

　　

此时，如果我们把 resize()方法的参数换成四边形 Quadrangle 类，方法内部就会报错。 因为正方形 Square 已经没有了 setWidth()和 setHeight()方法了。因此，为了约束继承 泛滥，resize()的方法参数只能用 Rectangle 长方形

**七、合成复用原则**

　　**合成复用原则（Composite/Aggregate Reuse Principle,CARP）是指尽量使用对象组 合(has-a)/聚合(contanis-a)，而不是继承关系达到软件复用的目的。**可以使系统更加灵 活，降低类与类之间的耦合度，一个类的变化对其他类造成的影响相对较少。 继承我们叫做白箱复用，相当于把所有的实现细节暴露给子类。组合/聚合也称之为黑箱 复用，对类以外的对象是无法获取到实现细节的。要根据具体的业务场景来做代码设计， 其实也都需要遵循 OOP 模型。还是以数据库操作为例，先来创建 DBConnection 类：

```java
public class DBConnection {
    public String getConnection(){
        return "MySQL 数据库连接";
    }
}
 
public class ProductDao {
    private DBConnection dbConnection;
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    public void addProduct(){
        String conn = dbConnection.getConnection();
        System.out.println("使用"+conn+"增加产品");
    }
}
```

　　这就是一种非常典型的合成复用原则应用场景。但是，目前的设计来说，DBConnection 还不是一种抽象，不便于系统扩展。目前的系统支持 MySQL 数据库连接，假设业务发生 变化，数据库操作层要支持 Oracle 数据库。当然，我们可以在 DBConnection 中增加对 Oracle 数据库支持的方法。但是违背了开闭原则。其实，我们可以不必修改 Dao 的代码， 将 DBConnection 修改为 abstract，来看代码：

 

```java
public abstract class DBConnection {
    public abstract String getConnection();
}
 
 
/**
 * @Description 将 MySQL 的逻辑抽离
 */
public class MySQLConnection extends DBConnection {
    @Override
    public String getConnection() {
        return "MySQL 数据库连接";
    }
}
 
 
/**
 * @Description 创建 Oracle 支持的逻辑
 */
public class OracleConnection extends DBConnection {
    @Override
    public String getConnection() {
        return "Oracle 数据库连接";
    }
}
```

　　具体选择交给应用层，来看一下类图

![img](https://img2018.cnblogs.com/blog/1424024/201905/1424024-20190520235413319-412181177.png)

**设计原则总结**

　　**学习设计原则，学习设计模式的基础。在实际开发过程中，并不是一定要求所有代码都 遵循设计原则，我们要考虑人力、时间、成本、质量，不是刻意追求完美，要在适当的 场景遵循设计原则，体现的是一种平衡取舍，帮助我们设计出更加优雅的代码结构。**