/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liuyk.observer;

import java.util.ArrayList;

/**
 * Provides methods for registering or unregistering arbitrary observers in an {@link ArrayList}.
 * <p>
 * This abstract class is intended to be subclassed and specialized to maintain
 * a registry of observers of specific types and dispatch notifications to them.
 * <p>
 * The observer type.
 */
public abstract class EObservable<T> {
    /**
     * The list of observers.  An observer can be in the list at most
     * once and will never be null.
     */
    public final ArrayList<T> mObservers = new ArrayList<>();

    /**
     * Adds an observer to the list. The observer cannot be null and it must not already
     * be registered.
     *
     * @param observer the observer to register
     * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException    the observer is already registered
     */
    public void registerObserver(T observer) {
        if (observer == null) {
            return;
        }
        synchronized (mObservers) {
            if (mObservers.contains(observer)) {
                return;
            }
            mObservers.add(observer);
        }
    }

    /**
     * Removes a previously registered observer. The observer must not be null and it
     * must already have been registered.
     *
     * @param observer the observer to unregister
     * @throws IllegalArgumentException the observer is null
     * @throws IllegalStateException    the observer is not yet registered
     */
    public void unregisterObserver(T observer) {
        if (observer == null) {
            return;
        }
        synchronized (mObservers) {
            int index = mObservers.indexOf(observer);
            if (index == -1) {
                return;
            }
            mObservers.remove(index);
        }
    }

    /**
     * Remove all registered observers.
     */
    public void unregisterAll() {
        synchronized (mObservers) {
            mObservers.clear();
        }
    }
}
