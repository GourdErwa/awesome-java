
# 23种设计模式-访问者模式（visitor pattern）

# 定义
>表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素类的前提下定义作用于这些元素的新操作。
从定义可以看出结构对象是使用访问者模式必备条件，而且这个结构对象必须存在遍历自身各个对象的方法。这便类似于Java语言当中的collection概念了。

# 概述
>可以解决哪些问题？
>* 在不更改类的情况下为对象结构的（某些）类定义新操作

>如何解决上述问题？
>* 定义一个单独的（访问者）接口，该接口实现对对象结构的元素执行的操作
>* 客户端遍历对象结构并在元素上调用调度操作accept（visitor） - 将请求“调度”（委托）给“接受的访问者对象”。然后访问者对象对元素执行操作（“访问元素”）


# 类图
## WIKI.UML diagram
![UML diagram](../doc/wiki-uml/wiki.visitor.uml.jpg)
## WIKI.Class diagram
![Class diagram](../doc/wiki-uml/wiki.visitor.class_diagram.png)

访问者模式主要包含如下几个角色：
* ``Visitor``: 抽象访问者。为该对象结构中的ConcreteElement的每一个类声明的一个操作。
* ``ConcreteVisitor``: 具体访问者。实现Visitor申明的每一个操作，每一个操作实现算法的一部分。
* ``Element``: 抽象元素。定义一个Accept操作，它以一个访问者为参数。
* ``ConcreteElement``: 具体元素 。实现Accept操作。
* ``ObjectStructure``: 对象结构。能够枚举它的元素，可以提供一个高层的接口来允许访问者访问它的元素。

> 在访问者模式中对象结构存储了不同类型的对象，以便不同的访问者来访问。
> 从上面的UML结构图中我们可以看出，访问者模式主要分为两个层次结构，一个是访问者层次结构，提供了抽象访问者和具体访问者，主要用于什么一些操作。
> 一个是元素层次结构，提供了抽象元素和具体元素，主要用于声明Accept操作。

> 在访问者模式中相同的访问者可以以不同的方式访问不同的元素，所以在访问者模式中增加新的访问者无需修改现有代码，可扩展行强。

> 同时在访问者模式用到了一种双分派的技术，所谓双分派技术就是在选择一个方法的时候，不仅仅要根据消息接收者（receiver）的运行时区别（Run time type），还要根据参数的运行时区别。
> 在访问者模式中，客户端将具体状态当做参数传递给具体访问者，这里完成第一次分派，然后具体访问者作为参数的“具体状态”中的方法，同时也将自己this作为参数传递进去，这里就完成了第二次分派。
> 双分派意味着得到的执行操作决定于请求的种类和接受者的类型。

## 项目模拟示例.Class diagram
![Class diagram](../doc/project-uml/visitor.class_diagram.png)

# 用途
* 优点
    1. 使得新增新的访问操作变得更加简单
    2. 能够使得用户在不修改现有类的层次结构下，定义该类层次结构的操作
    3. 将有关元素对象的访问行为集中到一个访问者对象中，而不是分散搞一个个的元素类中
* 缺点
    1. 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类都意味着要在抽象访问者角色中增加一个新的抽象操作，并在每一个具体访问者类中增加相应的具体操作，违背了“开闭原则”的要求
    2. 破坏封装。当采用访问者模式的时候，就会打破组合类的封装
    3. 难理解
* 适用场景
    1. 对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作
    2. 需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。
    3. e.g 有一个对象M，支持生成 SQL 语句，我们可以认为M 为元素，在不同的数据库下生成的 SQL 语句不同，我们把数据库定义为访问者。

# 衍生思考

# 相关链接
* [源码](https://github.com/GourdErwa/java-advanced/tree/master/design-patterns/patterns-visitor)
* [wiki.Mediator pattern](https://en.wikipedia.org/wiki/visitor_pattern)
* org.dom4j.Visitor
* com.sun.tools.classfile.Type#Visitor
* org.springframework.asm.AnnotationVisitor
* com.google.inject.spi.BindingScopingVisitor
* com.google.inject.spi.ElementVisitor<V> & com.google.inject.spi.Element
* Elastic源码中典型应用场景，命名规范与模式定义一致 org.elasticsearch.common.inject.spi.ElementVisitor&org.elasticsearch.common.inject.spi.Element
