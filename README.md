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
``` dependencies {
	compile 'io.github.jasvilladarez:rxfirebase:0.2.0'
}```

# License
Copyright 2016 Jasmine Villadarez

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.