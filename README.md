# RxFirebase
A wrapper for Google's Firebase Realtime Database library. It uses Kotlin to create extension functions for Queries.

# Usage
RxFirebase has 3 functions:
* `observe(T.class)` returns `Observable<T>`
* `observeListOf(T.class)` returns `Observalbe<List<T>>`
* `observeChildrenOf(T.class)` return `Observable<T>`

All these functions create an observable with Firebase Event listeners that gets removed when the subscriber unsubscribes. Both `observe` and `observeListOf` use Firebase's `ValueEventListener`, thus, every change made to a query is caught in `onNext` function of the subscriber. On the other hand, `observeChildrenOf`, uses Firebase's `ChildEventListener`, thus, every change made to the children of the query is caught in `onNext` function of the subscriber.

### Kotlin
```
FirebaseDatabase.getInstance().reference.observeListOf(Sample::class.java)
```
Above code returns `Observable<List<Sample>>`

### Java
```
RxFirebaseDatabaseKt.observe(FirebaseDatabase.getInstance().getReference(),
Sample.class)
```
Above code returns `Observable<Sample>`

You can also view the sample app I made using this library here:
```
https://github.com/jasvilladarez/Kotlin_Practice/tree/feature/firebase/Notes
```

# Download
``` 
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}

dependencies {
        compile 'com.github.jasvilladarez:rxfirebase:0.2.0'
}
```

# License
MIT License

Copyright (c) 2017 Jasmine Villadarez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
