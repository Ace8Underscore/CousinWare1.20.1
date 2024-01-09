package com.cousinware.eventlistener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventBus {

    /*
     * @author Ace8Underscores
     */

    boolean debug = false;

    List<Method> targetMethodsArrayList;
    ArrayList<Method> removeQueue;
    ArrayList<Method> addQueue;

    public EventBus() {
        targetMethodsArrayList = new ArrayList<>();
        removeQueue = new ArrayList<>();
        addQueue = new ArrayList<>();
    }

    public void addListener(Object c) {
        //below we search the given class to see if any method contains our listener if so we add it to the targetMethodsArrayList
        for (Method m : c.getClass().getMethods()) {
            if (ClassUtils.hasAnnotation(m, Listener.class)) {
                if (debug) System.out.println("Listener was add to class: " + c + " at method " + m);
                addQueue.add(m);

            }
        }
    }

    public void removeListener(Object c) {
        for (Method m : c.getClass().getDeclaredMethods()) {
            if (ClassUtils.hasAnnotation(m, Listener.class)) {
                if (debug) System.out.println("Listener was removed from class: " + c + " at method " + m);
                removeQueue.add(m);
                //System.out.println("is queue empty " + removeQueue.isEmpty());

            }
        }
    }
    public void postEvent(Object c) {
        //checking below to see if any of the queues are not empty. if not we remove and add methods to the main targetMethodArraylist before accessing the list
        if (!removeQueue.isEmpty()) removeQueueLogic();
        if (!addQueue.isEmpty()) addQueueLogic();

        // below we search each given method that has a listener and check if any of its parameters contain the posted even or object
        //TODO n^2 not good possibly could use a hashmap?
        if (targetMethodsArrayList.isEmpty()) return;
        for (Method method : targetMethodsArrayList) {

            if ((method.getParameterTypes()[0] == c.getClass())) {
                try {
                    method.invoke(method.getDeclaringClass(), c);
                } catch (Exception e) {
                    //System.out.println(e.getMessage());
                    //System.out.println("\u001B[31m" + "Tried to Post event but failed! Have you initialized your listener correctly? err at: " + method + "\n|| Failed event -> " + c.getClass() + "\u001B[0m");
                }
            }

        }
    }

    public void removeQueueLogic() {
        for (Method m : removeQueue) {
            targetMethodsArrayList.remove(m);
        }
        removeQueue = new ArrayList<>();
    }

    public void addQueueLogic() {
        targetMethodsArrayList.addAll(addQueue);
        addQueue = new ArrayList<>();
    }


    public List<Method> getTargetMethodsArrayList() {
        return this.targetMethodsArrayList;
    }
}
