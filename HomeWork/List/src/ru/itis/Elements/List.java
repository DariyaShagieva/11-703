package ru.itis.Elements;

public class List {
    ListElement now;
    ListElement previously;
    ListElement elementsCycle;

    public void printMyList (){
        ListElement el = now;
        do{
            System.out.print(el.obj + " ");
            el = el.previouslyElement;
        } while (!el.equals(now));
    }

    public void addFront(Object obj){
        ListElement newEl = new ListElement();
        newEl.obj = obj;
        if (previously == null){
            elementsCycle = newEl;
            newEl.previouslyElement = elementsCycle;
            previously = newEl;
            now = elementsCycle;
        } else {
            newEl.previouslyElement = now;
            now = newEl;
            elementsCycle.previouslyElement = now;
        }
    }

    public void addBack(Object obj) {
        ListElement newPreviously = new ListElement();
        newPreviously.obj = obj;
        if (now == null) {
            addFront(obj);
        } else {
            addFront(obj);
            newPreviously.previouslyElement = now;
        }
    }

    public void deleteElement (int num){
        if (now == null){
            return;
        }

        if (now == previously){
            now = null;
            previously = null;
            return;
        }

        ListElement previouslyElement = now;
        for (int count = 0; count < num; count++){
            previouslyElement = now;
            now = previously;
        }
        previously.previouslyElement = previouslyElement;
    }
}
