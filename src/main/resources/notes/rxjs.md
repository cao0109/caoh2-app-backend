## 1. rxjs是什么?

rxjs是一个响应式编程库,它提供了一套API,可以让我们更方便的处理异步事件.
它的核心是一个可观察对象,它可以被订阅,并且可以发出值.
[官方文档](https://rxjs-dev.firebaseapp.com/)

### Observable(可观察对象)

Observable是一个可观察对象,它可以被订阅,并且可以发出值.它是一个类,可以通过new操作符来创建一个实例.

```ts
import {Observable} from 'rxjs/Observable';

const observable = new Observable(observer => {
    observer.next(1); // 发出值
    observer.next(2);
    observer.next(3);
    setTimeout(() => {
        observer.next(4);
        observer.complete(); // 完成
    }, 1000);
});
```

### Observer(观察者)

Observer是一个观察者,它可以订阅一个可观察对象,并且可以接收可观察对象发出的值.

```ts
import {Observable} from 'rxjs/Observable';

const observable = new Observable(observer => {
    observer.next(1); // 发出值
    observer.next(2);
    observer.next(3);
    setTimeout(() => {
        observer.next(4);
        observer.complete(); // 完成
    }, 1000);
});

const observer = {
    next: x => console.log('got value ' + x),
    error: err => console.error('something wrong occurred: ' + err),
    complete: () => console.log('done'),
};

observable.subscribe(observer);
```

### Subscription(订阅)

Subscription是一个订阅,它是一个对象,它表示一个可观察对象的执行,并且可以取消这个执行.

```ts
import {Observable} from 'rxjs/Observable';

const observable = new Observable(observer => {
    observer.next(1); // 发出值
    observer.next(2);
    observer.next(3);
    setTimeout(() => {
        observer.next(4);
        observer.complete(); // 完成
    }, 1000);
});

const observer = {
    next: x => console.log('got value ' + x),
    error: err => console.error('something wrong occurred: ' + err),
    complete: () => console.log('done'),
};

const subscription = observable.subscribe(observer);
subscription.unsubscribe(); // 取消订阅
```

### Subject(主题)

Subject是一个主题,它是一个可观察对象,并且是一个观察者,它可以订阅一个可观察对象,并且可以接收可观察对象发出的值.


