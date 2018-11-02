/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.utils;

import com.sanjnan.rae.common.annotations.SkipPatching;
import com.sanjnan.rae.common.exception.PatchingException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vinay on 1/11/16.
 */

/**
 * This class is used to patch one object with another object.
 */
public class ObjectPatcher {

  /**
   * This method can be used to copy attribute values from class of one type into another type. It depends on existence
   * of getter and setter methods. If a getter method is present and is not skipped by SkipPatching annotation and a
   * matching setter method exists in destination class, the value is copied. If the source value is null then it is not
   * copied.
   *
   * @param destinationClass
   * @param destination
   * @param sourceClass
   * @param source
   * @throws PatchingException
   * @throws InvocationTargetException
   * @throws IllegalAccessException
   */
  public static void diffAndPatch(Class<?> destinationClass,
                                  Object destination,
                                  Class<?> sourceClass,
                                  Object source)
          throws PatchingException, InvocationTargetException, IllegalAccessException {
    if (!(destinationClass.isInstance(destination))) {
      throw new PatchingException(destinationClass, destination);
    }
    if (!(sourceClass.isInstance(source))) {
      throw new PatchingException(sourceClass, source);
    }
    Map<String, Method> getterMethods = new HashMap<>();
    Map<String, Method> setterMethods = new HashMap<>();
    for (Method m : sourceClass.getMethods()) {
      if (m.getGenericParameterTypes().length == 0 &&
              m.getName().length() > 3 &&
              m.getName().startsWith("get")) {
        getterMethods.put(m.getName(), m);

      }
    }
    for (Method m : destinationClass.getMethods()) {
      if (m.getGenericParameterTypes().length == 1 &&
              m.getName().length() > 3 &&
              m.getName().startsWith("set")) {
        setterMethods.put(m.getName(), m);
      }
    }
    for (Map.Entry<String, Method> e : getterMethods.entrySet()) {

      Annotation[] annotations = e.getValue().getAnnotations();
      boolean skipped = false;
      for (Annotation a : annotations) {
        if (a.annotationType().equals(SkipPatching.class)) {
          skipped = true;
        }
      }

      if (!skipped) {

        Object sourceValue = e.getValue().invoke(source);
        if (sourceValue != null) {

          String setterMethodName = "set" + e.getValue().getName().substring(new String("get").length());
          Method m = setterMethods.get(setterMethodName);
          if (m != null) {
            m.invoke(destination, sourceValue);
          }
        }
      }
    }
  }
}
