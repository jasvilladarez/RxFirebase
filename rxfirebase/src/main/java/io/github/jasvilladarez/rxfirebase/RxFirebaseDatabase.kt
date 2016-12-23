/*
 * Copyright 2016 Jasmine Villadarez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.jasvilladarez.rxfirebase

import com.google.firebase.FirebaseException
import com.google.firebase.database.*
import rx.Observable
import rx.subscriptions.Subscriptions

fun <T> Query.observeListOf(classType: Class<T>): Observable<List<T>> {
    return Observable.create<DataSnapshot> { subscriber ->
        val listener = addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                subscriber.onNext(dataSnapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                subscriber.onError(FirebaseException(error.message))
            }
        })

        subscriber.add(Subscriptions.create { removeEventListener(listener) })
    }.map { dataSnapshot ->
        dataSnapshot.children.toMutableList().map { it.getValue(classType) }
    }
}

fun <T> Query.observeChildren(classType: Class<T>) : Observable<ChildEvent<T>> {
    return Observable.create { subscriber ->
        val listener = addChildEventListener(object : ChildEventListener {
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevName: String) {
                subscriber.onNext(ChildEvent(ChildEvent.EventType.CHILD_MOVED,
                        dataSnapshot.getValue(classType)))
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, prevName: String) {
                subscriber.onNext(ChildEvent(ChildEvent.EventType.CHILD_CHANGED,
                        dataSnapshot.getValue(classType)))
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, prevName: String) {
                subscriber.onNext(ChildEvent(ChildEvent.EventType.CHILD_ADDED,
                        dataSnapshot.getValue(classType)))
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                subscriber.onNext(ChildEvent(ChildEvent.EventType.CHILD_REMOVED,
                        dataSnapshot.getValue(classType)))
            }

            override fun onCancelled(error: DatabaseError) {
                subscriber.onError(FirebaseException(error.message))
            }

        })

        subscriber.add(Subscriptions.create { removeEventListener(listener) })
    }
}

data class ChildEvent<out T> (
        val event: EventType,
        val child: T){

    enum class EventType {
        CHILD_ADDED, CHILD_MOVED, CHILD_CHANGED, CHILD_REMOVED
    }

}